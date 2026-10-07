package currency_converter_db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/** The main feature: convert an amount, optionally with fees (NetBeans form: see ConverterForm.form). */
public class ConverterForm extends javax.swing.JFrame {

    private final DefaultTableModel history = new DefaultTableModel(
            new String[]{"ID", "From", "To", "Amount", "Converted", "Rate", "Date"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private String lastFrom, lastTo;
    private RateService.Result last;

    public ConverterForm() {
        initComponents();
        getContentPane().setBackground(UI.BG);
        lblWho.setText(Session.username + " (" + Session.role + ")");
        lblNet.setForeground(UI.GREEN);
        tblHistory.setModel(history);
        loadCurrencies();
        loadHistory();
        UI.center(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        lblWho = new javax.swing.JLabel();
        lblFrom = new javax.swing.JLabel();
        cmbFrom = new javax.swing.JComboBox<>();
        btnSwap = new javax.swing.JButton();
        lblTo = new javax.swing.JLabel();
        cmbTo = new javax.swing.JComboBox<>();
        lblAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        chkFee = new javax.swing.JCheckBox();
        btnConvert = new javax.swing.JButton();
        pnlResult = new javax.swing.JPanel();
        lblRateCaption = new javax.swing.JLabel();
        lblRate = new javax.swing.JLabel();
        lblConvertedCaption = new javax.swing.JLabel();
        lblConverted = new javax.swing.JLabel();
        lblFeeCaption = new javax.swing.JLabel();
        lblFee = new javax.swing.JLabel();
        lblNetCaption = new javax.swing.JLabel();
        lblNet = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();
        scrHistory = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        btnFavorite = new javax.swing.JButton();
        btnAlert = new javax.swing.JButton();
        btnReceipt = new javax.swing.JButton();
        btnFullHistory = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Currency Converter");

        pnlHeader.setBackground(new java.awt.Color(0, 82, 147));
        lblHeading.setFont(new java.awt.Font("SansSerif", 1, 22));
        lblHeading.setText("Currency Converter");
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));

        lblWho.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblWho.setText(" ");
        lblWho.setForeground(new java.awt.Color(200, 220, 240));

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeading)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblWho)
                .addGap(20, 20, 20))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeading)
                    .addComponent(lblWho))
                .addGap(12, 12, 12))
        );

        lblFrom.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblFrom.setText("From");

        btnSwap.setBackground(new java.awt.Color(90, 100, 110));
        btnSwap.setForeground(new java.awt.Color(255, 255, 255));
        btnSwap.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnSwap.setText("Swap");
        btnSwap.setBorderPainted(false);
        btnSwap.setFocusPainted(false);
        btnSwap.setOpaque(true);
        btnSwap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwapActionPerformed(evt);
            }
        });

        lblTo.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblTo.setText("To");

        lblAmount.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblAmount.setText("Amount");

        txtAmount.setText("100");
        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        chkFee.setBackground(new java.awt.Color(244, 247, 250));
        chkFee.setText("Apply conversion fee");

        btnConvert.setBackground(new java.awt.Color(0, 130, 80));
        btnConvert.setForeground(new java.awt.Color(255, 255, 255));
        btnConvert.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnConvert.setText("Convert");
        btnConvert.setBorderPainted(false);
        btnConvert.setFocusPainted(false);
        btnConvert.setOpaque(true);
        btnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertActionPerformed(evt);
            }
        });

        pnlResult.setBackground(new java.awt.Color(234, 241, 248));
        lblRateCaption.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblRateCaption.setText("Rate used");

        lblRate.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblRate.setText("-");

        lblConvertedCaption.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblConvertedCaption.setText("Converted amount");

        lblConverted.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblConverted.setText("-");

        lblFeeCaption.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblFeeCaption.setText("Fee");

        lblFee.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblFee.setText("-");

        lblNetCaption.setFont(new java.awt.Font("SansSerif", 1, 13));
        lblNetCaption.setText("You receive");

        lblNet.setFont(new java.awt.Font("SansSerif", 0, 18));
        lblNet.setText("-");
        lblNet.setForeground(new java.awt.Color(0, 130, 80));

        javax.swing.GroupLayout pnlResultLayout = new javax.swing.GroupLayout(pnlResult);
        pnlResult.setLayout(pnlResultLayout);
        pnlResultLayout.setHorizontalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRateCaption)
                    .addComponent(lblConvertedCaption)
                    .addComponent(lblFeeCaption)
                    .addComponent(lblNetCaption))
                .addGap(20, 20, 20)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblConverted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        pnlResultLayout.setVerticalGroup(
            pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRateCaption)
                    .addComponent(lblRate))
                .addGap(8, 8, 8)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConvertedCaption)
                    .addComponent(lblConverted))
                .addGap(8, 8, 8)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFeeCaption)
                    .addComponent(lblFee))
                .addGap(8, 8, 8)
                .addGroup(pnlResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNetCaption)
                    .addComponent(lblNet))
                .addGap(8, 8, 8))
        );

        lblMsg.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblMsg.setText(" ");
        lblMsg.setForeground(new java.awt.Color(180, 40, 40));

        scrHistory.setBorder(javax.swing.BorderFactory.createTitledBorder("My recent conversions"));
        tblHistory.setRowHeight(22);

        scrHistory.setViewportView(tblHistory);

        btnFavorite.setBackground(new java.awt.Color(0, 82, 147));
        btnFavorite.setForeground(new java.awt.Color(255, 255, 255));
        btnFavorite.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFavorite.setText("Add to Favorites");
        btnFavorite.setBorderPainted(false);
        btnFavorite.setFocusPainted(false);
        btnFavorite.setOpaque(true);
        btnFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFavoriteActionPerformed(evt);
            }
        });

        btnAlert.setBackground(new java.awt.Color(0, 82, 147));
        btnAlert.setForeground(new java.awt.Color(255, 255, 255));
        btnAlert.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnAlert.setText("Set Rate Alert");
        btnAlert.setBorderPainted(false);
        btnAlert.setFocusPainted(false);
        btnAlert.setOpaque(true);
        btnAlert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertActionPerformed(evt);
            }
        });

        btnReceipt.setBackground(new java.awt.Color(0, 130, 80));
        btnReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnReceipt.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnReceipt.setText("Receipt PDF");
        btnReceipt.setBorderPainted(false);
        btnReceipt.setFocusPainted(false);
        btnReceipt.setOpaque(true);
        btnReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptActionPerformed(evt);
            }
        });

        btnFullHistory.setBackground(new java.awt.Color(90, 100, 110));
        btnFullHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnFullHistory.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnFullHistory.setText("Full History");
        btnFullHistory.setBorderPainted(false);
        btnFullHistory.setFocusPainted(false);
        btnFullHistory.setOpaque(true);
        btnFullHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFullHistoryActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(180, 40, 40));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnExit.setText("Exit");
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setOpaque(true);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFrom)
                    .addComponent(lblAmount))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFee))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTo))
                .addGap(12, 12, 12)
                .addComponent(cmbTo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(pnlResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(scrHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFavorite, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnFullHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFrom)
                    .addComponent(cmbFrom)
                    .addComponent(btnSwap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTo)
                    .addComponent(cmbTo))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAmount)
                    .addComponent(txtAmount)
                    .addComponent(chkFee))
                .addGap(14, 14, 14)
                .addComponent(btnConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlResult)
                .addGap(4, 4, 4)
                .addComponent(lblMsg)
                .addGap(8, 8, 8)
                .addComponent(scrHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFavorite, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFullHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSwapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwapActionPerformed
        Object f = cmbFrom.getSelectedItem();
        cmbFrom.setSelectedItem(cmbTo.getSelectedItem());
        cmbTo.setSelectedItem(f);
    }//GEN-LAST:event_btnSwapActionPerformed

    private void btnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertActionPerformed
        doConvert();
    }//GEN-LAST:event_btnConvertActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        doConvert();
    }//GEN-LAST:event_txtAmountActionPerformed

    private void btnFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFavoriteActionPerformed
        addFavorite();
    }//GEN-LAST:event_btnFavoriteActionPerformed

    private void btnAlertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertActionPerformed
        addAlert();
    }//GEN-LAST:event_btnAlertActionPerformed

    private void btnReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptActionPerformed
        receipt();
    }//GEN-LAST:event_btnReceiptActionPerformed

    private void btnFullHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFullHistoryActionPerformed
        CrudForms.create(Specs.conversions()).setVisible(true);
    }//GEN-LAST:event_btnFullHistoryActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void loadCurrencies() {
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            try (Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery("SELECT currency_id, CONCAT(currency_code,' - ',currency_name) FROM currencies ORDER BY currency_code")) {
                while (rs.next()) {
                    Item it = new Item(rs.getInt(1), rs.getString(2));
                    cmbFrom.addItem(it);
                    cmbTo.addItem(it);
                }
            }
            if (cmbTo.getItemCount() > 1) cmbTo.setSelectedIndex(1);
        } catch (SQLException e) {
            AppLog.error("Loading currencies", e);
            JOptionPane.showMessageDialog(this, "Could not load currencies: " + e.getMessage());
        }
    }

    private void loadHistory() {
        history.setRowCount(0);
        String sql = "SELECT v.conversion_id, b.currency_code, t.currency_code, v.amount, v.converted_amount, v.rate_used, v.conversion_date "
                + "FROM conversions v JOIN currencies b ON v.base_currency_id=b.currency_id "
                + "JOIN currencies t ON v.target_currency_id=t.currency_id WHERE v.user_id=? ORDER BY v.conversion_id DESC LIMIT 10";
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, Session.userId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String d = String.valueOf(rs.getTimestamp(7));
                        history.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getBigDecimal(4).toPlainString(), rs.getBigDecimal(5).toPlainString(),
                            rs.getBigDecimal(6).toPlainString(), d.length() > 19 ? d.substring(0, 19) : d});
                    }
                }
            }
        } catch (SQLException e) {
            AppLog.error("Loading conversion history", e);
        }
    }

    private void doConvert() {
        lblMsg.setText(" ");
        BigDecimal amount;
        try {
            amount = new BigDecimal(txtAmount.getText().trim());
            if (amount.signum() <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            lblMsg.setText("Enter a positive number for the amount.");
            return;
        }
        Item from = (Item) cmbFrom.getSelectedItem();
        Item to = (Item) cmbTo.getSelectedItem();
        if (from == null || to == null) return;

        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            RateService.Result res;
            try {
                res = RateService.compute(c, from.id, to.id, amount, chkFee.isSelected());
            } catch (IllegalStateException ex) {
                lblMsg.setText(ex.getMessage());
                AppLog.error("Rate unavailable " + from + " -> " + to, ex);
                return;
            }
            int convId;
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO conversions (user_id, base_currency_id, target_currency_id, amount, converted_amount, rate_used) VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, Session.userId);
                ps.setInt(2, from.id);
                ps.setInt(3, to.id);
                ps.setBigDecimal(4, res.amount);
                ps.setBigDecimal(5, res.converted);
                ps.setBigDecimal(6, res.rate);
                ps.executeUpdate();
                try (ResultSet k = ps.getGeneratedKeys()) {
                    k.next();
                    convId = k.getInt(1);
                }
            }
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO rate_history (base_currency_id, target_currency_id, rate) VALUES (?,?,?)")) {
                ps.setInt(1, from.id);
                ps.setInt(2, to.id);
                ps.setBigDecimal(3, res.rate);
                ps.executeUpdate();
            }
            AppLog.transaction(convId, true, "Converted " + res.amount + " " + code(from) + " to " + res.converted + " " + code(to)
                    + " (" + res.route + ", fee " + res.fee + ")");
            AppLog.audit("INSERT", "conversions", convId);

            lblRate.setText("1 " + code(from) + " = " + res.rate.toPlainString() + " " + code(to) + "   (" + res.route + ")");
            lblConverted.setText(res.converted.toPlainString() + " " + code(to));
            lblFee.setText(res.fee.toPlainString() + " " + code(to));
            lblNet.setText(res.net.toPlainString() + " " + code(to));
            last = res;
            lastFrom = from.label;
            lastTo = to.label;
            loadHistory();
            checkAlerts(c, from, to, res.rate);
        } catch (SQLException ex) {
            AppLog.error("Conversion failed", ex);
            lblMsg.setText("Conversion failed: " + ex.getMessage());
        }
    }

    private String code(Item i) { return i.label.substring(0, 3); }

    private void checkAlerts(Connection c, Item from, Item to, BigDecimal rate) throws SQLException {
        List<String> hit = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT a.alert_id, a.target_rate FROM alerts a JOIN currency_pairs p ON a.pair_id=p.pair_id "
                + "WHERE a.user_id=? AND a.triggered=FALSE AND p.base_currency_id=? AND p.target_currency_id=?")) {
            ps.setInt(1, Session.userId);
            ps.setInt(2, from.id);
            ps.setInt(3, to.id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rate.compareTo(rs.getBigDecimal(2)) >= 0) {
                        hit.add(rs.getInt(1) + ":" + rs.getBigDecimal(2).toPlainString());
                    }
                }
            }
        }
        for (String h : hit) {
            try (PreparedStatement ps = c.prepareStatement("UPDATE alerts SET triggered=TRUE WHERE alert_id=?")) {
                ps.setInt(1, Integer.parseInt(h.split(":")[0]));
                ps.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "Rate alert reached! " + code(from) + " -> " + code(to)
                    + " is now " + rate.toPlainString() + " (target " + h.split(":")[1] + ").");
        }
    }

    /** Finds the pair_id for base->target, creating the pair if it does not exist yet. */
    private int ensurePair(Connection c, int base, int target) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT pair_id FROM currency_pairs WHERE base_currency_id=? AND target_currency_id=?")) {
            ps.setInt(1, base);
            ps.setInt(2, target);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO currency_pairs (base_currency_id, target_currency_id) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, base);
            ps.setInt(2, target);
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) {
                k.next();
                return k.getInt(1);
            }
        }
    }

    private void addFavorite() {
        Item from = (Item) cmbFrom.getSelectedItem();
        Item to = (Item) cmbTo.getSelectedItem();
        if (from == null || to == null || from.equals(to)) {
            JOptionPane.showMessageDialog(this, "Choose two different currencies first.");
            return;
        }
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            int pair = ensurePair(c, from.id, to.id);
            try (PreparedStatement ps = c.prepareStatement("INSERT IGNORE INTO favorites (user_id, pair_id) VALUES (?,?)")) {
                ps.setInt(1, Session.userId);
                ps.setInt(2, pair);
                int n = ps.executeUpdate();
                if (n > 0) AppLog.audit("INSERT", "favorites", pair);
                JOptionPane.showMessageDialog(this, n > 0 ? code(from) + " -> " + code(to) + " added to favorites."
                        : "That pair is already in your favorites.");
            }
        } catch (SQLException e) {
            AppLog.error("Add favorite", e);
            JOptionPane.showMessageDialog(this, "Could not add favorite: " + e.getMessage());
        }
    }

    private void addAlert() {
        Item from = (Item) cmbFrom.getSelectedItem();
        Item to = (Item) cmbTo.getSelectedItem();
        if (from == null || to == null || from.equals(to)) {
            JOptionPane.showMessageDialog(this, "Choose two different currencies first.");
            return;
        }
        String s = JOptionPane.showInputDialog(this, "Alert me when 1 " + code(from) + " reaches (in " + code(to) + "):");
        if (s == null) return;
        BigDecimal target;
        try {
            target = new BigDecimal(s.trim());
            if (target.signum() <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a positive number.");
            return;
        }
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            int pair = ensurePair(c, from.id, to.id);
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO alerts (user_id, pair_id, target_rate) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, Session.userId);
                ps.setInt(2, pair);
                ps.setBigDecimal(3, target);
                ps.executeUpdate();
                try (ResultSet k = ps.getGeneratedKeys()) {
                    if (k.next()) AppLog.audit("INSERT", "alerts", k.getInt(1));
                }
            }
            JOptionPane.showMessageDialog(this, "Alert saved.");
        } catch (SQLException e) {
            AppLog.error("Add alert", e);
            JOptionPane.showMessageDialog(this, "Could not save alert: " + e.getMessage());
        }
    }

    private void receipt() {
        if (last == null) {
            JOptionPane.showMessageDialog(this, "Convert something first.");
            return;
        }
        List<String[]> lines = new ArrayList<>();
        lines.add(new String[]{"From", lastFrom});
        lines.add(new String[]{"To", lastTo});
        lines.add(new String[]{"Amount", last.amount.toPlainString()});
        lines.add(new String[]{"Rate", last.rate.toPlainString() + " (" + last.route + ")"});
        lines.add(new String[]{"Converted", last.converted.toPlainString()});
        lines.add(new String[]{"Fee", last.fee.toPlainString()});
        lines.add(new String[]{"You receive", last.net.toPlainString()});
        PdfUtil.receipt("Conversion Receipt", lines);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlert;
    private javax.swing.JButton btnConvert;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFavorite;
    private javax.swing.JButton btnFullHistory;
    private javax.swing.JButton btnReceipt;
    private javax.swing.JButton btnSwap;
    private javax.swing.JCheckBox chkFee;
    private javax.swing.JComboBox<Item> cmbFrom;
    private javax.swing.JComboBox<Item> cmbTo;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblConverted;
    private javax.swing.JLabel lblConvertedCaption;
    private javax.swing.JLabel lblFee;
    private javax.swing.JLabel lblFeeCaption;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblNet;
    private javax.swing.JLabel lblNetCaption;
    private javax.swing.JLabel lblRate;
    private javax.swing.JLabel lblRateCaption;
    private javax.swing.JLabel lblTo;
    private javax.swing.JLabel lblWho;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlResult;
    private javax.swing.JScrollPane scrHistory;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
}
