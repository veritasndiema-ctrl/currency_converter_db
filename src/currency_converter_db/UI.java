package currency_converter_db;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Small styling helpers so every form looks the same. */
public final class UI {

    public static final Color BLUE = new Color(0, 82, 147);
    public static final Color GREEN = new Color(0, 130, 80);
    public static final Color RED = new Color(180, 40, 40);
    public static final Color GREY = new Color(90, 100, 110);
    public static final Color BG = new Color(244, 247, 250);

    private UI() { }

    public static JButton button(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(Math.max(95, text.length() * 9 + 30), 34));
        return b;
    }

    public static JPanel header(String title, String subtitle) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BLUE);
        p.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        JLabel t = new JLabel(title);
        t.setForeground(Color.WHITE);
        t.setFont(new Font("SansSerif", Font.BOLD, 22));
        p.add(t, BorderLayout.WEST);
        if (subtitle != null) {
            JLabel s = new JLabel(subtitle, SwingConstants.RIGHT);
            s.setForeground(new Color(200, 220, 240));
            s.setFont(new Font("SansSerif", Font.PLAIN, 13));
            p.add(s, BorderLayout.EAST);
        }
        return p;
    }

    public static JPanel buttonBar() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
        p.setBackground(BG);
        return p;
    }

    public static void center(JFrame f) {
        Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(Math.max(0, (s.width - f.getWidth()) / 2), Math.max(0, (s.height - f.getHeight()) / 2));
    }

    /** Gives a menu group its coloured, titled frame (the NetBeans form only sets a plain title). */
    public static void styleGroup(JPanel p, Color color, String title) {
        javax.swing.border.TitledBorder tb = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2), "  " + title + "  ");
        tb.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        tb.setTitleColor(color);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0),
                BorderFactory.createCompoundBorder(tb, BorderFactory.createEmptyBorder(4, 8, 6, 8))));
    }
}
