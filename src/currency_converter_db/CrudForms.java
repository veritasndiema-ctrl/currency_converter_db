package currency_converter_db;

import javax.swing.JFrame;

/** Opens the NetBeans form that belongs to a table (one form per table). */
public final class CrudForms {

    private CrudForms() { }

    public static JFrame create(TableSpec spec) {
        switch (spec.table) {
            case "countries": return new CountriesForm();
            case "currencies": return new CurrenciesForm();
            case "roles": return new RolesForm();
            case "rate_sources": return new RateSourcesForm();
            case "currency_pairs": return new CurrencyPairsForm();
            case "exchange_rates": return new ExchangeRatesForm();
            case "rate_history": return new RateHistoryForm();
            case "fee_structures": return new FeeStructuresForm();
            case "users": return new UsersForm();
            case "user_sessions": return new UserSessionsForm();
            case "conversions": return new ConversionsForm();
            case "favorites": return new FavoritesForm();
            case "alerts": return new AlertsForm();
            case "transaction_log": return new TransactionLogForm();
            case "audit_trail": return new AuditTrailForm();
            case "error_log": return new ErrorLogForm();
            default: throw new IllegalArgumentException("No form for table " + spec.table);
        }
    }
}
