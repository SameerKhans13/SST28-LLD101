public class AddOnPricingComponent implements PricingComponent {
    private final AddOn addOn;
    private final double price;

    public AddOnPricingComponent(AddOn addOn, double price) {
        this.addOn = addOn;
        this.price = price;
    }

    @Override
    public Money apply(BookingRequest req) {
        if (req.getAddOns().contains(addOn)) {
            return new Money(price);
        }
        return new Money(0.0);
    }
}