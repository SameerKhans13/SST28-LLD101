import java.util.*;

public class PricingPolicy {
    public static final Map<RoomType, Double> roomPrices = Map.of(
        RoomType.SINGLE, 14000.0,
        RoomType.DOUBLE, 15000.0,
        RoomType.TRIPLE, 12000.0,
        RoomType.DELUXE, 16000.0
    );

    public static final Map<AddOn, Double> addOnPrices = Map.of(
        AddOn.MESS, 1000.0,
        AddOn.LAUNDRY, 500.0,
        AddOn.GYM, 300.0
    );

    public static List<PricingComponent> components() {
        List<PricingComponent> list = new ArrayList<>();
        roomPrices.forEach((rt, p) -> list.add(new RoomPricingComponent(rt, p)));
        addOnPrices.forEach((a, p) -> list.add(new AddOnPricingComponent(a, p)));
        return list;
    }
}