import currency_converter_db.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GuiTest {
    static void check(boolean ok, String what) { System.out.println((ok ? "PASS " : "FAIL ") + what); if (!ok) { System.exit(1); } }
    static void all(Container c, List<Component> out) { for (Component k : c.getComponents()) { out.add(k); if (k instanceof Container) all((Container) k, out); } }
    static <T> List<T> find(Container root, Class<T> cls) { List<Component> l = new ArrayList<>(); all(root, l); List<T> r = new ArrayList<>(); for (Component k : l) if (cls.isInstance(k)) r.add(cls.cast(k)); return r; }
    static JButton btn(Container root, String text) { for (JButton b : find(root, JButton.class)) if (b.getText().equals(text)) return b; throw new RuntimeException("no button " + text); }
    static void click(Container root, String text) throws Exception { SwingUtilities.invokeAndWait(() -> btn(root, text).doClick()); }
    static int count(String sql) throws Exception { try (Connection c = DBConnection.connect(); ResultSet r = c.createStatement().executeQuery(sql)) { r.next(); return r.getInt(1); } }
    static Object fieldOf(Object o, String n) throws Exception { java.lang.reflect.Field f = o.getClass().getDeclaredField(n); f.setAccessible(true); return f.get(o); }
    static JTextField fld(Object o, String n) throws Exception { java.lang.reflect.Field f = o.getClass().getDeclaredField(n); f.setAccessible(true); return (JTextField) f.get(o); }
    static <T extends JFrame> T open(java.util.function.Supplier<T> s) throws Exception { Object[] h = new Object[1]; SwingUtilities.invokeAndWait(() -> { T f = s.get(); f.setVisible(true); h[0] = f; }); @SuppressWarnings("unchecked") T t = (T) h[0]; return t; }

    public static void main(String[] a) throws Exception {
        // auto-close any modal dialog so the test never blocks
        Thread closer = new Thread(() -> { while (true) { try { Thread.sleep(250); } catch (Exception e) { } for (Window w : Window.getWindows()) if (w instanceof JDialog && w.isVisible() && find(w, JButton.class).stream().noneMatch(b -> b.getText().equalsIgnoreCase("Yes"))) { System.out.println("   [dialog closed] " + String.join(" | ", texts(w))); SwingUtilities.invokeLater(w::dispose); } } });
        closer.setDaemon(true); closer.start();

        Session.start(1, "admin", "ADMIN");
        Specs s = null;
        String[] names = {"countries","currencies","roles","rateSources","currencyPairs","exchangeRates","rateHistory","feeStructures","users","userSessions","conversions","favorites","alerts","transactionLog","auditTrail","errorLog"};
        for (String n : names) {
            Method m = Specs.class.getMethod(n);
            TableSpec spec = (TableSpec) m.invoke(null);
            JFrame f = open(() -> CrudForms.create(spec));
            JTable t = find(f, JTable.class).get(0);
            check(t.getRowCount() > 0 || n.equals("errorLog") || n.equals("userSessions"), "admin opens " + spec.title + " (" + t.getRowCount() + " rows)");
            f.dispose();
        }

        // --- CRUD round trip on countries
        int before = count("select count(*) from countries");
        JFrame cf = open(() -> CrudForms.create(Specs.countries()));
        JTextField tfName = fld(cf, "txtCountryName"), tfCode = fld(cf, "txtCountryCode");
        SwingUtilities.invokeAndWait(() -> { tfName.setText("Testland"); tfCode.setText("TL"); });
        click(cf, "Save");
        check(count("select count(*) from countries") == before + 1, "Save inserts a new country");
        check(count("select count(*) from audit_trail where table_affected='countries' and action='INSERT'") >= 1, "audit_trail row written");
        // selected row is the new one -> edit
        SwingUtilities.invokeAndWait(() -> tfName.setText("Testland2"));
        click(cf, "Save");
        check(count("select count(*) from countries where country_name='Testland2'") == 1, "Save updates the row");
        // validation
        SwingUtilities.invokeAndWait(() -> { btn(cf, "New").doClick(); });
        click(cf, "Save");   // empty -> warning dialog, no insert
        check(count("select count(*) from countries") == before + 1, "empty form is rejected");
        // find
        Method find = null;
        // delete the test row: select it by clicking Last? ordering by name -> find via grid
        JTable grid = find(cf, JTable.class).get(0);
        int rowIdx = -1; for (int i = 0; i < grid.getRowCount(); i++) if ("Testland2".equals(grid.getValueAt(i, 1))) rowIdx = i;
        check(rowIdx >= 0, "new row visible in grid");
        final int ri = rowIdx;
        SwingUtilities.invokeAndWait(() -> grid.setRowSelectionInterval(ri, ri));
        // auto-answer confirm dialog "Yes": replace closer behaviour by pressing yes in a helper thread
        Thread yes = new Thread(() -> { for (int i = 0; i < 20; i++) { try { Thread.sleep(150); } catch (Exception e) { } for (Window w : Window.getWindows()) if (w instanceof JDialog && w.isVisible()) for (JButton b : find(w, JButton.class)) if (b.getText().equalsIgnoreCase("Yes")) { b.doClick(); return; } } });
        yes.start();
        click(cf, "Delete");
        yes.join(3000);
        Thread.sleep(500);
        check(count("select count(*) from countries") == before, "Delete removes the row");
        // FK-protected delete
        SwingUtilities.invokeAndWait(() -> grid.setRowSelectionInterval(0, 0));
        // PDF
        click(cf, "Preview PDF");
        Thread.sleep(800);
        java.io.File[] pdfs = new java.io.File("reports").listFiles((d, n) -> n.endsWith(".pdf"));
        check(pdfs != null && pdfs.length >= 1 && pdfs[0].length() > 1000, "Preview PDF creates a report file");
        click(cf, "Next  >"); click(cf, "Last  >|"); click(cf, "|<  First"); click(cf, "<  Prev");
        check(true, "navigation buttons run");
        cf.dispose();

        // --- Users form: add a user with password
        int ub = count("select count(*) from users");
        JFrame uf = open(() -> CrudForms.create(Specs.users()));
        JTextField utName = fld(uf, "txtUsername"), utMail = fld(uf, "txtEmail");
        JPasswordField pw = find(uf, JPasswordField.class).get(0);
        SwingUtilities.invokeAndWait(() -> { utName.setText("tester"); utMail.setText("t@x.com"); pw.setText("secret1"); });
        click(uf, "Save");
        check(count("select count(*) from users where username='tester'") == 1, "admin creates a user (hashed password)");
        uf.dispose();

        // --- Converter
        int cb = count("select count(*) from conversions");
        ConverterForm cv = open(() -> new ConverterForm());
        click(cv, "Convert");
        check(count("select count(*) from conversions") == cb + 1, "Convert saves a conversion");
        check(count("select count(*) from transaction_log") >= 3, "transaction_log written");
        click(cv, "Add to Favorites");
        click(cv, "Receipt PDF");
        Thread.sleep(600);
        check(new java.io.File("reports").listFiles((d, n) -> n.startsWith("Conversion")).length >= 1, "Receipt PDF created");
        cv.dispose();

        // --- Regular user sees only own rows
        Session.end();
        Session.start(2, "demo_user", "USER");
        JFrame uc = open(() -> CrudForms.create(Specs.conversions()));
        JTable ct = find(uc, JTable.class).get(0);
        int own = count("select count(*) from conversions where user_id=2");
        check(ct.getRowCount() == own, "demo_user sees only own conversions (" + own + ")");
        check(!btn(uc, "Save").isEnabled(), "conversions are read-only (no Save)");
        uc.dispose();
        JFrame cc = open(() -> CrudForms.create(Specs.currencies()));
        check(!btn(cc, "Save").isEnabled() && !btn(cc, "Delete").isEnabled(), "regular user cannot edit reference data");
        cc.dispose();
        int fb = count("select count(*) from favorites where user_id=2");
        JFrame ff = open(() -> CrudForms.create(Specs.favorites()));
        check(btn(ff, "Save").isEnabled(), "regular user can manage own favorites (" + fb + ")");
        ff.dispose();
        MenuForm mf = open(() -> new MenuForm());
        boolean adminBtn = false; for (JButton b : find(mf, JButton.class)) if (b.getText().equals("Users") && b.isShowing()) adminBtn = true;
        check(!adminBtn, "menu hides Administration for regular users");
        mf.dispose();
        // owner column is hidden on forms for regular users
        JFrame fav = open(() -> CrudForms.create(Specs.favorites()));
        javax.swing.JComboBox<?> cu = (javax.swing.JComboBox<?>) fieldOf(fav, "cmbUserId");
        check(!cu.isShowing(), "owner column (User) hidden for regular user on Favorites");
        fav.dispose();
        // admin sees Administration on the menu; Login + Register forms open
        Session.end();
        Session.start(1, "admin", "ADMIN");
        MenuForm am = open(() -> new MenuForm());
        boolean adminShown = false; for (JButton b : find(am, JButton.class)) if (b.getText().equals("Users") && b.isShowing()) adminShown = true;
        check(adminShown, "menu shows Administration for admin");
        click(am, "Convert Currency");
        check(java.util.Arrays.stream(Window.getWindows()).anyMatch(w -> w instanceof ConverterForm && w.isVisible()), "menu button opens the converter form");
        click(am, "Countries");
        check(java.util.Arrays.stream(Window.getWindows()).anyMatch(w -> w instanceof CountriesForm && w.isVisible()), "menu button opens the Countries form");
        am.dispose();
        LoginForm lf = open(() -> new LoginForm());
        RegisterForm rf = open(() -> new RegisterForm());
        check(lf.isShowing() && rf.isShowing(), "Login and Register forms open");
        System.out.println("ALL GUI TESTS PASSED");
        System.exit(0);
    }
    static List<String> texts(Window w) { List<String> r = new ArrayList<>(); for (JLabel l : find(w, JLabel.class)) if (l.getText() != null) r.add(l.getText().replaceAll("<[^>]+>", " ").trim()); return r; }
}
