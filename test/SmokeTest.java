import currency_converter_db.*;
import java.math.BigDecimal;
import java.sql.*;

public class SmokeTest {
    static void check(boolean ok, String what) { System.out.println((ok ? "PASS " : "FAIL ") + what); if (!ok) System.exit(1); }
    public static void main(String[] a) throws Exception {
        try (Connection c = DBConnection.connect()) {
            check(c != null, "connects with com.mysql.cj.jdbc.Driver");
            ResultSet rs = c.createStatement().executeQuery("select password_hash from users where username='admin'");
            rs.next();
            check(Security.verify("Admin@1234", rs.getString(1)), "admin password verifies");
            check(!Security.verify("wrong", rs.getString(1)), "wrong password rejected");
            int usd = RateService.currencyId(c, "USD"), ugx = RateService.currencyId(c, "UGX"),
                kes = RateService.currencyId(c, "KES"), gbp = RateService.currencyId(c, "GBP");
            RateService.Result r = RateService.compute(c, usd, ugx, new BigDecimal("100"), true);
            System.out.println(r.amount + " USD -> " + r.converted + " UGX rate " + r.rate + " fee " + r.fee + " net " + r.net + " " + r.route);
            check(r.converted.compareTo(new BigDecimal("370000.00")) == 0, "direct conversion");
            check(r.fee.compareTo(new BigDecimal("25.00")) == 0, "fee clamped to max 25.00");
            r = RateService.compute(c, ugx, usd, new BigDecimal("37000"), false);
            check(r.converted.compareTo(new BigDecimal("9.99")) == 0, "UGX->USD uses stored 0.00027 rate: " + r.converted);
            r = RateService.compute(c, kes, usd, new BigDecimal("1295"), false);
            check(r.route.equals("inverse") && r.converted.compareTo(new BigDecimal("10.00")) == 0, "inverse rate KES->USD = " + r.converted);
            r = RateService.compute(c, kes, gbp, new BigDecimal("1295"), false);
            check(r.route.equals("via USD"), "cross rate via USD KES->GBP = " + r.converted);
            boolean threw = false;
            try { RateService.compute(c, RateService.currencyId(c, "JPY"), RateService.currencyId(c, "RWF"), BigDecimal.ONE, false); }
            catch (IllegalStateException e) { threw = true; }
            check(!threw, "JPY->RWF resolves via USD");
        }
        System.out.println("ALL PASSED");
    }
}
