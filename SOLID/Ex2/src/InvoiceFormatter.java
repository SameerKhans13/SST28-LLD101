import java.util.List;

public class InvoiceFormatter {
    /**
     * Formats the invoice data into a printable string. Magic values such as
     * percentages and line ordering are preserved to maintain existing output.
     */
    public static String formatInvoice(InvoiceData data) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(data.id).append("\n");
        for (String line : data.itemLines) {
            out.append(line).append("\n");
        }
        out.append(String.format("Subtotal: %.2f\n", data.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", data.taxPct, data.tax));
        out.append(String.format("Discount: -%.2f\n", data.discount));
        out.append(String.format("TOTAL: %.2f\n", data.total));
        return out.toString();
    }
}
