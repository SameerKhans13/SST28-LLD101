import java.util.List;

public class FeeCalculatorTest {
    public static void main(String[] args) {
        BookingRequest req = new BookingRequest(RoomType.SINGLE, List.of(AddOn.MESS));
        FeeCalculator calc = new FeeCalculator(PricingPolicy.components());
        Money m = calc.calculate(req);
        String expected = "15000.00"; // 14000 + 1000
        if (!m.toString().equals(expected)) {
            throw new RuntimeException("expected " + expected + " got " + m);
        }
        System.out.println("FeeCalculatorTest passed");
    }
}