import java.util.List;

public class FeeCalculator {
    private final List<PricingComponent> components;

    public FeeCalculator(List<PricingComponent> components) {
        this.components = components;
    }

    /**
     * Calculates the monthly charge by summing the contributions from all
     * registered pricing components. Each component may choose to return zero
     * if it does not apply to the request.
     */
    public Money calculate(BookingRequest req) {
        Money total = new Money(0.0);
        for (PricingComponent pc : components) {
            total = total.plus(pc.apply(req));
        }
        return total;
    }
}