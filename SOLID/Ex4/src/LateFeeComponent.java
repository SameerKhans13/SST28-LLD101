public class LateFeeComponent implements PricingComponent {
    private final double amount;

    public LateFeeComponent(double amount) {
        // positive amount means extra charge, negative means discount
        this.amount = amount;
    }

    @Override
    public Money apply(BookingRequest req) {
        // always applicable for demo; real logic could inspect req
        return new Money(amount);
    }
}