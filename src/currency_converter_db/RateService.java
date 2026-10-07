package currency_converter_db;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Business rules of the converter: finding a rate and working out the fee. */
public final class RateService {

    private RateService() { }

    public static class Rate {
        public final BigDecimal value;
        public final Integer sourceId;
        public final String route;       // "direct", "inverse" or "via USD"
        Rate(BigDecimal value, Integer sourceId, String route) {
            this.value = value; this.sourceId = sourceId; this.route = route;
        }
    }

    public static class Result {
        public BigDecimal amount, rate, converted, fee, net;
        public String route;
    }

    /** Latest stored rate base->target; tries direct, then inverse, then a cross via USD. Null if none. */
    public static Rate find(Connection c, int base, int target) throws SQLException {
        if (base == target) return new Rate(BigDecimal.ONE, null, "same currency");
        Rate r = lookup(c, base, target);
        if (r != null) return r;
        Rate inv = lookup(c, target, base);
        if (inv != null) {
            return new Rate(BigDecimal.ONE.divide(inv.value, 10, RoundingMode.HALF_UP), inv.sourceId, "inverse");
        }
        Integer usd = currencyId(c, "USD");
        if (usd != null && usd != base && usd != target) {
            Rate a = direct(c, base, usd);
            Rate b = direct(c, usd, target);
            if (a != null && b != null) {
                return new Rate(a.value.multiply(b.value).setScale(10, RoundingMode.HALF_UP), a.sourceId, "via USD");
            }
        }
        return null;
    }

    private static Rate direct(Connection c, int base, int target) throws SQLException {
        Rate r = lookup(c, base, target);
        if (r != null) return r;
        Rate inv = lookup(c, target, base);
        return inv == null ? null
                : new Rate(BigDecimal.ONE.divide(inv.value, 10, RoundingMode.HALF_UP), inv.sourceId, "inverse");
    }

    private static Rate lookup(Connection c, int base, int target) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT rate, source_id FROM exchange_rates WHERE base_currency_id=? AND target_currency_id=? "
                + "ORDER BY rate_date DESC, rate_id DESC LIMIT 1")) {
            ps.setInt(1, base);
            ps.setInt(2, target);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                int s = rs.getInt(2);
                return new Rate(rs.getBigDecimal(1), rs.wasNull() ? null : s, "direct");
            }
        }
    }

    public static Integer currencyId(Connection c, String code) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT currency_id FROM currencies WHERE currency_code=?")) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    /** Fee for a converted amount: percentage of it, clamped between min_fee and max_fee. */
    public static BigDecimal fee(Connection c, Integer sourceId, BigDecimal converted) throws SQLException {
        if (sourceId == null) return BigDecimal.ZERO;
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT fee_percentage, min_fee, max_fee FROM fee_structures WHERE source_id=? ORDER BY fee_id LIMIT 1")) {
            ps.setInt(1, sourceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return BigDecimal.ZERO;
                BigDecimal fee = converted.multiply(rs.getBigDecimal(1)).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                BigDecimal min = rs.getBigDecimal(2), max = rs.getBigDecimal(3);
                if (min != null && fee.compareTo(min) < 0) fee = min;
                if (max != null && fee.compareTo(max) > 0) fee = max;
                return fee.setScale(2, RoundingMode.HALF_UP);
            }
        }
    }

    /** Work out a conversion without saving it. Throws IllegalStateException if no rate exists. */
    public static Result compute(Connection c, int base, int target, BigDecimal amount, boolean withFee) throws SQLException {
        Rate r = find(c, base, target);
        if (r == null) throw new IllegalStateException("No exchange rate is available for this currency pair.");
        Result res = new Result();
        res.amount = amount;
        res.rate = r.value.setScale(6, RoundingMode.HALF_UP);
        res.route = r.route;
        res.converted = amount.multiply(r.value).setScale(2, RoundingMode.HALF_UP);
        res.fee = withFee ? fee(c, r.sourceId, res.converted) : BigDecimal.ZERO.setScale(2);
        res.net = res.converted.subtract(res.fee);
        return res;
    }
}
