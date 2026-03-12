import java.util.*;

public class PricingComponentFactory {
    public static List<PricingComponent> componentsFor(BookingRequest req) {
        List<PricingComponent> comps = new ArrayList<>();
        comps.add(req.roomType);
        comps.addAll(req.addOns);
        return comps;
    }
}