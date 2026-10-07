package currency_converter_db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Everything CrudForm needs to know about one database table. */
public class TableSpec {
    public final String table;
    public final String title;
    public final List<Col> cols = new ArrayList<>();
    public String orderBy;
    public boolean adminOnly;   // regular users cannot even open it
    public boolean noSave;      // rows are created elsewhere (converter / logs) - no New/Save
    public String ownerCol;     // if set, regular users only see and manage their own rows

    public TableSpec(String table, String title, Col... columns) {
        this.table = table;
        this.title = title;
        this.cols.addAll(Arrays.asList(columns));
    }

    public Col pk() {
        for (Col c : cols) if (c.pk) return c;
        throw new IllegalStateException("No primary key in " + table);
    }

    public TableSpec order(String o) { this.orderBy = o; return this; }
    public TableSpec adminOnly() { this.adminOnly = true; return this; }
    public TableSpec noSave() { this.noSave = true; return this; }
    public TableSpec owner(String col) { this.ownerCol = col; return this; }
}
