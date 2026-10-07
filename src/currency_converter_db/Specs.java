package currency_converter_db;

/** The 16 tables of the currency_converter database described for CrudForm. */
public final class Specs {

    private Specs() { }

    private static Col hiddenCol(Col c) { c.hidden = true; return c; }

    private static final String COUNTRIES = "SELECT country_id, country_name FROM countries ORDER BY country_name";
    private static final String CURRENCIES = "SELECT currency_id, CONCAT(currency_code,' - ',currency_name) FROM currencies ORDER BY currency_code";
    private static final String ROLES = "SELECT role_id, role_name FROM roles ORDER BY role_name";
    private static final String SOURCES = "SELECT source_id, source_name FROM rate_sources ORDER BY source_name";
    private static final String USERS = "SELECT user_id, username FROM users ORDER BY username";
    private static final String PAIRS = "SELECT p.pair_id, CONCAT(b.currency_code,' -> ',t.currency_code) "
            + "FROM currency_pairs p JOIN currencies b ON p.base_currency_id=b.currency_id "
            + "JOIN currencies t ON p.target_currency_id=t.currency_id ORDER BY 2";

    public static TableSpec countries() {
        return new TableSpec("countries", "Countries",
                Col.pk("country_id", "ID"),
                Col.text("country_name", "Country Name", true),
                Col.text("country_code", "ISO Code (2)", true)).order("country_name");
    }

    public static TableSpec currencies() {
        return new TableSpec("currencies", "Currencies",
                Col.pk("currency_id", "ID"),
                Col.text("currency_code", "Code (3)", true),
                Col.text("currency_name", "Currency Name", true),
                Col.text("symbol", "Symbol", false),
                Col.lookup("country_id", "Country", COUNTRIES, false)).order("currency_code");
    }

    public static TableSpec roles() {
        return new TableSpec("roles", "Roles",
                Col.pk("role_id", "ID"),
                Col.text("role_name", "Role Name", true)).adminOnly();
    }

    public static TableSpec rateSources() {
        return new TableSpec("rate_sources", "Rate Sources",
                Col.pk("source_id", "ID"),
                Col.text("source_name", "Source Name", true),
                Col.choice("source_type", "Source Type", "API", "MANUAL", "BANK"),
                Col.text("api_endpoint", "API Endpoint", false));
    }

    public static TableSpec currencyPairs() {
        return new TableSpec("currency_pairs", "Currency Pairs",
                Col.pk("pair_id", "ID"),
                Col.lookup("base_currency_id", "Base Currency", CURRENCIES, true),
                Col.lookup("target_currency_id", "Target Currency", CURRENCIES, true),
                Col.bool("is_active", "Active"));
    }

    public static TableSpec exchangeRates() {
        return new TableSpec("exchange_rates", "Exchange Rates",
                Col.pk("rate_id", "ID"),
                Col.lookup("base_currency_id", "Base Currency", CURRENCIES, true),
                Col.lookup("target_currency_id", "Target Currency", CURRENCIES, true),
                Col.lookup("source_id", "Source", SOURCES, false),
                Col.decimal("rate", "Rate", true),
                Col.date("rate_date", "Rate Date", true).today()).order("rate_date DESC, rate_id DESC");
    }

    public static TableSpec rateHistory() {
        return new TableSpec("rate_history", "Rate History",
                Col.pk("history_id", "ID"),
                Col.lookup("base_currency_id", "Base Currency", CURRENCIES, true),
                Col.lookup("target_currency_id", "Target Currency", CURRENCIES, true),
                Col.decimal("rate", "Rate", true),
                Col.stamp("recorded_at", "Recorded At")).order("recorded_at DESC, history_id DESC");
    }

    public static TableSpec feeStructures() {
        return new TableSpec("fee_structures", "Fee Structures",
                Col.pk("fee_id", "ID"),
                Col.lookup("source_id", "Rate Source", SOURCES, true),
                Col.decimal("fee_percentage", "Fee %", true),
                Col.decimal("min_fee", "Minimum Fee", false),
                Col.decimal("max_fee", "Maximum Fee", false));
    }

    public static TableSpec users() {
        return new TableSpec("users", "Users",
                Col.pk("user_id", "ID"),
                Col.text("username", "Username", true),
                Col.text("email", "Email", true),
                Col.password("password_hash", "Password *"),
                Col.lookup("role_id", "Role", ROLES, true),
                Col.stamp("created_at", "Created At")).adminOnly();
    }

    public static TableSpec userSessions() {
        return new TableSpec("user_sessions", "User Sessions",
                Col.pk("session_id", "ID"),
                Col.lookup("user_id", "User", USERS, true),
                Col.stamp("login_time", "Login Time"),
                Col.stamp("logout_time", "Logout Time"),
                Col.text("ip_address", "IP Address", false)).adminOnly().noSave().order("session_id DESC");
    }

    public static TableSpec conversions() {
        Col amount = Col.decimal("amount", "Amount", true); amount.readOnly = true;
        Col conv = Col.decimal("converted_amount", "Converted Amount", true); conv.readOnly = true;
        Col rate = Col.decimal("rate_used", "Rate Used", true); rate.readOnly = true;
        return new TableSpec("conversions", "Conversion History",
                Col.pk("conversion_id", "ID"),
                Col.lookup("user_id", "User", USERS, false),
                Col.lookup("base_currency_id", "From", CURRENCIES, true),
                Col.lookup("target_currency_id", "To", CURRENCIES, true),
                amount, conv, rate,
                Col.stamp("conversion_date", "Date")).noSave().owner("user_id").order("conversion_id DESC");
    }

    public static TableSpec favorites() {
        return new TableSpec("favorites", "Favorite Pairs",
                Col.pk("favorite_id", "ID"),
                Col.lookup("user_id", "User", USERS, true),
                Col.lookup("pair_id", "Currency Pair", PAIRS, true),
                Col.stamp("added_at", "Added At")).owner("user_id");
    }

    public static TableSpec alerts() {
        return new TableSpec("alerts", "Rate Alerts",
                Col.pk("alert_id", "ID"),
                Col.lookup("user_id", "User", USERS, true),
                Col.lookup("pair_id", "Currency Pair", PAIRS, true),
                Col.decimal("target_rate", "Target Rate", true),
                Col.bool("triggered", "Triggered"),
                Col.stamp("created_at", "Created At")).owner("user_id");
    }

    public static TableSpec transactionLog() {
        return new TableSpec("transaction_log", "Transaction Log",
                Col.pk("log_id", "ID"),
                Col.integer("conversion_id", "Conversion ID", true),
                Col.choice("status", "Status", "SUCCESS", "FAILED"),
                Col.text("message", "Message", false),
                Col.stamp("logged_at", "Logged At")).adminOnly().noSave().order("log_id DESC");
    }

    public static TableSpec auditTrail() {
        return new TableSpec("audit_trail", "Audit Trail",
                Col.pk("audit_id", "ID"),
                Col.lookup("user_id", "User", USERS, false),
                Col.text("action", "Action", true),
                Col.text("table_affected", "Table", true),
                Col.integer("record_id", "Record ID", false),
                Col.stamp("changed_at", "Changed At")).adminOnly().noSave().order("audit_id DESC");
    }

    public static TableSpec errorLog() {
        return new TableSpec("error_log", "Error Log",
                Col.pk("error_id", "ID"),
                Col.lookup("user_id", "User", USERS, false),
                Col.text("error_message", "Error Message", true),
                hiddenCol(Col.longText("stack_trace", "Stack Trace")),
                Col.stamp("occurred_at", "Occurred At")).adminOnly().noSave().order("error_id DESC");
    }
}
