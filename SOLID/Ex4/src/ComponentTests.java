import java.util.List;

public class ComponentTests {
    public static void main(String[] args) {
        BookingRequest req = new BookingRequest(RoomType.DOUBLE, List.of(AddOn.GYM));
        PricingComponent room = new RoomPricingComponent(RoomType.DOUBLE, 15000);
        PricingComponent gym = new AddOnPricingComponent(AddOn.GYM, 300);
        if (!room.apply(req).toString().equals("15000.00")) {
            throw new RuntimeException("RoomPricingComponent failed");
        }
        if (!gym.apply(req).toString().equals("300.00")) {
            throw new RuntimeException("AddOnPricingComponent failed");
        }
        System.out.println("ComponentTests passed");
    }
}