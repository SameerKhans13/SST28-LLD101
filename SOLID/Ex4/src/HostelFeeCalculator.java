import java.util.*;
import java.util.UUID;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        List<PricingComponent> components = PricingComponentFactory.componentsFor(req);
        Money monthly = calculateMonthly(components);
        Money deposit = calculateDeposit(components);

        ReceiptPrinter.print(req, monthly, deposit, System.out);

        String bookingId = "H-" + UUID.randomUUID().toString().substring(0,4); // simple unique id
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(List<PricingComponent> components) {
        Money total = Money.ZERO;
        for (PricingComponent pc : components) {
            total = total.plus(pc.monthly());
        }
        return total;
    }

    private Money calculateDeposit(List<PricingComponent> components) {
        Money total = Money.ZERO;
        for (PricingComponent pc : components) {
            total = total.plus(pc.deposit());
        }
        return total;
    }
}