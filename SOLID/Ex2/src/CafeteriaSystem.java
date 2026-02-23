import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final IInvoiceStore store;
    private final BillCalculator calculator = new BillCalculator();
    private int invoiceSeq = 1000;

    public CafeteriaSystem(IInvoiceStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(List<ITaxStrategy> taxStrategies, List<IDiscountStrategy> discountStrategies, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        
        BillCalculator.BillResult res = calculator.calculate(lines, menu, taxStrategies, discountStrategies);

        InvoiceData data = new InvoiceData(invId, res.itemLines,
                                           res.subtotal, res.taxPct, res.tax,
                                           res.discount, res.total);

        String printable = InvoiceFormatter.formatInvoice(data);

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
