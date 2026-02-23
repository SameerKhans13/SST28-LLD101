import java.util.List;

public class BookingProcessorTest {
    public static void main(String[] args) {
        BookingRequest req = new BookingRequest(RoomType.TRIPLE, List.of());
        List<PricingComponent> comps = PricingPolicy.components();
        FeeCalculator feeCalc = new FeeCalculator(comps);
        IdGenerator idGen = new RandomIdGenerator(123);
        Money deposit = new Money(5000);
        BookingProcessor proc = new BookingProcessor(feeCalc, idGen, deposit);
        BookingResult res = proc.process(req);
        if (!res.bookingId.startsWith("H-")) throw new RuntimeException("bad id");
        if (!res.monthly.toString().equals("12000.00")) throw new RuntimeException("bad monthly");
        System.out.println("BookingProcessorTest passed");
    }
}