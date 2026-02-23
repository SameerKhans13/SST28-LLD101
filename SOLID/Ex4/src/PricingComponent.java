public interface PricingComponent {
    /**
     * Returns the amount contributed by this component for the given request.
     */
    Money apply(BookingRequest req);
}