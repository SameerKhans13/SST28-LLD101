import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BillCalculator {
    public static class BillResult {
        public double subtotal, tax, discount, total, taxPct;
        public List<String> itemLines = new ArrayList<>();
    }

    public BillResult calculate(List<OrderLine> lines, Map<String, MenuItem> menu, 
                                List<ITaxStrategy> taxStrategies, List<IDiscountStrategy> discountStrategies) {
        BillResult res = new BillResult();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            res.subtotal += lineTotal;
            res.itemLines.add(String.format("- %s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        // aggregate tax percentages from all provided rules
        res.taxPct = 0.0;
        for (ITaxStrategy ts : taxStrategies) {
            res.taxPct += ts.getTaxPercent();
        }
        res.tax = res.subtotal * (res.taxPct / 100.0);

        // sum up discounts from each rule
        res.discount = 0.0;
        for (IDiscountStrategy ds : discountStrategies) {
            res.discount += ds.calculateDiscount(res.subtotal, lines.size());
        }

        res.total = res.subtotal + res.tax - res.discount;
        
        return res;
    }
}
