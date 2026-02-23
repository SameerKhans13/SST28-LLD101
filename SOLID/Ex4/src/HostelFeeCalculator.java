import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base;
        // avoid direct reference to LegacyRoomTypes constants which may be missing;
        // convert whatever roomType is to a string and switch on that
        // use accessor instead of direct field access; field may be non-public
        Object roomType = req.getRoomType();
        String type = roomType == null ? "" : roomType.toString();
        switch (type) {
            case "SINGLE" -> base = 14000.0;
            case "DOUBLE" -> base = 15000.0;
            case "TRIPLE" -> base = 12000.0;
            default -> base = 16000.0;
        }

        double add = 0.0;
        // use accessor instead of direct field access; field may be non-public
        List<AddOn> addons = req.getAddOns();
        if (addons != null) {
            for (AddOn a : addons) {
                if (a == AddOn.MESS) add += 1000.0;
                else if (a == AddOn.LAUNDRY) add += 500.0;
                else if (a == AddOn.GYM) add += 300.0;
            }
        }

        return new Money(base + add);
    }
}
