package currency_converter_db;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * The behaviour behind every table form (Countries, Currencies, Users ...).
 *
 * The screens themselves are NetBeans drag-and-drop forms (CountriesForm.java /
 * CountriesForm.form and so on). Each of them creates one CrudController and hands
 * it the components that were dropped on the form; the controller loads the data,
 * fills the grid, validates, saves, deletes, searches and prints.
 */
public class CrudController {

    private final JFrame frame;
    private final TableSpec spec;
    private final boolean admin = Session.isAdmin();
    private final boolean ownerLocked;      // regular user looking at their own rows only
    private final boolean canSave;
    private final boolean canDelete;

    private final Map<Col, JComponent> inputs = new HashMap<>();
    private final Map<Col, List<Item>> lookups = new HashMap<>();
    private final List<Col> gridCols = new ArrayList<>();

    private final List<Object[]> all = new ArrayList<>();     // every row loaded
    private final List<Object[]> shown = new ArrayList<>();   // rows currently in the grid (after Find)
    private final DefaultTableModel model;
    private final JTable grid;
    private final JLabel status;
    private boolean populating;

    /**
     * @param inputs one component per column of the spec, in the same order as spec.cols
     * @param labels the caption label of each column, same order
     */
    public CrudController(JFrame frame, TableSpec spec, JTable grid, JLabel status,
                          JComponent[] inputs, JLabel[] labels) {
        this.frame = frame;
        this.spec = spec;
        this.grid = grid;
        this.status = status;
        this.ownerLocked = spec.ownerCol != null && !admin;
        this.canSave = !spec.noSave && (admin || spec.ownerCol != null);
        this.canDelete = admin || spec.ownerCol != null;

        loadLookups();
        for (int i = 0; i < spec.cols.size(); i++) {
            Col c = spec.cols.get(i);
            if (!visibleInForm(c)) {                 // owner column is hidden for regular users
                inputs[i].setVisible(false);
                if (inputs[i].getParent() instanceof javax.swing.JViewport) {
                    inputs[i].getParent().getParent().setVisible(false);
                }
                labels[i].setVisible(false);
                continue;
            }
            this.inputs.put(c, inputs[i]);
            configureInput(c, inputs[i]);
            if (c.required && !c.pk) labels[i].setText(labels[i].getText() + " *");
        }

        for (Col c : spec.cols) if (!c.hidden && visibleInForm(c)) gridCols.add(c);
        String[] heads = new String[gridCols.size()];
        for (int i = 0; i < heads.length; i++) heads[i] = gridCols.get(i).label;
        model = new DefaultTableModel(heads, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        grid.setModel(model);
        grid.setRowHeight(24);
        grid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        grid.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        grid.getTableHeader().setBackground(UI.BLUE);
        grid.getTableHeader().setForeground(Color.WHITE);
        grid.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        grid.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !populating) showSelected();
        });

        clearForm();
        reload(null);
    }

    public boolean canSave() { return canSave; }
    public boolean canDelete() { return canDelete; }

    private boolean visibleInForm(Col c) {
        return !(ownerLocked && c.name.equals(spec.ownerCol));
    }

    /** Fills combo boxes and decides which inputs the current user may edit. */
    @SuppressWarnings("unchecked")
    private void configureInput(Col c, JComponent in) {
        switch (c.type) {
            case BOOL:
                in.setEnabled(canSave);
                break;
            case ENUM:
                JComboBox<String> combo = (JComboBox<String>) in;
                combo.removeAllItems();
                for (String v : c.enumValues) combo.addItem(v);
                combo.setEnabled(canSave);
                break;
            case LOOKUP:
                JComboBox<Item> lk = (JComboBox<Item>) in;
                lk.removeAllItems();
                for (Item it : lookups.get(c)) lk.addItem(it);
                lk.setEnabled(canSave);
                break;
            case LONGTEXT:
                ((JTextArea) in).setEditable(false);
                break;
            case PASSWORD:
                ((JPasswordField) in).setEditable(canSave);
                in.setToolTipText("Leave blank to keep the current password");
                break;
            default:
                JTextField tf = (JTextField) in;
                boolean editable = canSave && !c.readOnly;
                tf.setEditable(editable);
                if (!editable) tf.setBackground(new Color(235, 238, 241));
                if (c.type == Col.Type.DATE) tf.setToolTipText("Format: yyyy-MM-dd");
        }
    }

    // ------------------------------------------------------------------ buttons

    public void newRecord() { grid.clearSelection(); clearForm(); }
    public void first() { move(0); }
    public void previous() { move(grid.getSelectedRow() - 1); }
    public void next() { move(grid.getSelectedRow() + 1); }
    public void last() { move(grid.getRowCount() - 1); }

    // ------------------------------------------------------------------ data loading

    private void loadLookups() {
        try (Connection conn = DBConnection.connect()) {
            if (conn == null) return;
            for (Col c : spec.cols) {
                if (c.type != Col.Type.LOOKUP) continue;
                List<Item> items = new ArrayList<>();
                if (!c.required) items.add(new Item(null, "(none)"));
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(c.lookupSql)) {
                    while (rs.next()) items.add(new Item(rs.getInt(1), rs.getString(2)));
                }
                lookups.put(c, items);
            }
        } catch (SQLException e) {
            AppLog.error("Loading lookups for " + spec.table, e);
            JOptionPane.showMessageDialog(frame, "Could not load lists: " + e.getMessage());
        }
        for (Col c : spec.cols) lookups.putIfAbsent(c, new ArrayList<>());
    }

    private Item findItem(Col c, Integer id) {
        for (Item it : lookups.get(c)) if (it.id == null ? id == null : it.id.equals(id)) return it;
        return new Item(id, id == null ? "(none)" : "#" + id);
    }

    /** Reloads all rows from MySQL; selectId (a primary key value) is re-selected afterwards. */
    private void reload(Integer selectId) {
        all.clear();
        StringBuilder sql = new StringBuilder("SELECT ");
        for (int i = 0; i < spec.cols.size(); i++) {
            if (i > 0) sql.append(", ");
            sql.append(spec.cols.get(i).name);
        }
        sql.append(" FROM ").append(spec.table);
        if (ownerLocked) sql.append(" WHERE ").append(spec.ownerCol).append(" = ?");
        sql.append(" ORDER BY ").append(spec.orderBy != null ? spec.orderBy : spec.pk().name);

        try (Connection conn = DBConnection.connect()) {
            if (conn == null) return;
            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
                if (ownerLocked) ps.setInt(1, Session.userId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Object[] row = new Object[spec.cols.size()];
                        for (int i = 0; i < row.length; i++) row[i] = readValue(rs, i + 1, spec.cols.get(i));
                        all.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            AppLog.error("Loading " + spec.table, e);
            JOptionPane.showMessageDialog(frame, "Could not load data: " + e.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
        }
        shown.clear();
        shown.addAll(all);
        fillGrid(selectId);
    }

    private Object readValue(ResultSet rs, int idx, Col c) throws SQLException {
        switch (c.type) {
            case LOOKUP: {
                int v = rs.getInt(idx);
                return findItem(c, rs.wasNull() ? null : v);
            }
            case BOOL:
                return rs.getBoolean(idx);
            case DECIMAL: {
                BigDecimal d = rs.getBigDecimal(idx);
                return d == null ? "" : d.toPlainString();
            }
            case DATETIME: {
                Timestamp t = rs.getTimestamp(idx);
                if (t == null) return "";
                String s = t.toString();
                return s.length() > 19 ? s.substring(0, 19) : s;
            }
            case DATE: {
                java.sql.Date d = rs.getDate(idx);
                return d == null ? "" : d.toString();
            }
            default: {
                String s = rs.getString(idx);
                return s == null ? "" : s;
            }
        }
    }

    private String display(Object v) {
        if (v instanceof Boolean) return ((Boolean) v) ? "Yes" : "No";
        return v == null ? "" : v.toString();
    }

    private void fillGrid(Integer selectId) {
        populating = true;
        model.setRowCount(0);
        int pkIdx = spec.cols.indexOf(spec.pk());
        int toSelect = -1;
        for (int r = 0; r < shown.size(); r++) {
            Object[] row = shown.get(r);
            Object[] line = new Object[gridCols.size()];
            for (int i = 0; i < line.length; i++) line[i] = display(row[spec.cols.indexOf(gridCols.get(i))]);
            model.addRow(line);
            if (selectId != null && selectId.toString().equals(row[pkIdx])) toSelect = r;
        }
        populating = false;
        status.setText(shown.size() + " record(s)" + (shown.size() != all.size() ? " (filtered from " + all.size() + ")" : ""));
        if (toSelect >= 0) {
            grid.setRowSelectionInterval(toSelect, toSelect);
        } else {
            clearForm();
        }
    }

    // ------------------------------------------------------------------ form <-> row

    private void clearForm() {
        for (Col c : spec.cols) {
            JComponent in = inputs.get(c);
            if (in == null) continue;
            setInput(c, in, null);
        }
    }

    private void showSelected() {
        int r = grid.getSelectedRow();
        if (r < 0 || r >= shown.size()) return;
        Object[] row = shown.get(r);
        for (int i = 0; i < spec.cols.size(); i++) {
            Col c = spec.cols.get(i);
            JComponent in = inputs.get(c);
            if (in != null) setInput(c, in, row[i]);
        }
    }

    @SuppressWarnings("unchecked")
    private void setInput(Col c, JComponent in, Object value) {
        switch (c.type) {
            case BOOL:
                ((JCheckBox) in).setSelected(value != null && (Boolean) value);
                break;
            case ENUM:
                JComboBox<String> e = (JComboBox<String>) in;
                if (value == null || value.toString().isEmpty()) e.setSelectedIndex(0);
                else e.setSelectedItem(value.toString());
                break;
            case LOOKUP:
                JComboBox<Item> l = (JComboBox<Item>) in;
                if (value instanceof Item) l.setSelectedItem(value);
                else if (l.getItemCount() > 0) l.setSelectedIndex(0);
                break;
            case LONGTEXT:
                ((JTextArea) in).setText(value == null ? "" : value.toString());
                break;
            case PASSWORD:
                ((JPasswordField) in).setText("");
                break;
            default:
                String txt = value == null ? "" : value.toString();
                if (value == null && c.type == Col.Type.DATE && c.todayDefault) txt = LocalDate.now().toString();
                ((JTextField) in).setText(txt);
        }
    }

    private void move(int index) {
        if (grid.getRowCount() == 0) return;
        int i = Math.max(0, Math.min(grid.getRowCount() - 1, index));
        grid.setRowSelectionInterval(i, i);
        grid.scrollRectToVisible(grid.getCellRect(i, 0, true));
    }

    // ------------------------------------------------------------------ save / delete / find / preview

    private static class InputError extends Exception {
        InputError(String m) { super(m); }
    }

    @SuppressWarnings("unchecked")
    private Object parse(Col c, JComponent in, boolean inserting) throws InputError {
        switch (c.type) {
            case BOOL:
                return ((JCheckBox) in).isSelected();
            case ENUM:
                return ((JComboBox<String>) in).getSelectedItem();
            case LOOKUP: {
                Item it = (Item) ((JComboBox<Item>) in).getSelectedItem();
                Integer id = it == null ? null : it.id;
                if (id == null && c.required) throw new InputError(c.label + " is required.");
                return id;
            }
            case PASSWORD: {
                String pw = new String(((JPasswordField) in).getPassword());
                if (pw.isEmpty()) {
                    if (inserting) throw new InputError(c.label + " is required.");
                    return null; // keep existing hash
                }
                if (pw.length() < 6) throw new InputError(c.label + " must be at least 6 characters.");
                return Security.hash(pw);
            }
            default:
        }
        String text = (c.type == Col.Type.LONGTEXT ? ((JTextArea) in).getText() : ((JTextField) in).getText()).trim();
        if (text.isEmpty()) {
            if (c.required) throw new InputError(c.label + " is required.");
            return null;
        }
        try {
            switch (c.type) {
                case INT: return Integer.valueOf(text);
                case DECIMAL: return new BigDecimal(text);
                case DATE: return java.sql.Date.valueOf(text);
                case DATETIME: return Timestamp.valueOf(text);
                default: return text;
            }
        } catch (IllegalArgumentException ex) {
            String hint = c.type == Col.Type.DATE ? " (use yyyy-MM-dd)" : c.type == Col.Type.DATETIME ? " (use yyyy-MM-dd HH:mm:ss)" : "";
            throw new InputError(c.label + " has an invalid value" + hint + ".");
        }
    }

    public void save() {
        Col pk = spec.pk();
        String pkText = ((JTextField) inputs.get(pk)).getText().trim();
        boolean inserting = pkText.isEmpty();

        List<Col> useCols = new ArrayList<>();
        List<Object> vals = new ArrayList<>();
        try {
            for (Col c : spec.cols) {
                if (c.pk || c.readOnly) continue;
                if (!visibleInForm(c)) {                       // owner column for a regular user
                    if (inserting) { useCols.add(c); vals.add(Session.userId); }
                    continue;
                }
                Object v = parse(c, inputs.get(c), inserting);
                if (c.type == Col.Type.PASSWORD && v == null) continue;   // unchanged password
                useCols.add(c);
                vals.add(v);
            }
        } catch (InputError ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Check your input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sql = new StringBuilder();
        if (inserting) {
            sql.append("INSERT INTO ").append(spec.table).append(" (");
            StringBuilder q = new StringBuilder();
            for (int i = 0; i < useCols.size(); i++) {
                if (i > 0) { sql.append(", "); q.append(", "); }
                sql.append(useCols.get(i).name);
                q.append("?");
            }
            sql.append(") VALUES (").append(q).append(")");
        } else {
            sql.append("UPDATE ").append(spec.table).append(" SET ");
            for (int i = 0; i < useCols.size(); i++) {
                if (i > 0) sql.append(", ");
                sql.append(useCols.get(i).name).append(" = ?");
            }
            sql.append(" WHERE ").append(pk.name).append(" = ?");
            if (ownerLocked) sql.append(" AND ").append(spec.ownerCol).append(" = ?");
        }

        try (Connection conn = DBConnection.connect()) {
            if (conn == null) return;
            try (PreparedStatement ps = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
                int p = 1;
                for (Object v : vals) ps.setObject(p++, v);
                if (!inserting) {
                    ps.setInt(p++, Integer.parseInt(pkText));
                    if (ownerLocked) ps.setInt(p, Session.userId);
                }
                int n = ps.executeUpdate();
                Integer id = inserting ? null : Integer.valueOf(pkText);
                if (inserting) {
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (keys.next()) id = keys.getInt(1);
                    }
                }
                if (n == 0) {
                    JOptionPane.showMessageDialog(frame, "Nothing was changed.");
                    return;
                }
                if (!spec.table.equals("audit_trail") && !spec.table.equals("error_log"))
                    AppLog.audit(inserting ? "INSERT" : "UPDATE", spec.table, id);
                reload(id);
                status.setText(inserting ? "Record added." : "Record updated.");
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            AppLog.error("Save " + spec.table, ex);
            String m = ex.getMessage() != null && ex.getMessage().toLowerCase().contains("duplicate")
                    ? "That value already exists (duplicate entry)." : "Database rule violated: " + ex.getMessage();
            JOptionPane.showMessageDialog(frame, m, "Cannot save", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            AppLog.error("Save " + spec.table, ex);
            JOptionPane.showMessageDialog(frame, "Could not save: " + ex.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        Col pk = spec.pk();
        String pkText = ((JTextField) inputs.get(pk)).getText().trim();
        if (pkText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Select a record to delete first.");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(frame, "Delete record " + pkText + " from " + spec.title + "?",
                "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM " + spec.table + " WHERE " + pk.name + " = ?"
                + (ownerLocked ? " AND " + spec.ownerCol + " = ?" : "");
        try (Connection conn = DBConnection.connect()) {
            if (conn == null) return;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, Integer.parseInt(pkText));
                if (ownerLocked) ps.setInt(2, Session.userId);
                int n = ps.executeUpdate();
                if (n > 0 && !spec.table.equals("audit_trail") && !spec.table.equals("error_log"))
                    AppLog.audit("DELETE", spec.table, Integer.parseInt(pkText));
                reload(null);
                status.setText(n > 0 ? "Record deleted." : "Nothing was deleted.");
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(frame,
                    "This record is used by other records, so it cannot be deleted.\nRemove or change those records first.",
                    "Cannot delete", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            AppLog.error("Delete " + spec.table, ex);
            JOptionPane.showMessageDialog(frame, "Could not delete: " + ex.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void find() {
        String q = JOptionPane.showInputDialog(frame, "Search " + spec.title + " for:\n(leave empty to show all records)");
        if (q == null) return;
        q = q.trim().toLowerCase();
        shown.clear();
        for (Object[] row : all) {
            if (q.isEmpty()) { shown.add(row); continue; }
            for (int i = 0; i < row.length; i++) {
                if (spec.cols.get(i).type == Col.Type.PASSWORD) continue;
                if (display(row[i]).toLowerCase().contains(q)) { shown.add(row); break; }
            }
        }
        if (shown.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No matching records found.");
            shown.addAll(all);
        }
        fillGrid(null);
        if (grid.getRowCount() > 0) move(0);
    }

    public void preview() {
        if (shown.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "There is nothing to print.");
            return;
        }
        String[] heads = new String[gridCols.size()];
        for (int i = 0; i < heads.length; i++) heads[i] = gridCols.get(i).label;
        List<String[]> rows = new ArrayList<>();
        for (Object[] row : shown) {
            String[] line = new String[gridCols.size()];
            for (int i = 0; i < line.length; i++) line[i] = display(row[spec.cols.indexOf(gridCols.get(i))]);
            rows.add(line);
        }
        PdfUtil.tableReport(spec.title, heads, rows);
    }
}
