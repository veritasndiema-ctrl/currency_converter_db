package currency_converter_db;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

/** Writes to audit_trail, error_log and transaction_log. Never throws. */
public final class AppLog {

    private AppLog() { }

    private static Integer uid() { return Session.userId == 0 ? null : Session.userId; }

    public static void audit(String action, String table, Integer recordId) {
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO audit_trail (user_id, action, table_affected, record_id) VALUES (?,?,?,?)")) {
                if (uid() == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, uid());
                ps.setString(2, action);
                ps.setString(3, table);
                if (recordId == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, recordId);
                ps.executeUpdate();
            }
        } catch (Exception ignored) { }
    }

    public static void error(String message, Throwable t) {
        String msg = message + (t != null && t.getMessage() != null ? ": " + t.getMessage() : "");
        if (msg.length() > 500) msg = msg.substring(0, 500);
        String trace = null;
        if (t != null) {
            StringWriter sw = new StringWriter();
            t.printStackTrace(new PrintWriter(sw));
            trace = sw.toString();
        }
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO error_log (user_id, error_message, stack_trace) VALUES (?,?,?)")) {
                if (uid() == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, uid());
                ps.setString(2, msg);
                ps.setString(3, trace);
                ps.executeUpdate();
            }
        } catch (Exception ignored) { }
    }

    public static void transaction(int conversionId, boolean success, String message) {
        try (Connection c = DBConnection.connect()) {
            if (c == null) return;
            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO transaction_log (conversion_id, status, message) VALUES (?,?,?)")) {
                ps.setInt(1, conversionId);
                ps.setString(2, success ? "SUCCESS" : "FAILED");
                ps.setString(3, message);
                ps.executeUpdate();
            }
        } catch (Exception ignored) { }
    }
}
