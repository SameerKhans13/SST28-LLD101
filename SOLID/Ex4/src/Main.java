import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        BookingRequest req = new BookingRequest(RoomType.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));

        // build pricing components from policy; simple data-driven approach
        List<PricingComponent> components = new ArrayList<>(PricingPolicy.components());
        // example of an additional rule (discount) that returns negative money
        components.add(new LateFeeComponent(-500.0));
        FeeCalculator feeCalc = new FeeCalculator(components);
        IdGenerator idGen = new RandomIdGenerator();
        Money deposit = new Money(5000.00);
        BookingProcessor processor = new BookingProcessor(feeCalc, idGen, deposit);
        BookingRepository repo = new FakeBookingRepo();

        BookingResult result = processor.process(req);
        ReceiptPrinter printer = new ReceiptPrinter(System.out);
        printer.print(req, result.monthly, result.deposit);
        repo.save(result.bookingId, req, result.monthly, result.deposit);
    }
}
