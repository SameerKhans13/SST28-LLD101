public class RoomPricingComponent implements PricingComponent {
    private final RoomType roomType;
    private final double price;

    public RoomPricingComponent(RoomType roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    @Override
    public Money apply(BookingRequest req) {
        if (req.getRoomType() == roomType) {
            return new Money(price);
        }
        return new Money(0.0);
    }
}