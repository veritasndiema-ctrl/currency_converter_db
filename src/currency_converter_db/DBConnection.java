package currency_converter_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Single place that knows how to reach MySQL.
 * Every form calls DBConnection.connect() inside try-with-resources.
 *
 * EDIT the three defaults below (or set the environment variables
 * CC_DB_URL / CC_DB_USER / CC_DB_PASSWORD) to match your MySQL setup.
 */
public class DBConnection {

    // Points to the currency_converter database (created by sql/schema.sql)
    private static final String URL = env("CC_DB_URL",
            "jdbc:mysql://localhost:3306/currency_converter?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
    private static final String USER = env("CC_DB_USER", "root");
    private static final String PASSWORD = env("CC_DB_PASSWORD", "kEITH2004!");

    static {
        try {
            // Modern Connector/J driver class (NOT the old com.mysql.jdbc.Driver)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "MySQL driver (com.mysql.cj.jdbc.Driver) not found.\n"
                    + "Add mysql-connector-j-8.x.jar to the project Libraries.",
                    "Driver missing", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String env(String key, String fallback) {
        String v = System.getenv(key);
        return (v == null || v.trim().isEmpty()) ? fallback : v;
    }

    /** Opens a new connection, or shows an error dialog and returns null. */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Database Connection Failed: " + e.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Quick main method to test the connection
    public static void main(String[] args) {
        Connection conn = connect();
        if (conn != null) {
            JOptionPane.showMessageDialog(null, "Connected to currency_converter successfully!");
        }
    }
}
