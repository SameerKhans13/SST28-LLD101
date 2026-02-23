import java.util.List;

public class InvoiceData {
    public final String id;
    public final List<String> itemLines;
    public final double subtotal;
    public final double taxPct;   // aggregated percent
    public final double tax;
    public final double discount;
    public final double total;

    public InvoiceData(String id, List<String> itemLines,
                       double subtotal, double taxPct, double tax,
                       double discount, double total) {
        this.id = id;
        this.itemLines = itemLines;
        this.subtotal = subtotal;
        this.taxPct = taxPct;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}