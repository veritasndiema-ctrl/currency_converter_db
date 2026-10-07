package currency_converter_db;

/** Describes one column of a table so CrudForm can build the field, validate and save it. */
public class Col {

    public enum Type { TEXT, LONGTEXT, INT, DECIMAL, DATE, DATETIME, BOOL, ENUM, LOOKUP, PASSWORD }

    public final String name;      // database column name
    public final String label;     // caption on the form / report
    public final Type type;
    public boolean pk;
    public boolean readOnly;       // never written by the form (DB fills it, e.g. timestamps)
    public boolean required;
    public boolean hidden;         // not shown in the grid (e.g. password hash)
    public boolean todayDefault;   // DATE fields start as today's date
    public String[] enumValues;
    public String lookupSql;       // "SELECT id, label FROM ..." for LOOKUP columns

    private Col(String name, String label, Type type) {
        this.name = name;
        this.label = label;
        this.type = type;
    }

    public static Col pk(String name, String label) {
        Col c = new Col(name, label, Type.INT);
        c.pk = true;
        c.readOnly = true;
        return c;
    }
    public static Col text(String name, String label, boolean required) {
        Col c = new Col(name, label, Type.TEXT); c.required = required; return c;
    }
    public static Col longText(String name, String label) { return new Col(name, label, Type.LONGTEXT); }
    public static Col integer(String name, String label, boolean required) {
        Col c = new Col(name, label, Type.INT); c.required = required; return c;
    }
    public static Col decimal(String name, String label, boolean required) {
        Col c = new Col(name, label, Type.DECIMAL); c.required = required; return c;
    }
    public static Col date(String name, String label, boolean required) {
        Col c = new Col(name, label, Type.DATE); c.required = required; return c;
    }
    /** Timestamp the database fills in itself - shown but not editable. */
    public static Col stamp(String name, String label) {
        Col c = new Col(name, label, Type.DATETIME); c.readOnly = true; return c;
    }
    public static Col bool(String name, String label) { return new Col(name, label, Type.BOOL); }
    public static Col choice(String name, String label, String... values) {
        Col c = new Col(name, label, Type.ENUM); c.enumValues = values; c.required = true; return c;
    }
    public static Col lookup(String name, String label, String sql, boolean required) {
        Col c = new Col(name, label, Type.LOOKUP); c.lookupSql = sql; c.required = required; return c;
    }
    public static Col password(String name, String label) {
        Col c = new Col(name, label, Type.PASSWORD); c.hidden = true; return c;
    }
    public Col today() { this.todayDefault = true; return this; }
}
