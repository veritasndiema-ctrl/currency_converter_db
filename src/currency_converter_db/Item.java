package currency_converter_db;

/** One entry in a lookup combo box: database id + the text the user sees. */
public class Item {
    public final Integer id;      // null = "(none)"
    public final String label;

    public Item(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override public String toString() { return label; }

    @Override public boolean equals(Object o) {
        if (!(o instanceof Item)) return false;
        Item other = (Item) o;
        return id == null ? other.id == null : id.equals(other.id);
    }

    @Override public int hashCode() { return id == null ? 0 : id.hashCode(); }
}
