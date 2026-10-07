package currency_converter_db;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/** Holds who is logged in, and records login/logout rows in user_sessions. */
public final class Session {

    public static int userId;
    public static String username;
    public static String role;
    private static int sessionId;

    private Session() { }

    public static boolean isAdmin() { return "ADMIN".equalsIgnoreCase(role); }

    public static void start(int id, String name, String roleName) {
        userId = id;
        username = name;
        role = roleName;
        String ip = "unknown";
        try { ip = InetAddress.getLocalHost().getHostAddress(); } catch (Exception ignored) { }
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO user_sessions (user_id, ip_address) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id);
                ps.setString(2, ip);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) sessionId = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            AppLog.error("Could not open session", e);
        }
    }

    public static void end() {
        if (sessionId != 0) {
            try (Connection c = DBConnection.connect()) {
                if (c != null) {
                    try (PreparedStatement ps = c.prepareStatement(
                            "UPDATE user_sessions SET logout_time = NOW() WHERE session_id = ?")) {
                        ps.setInt(1, sessionId);
                        ps.executeUpdate();
                    }
                }
            } catch (Exception e) {
                AppLog.error("Could not close session", e);
            }
        }
        sessionId = 0;
        userId = 0;
        username = null;
        role = null;
    }
}
