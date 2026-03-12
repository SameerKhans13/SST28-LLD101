public enum AddOn implements PricingComponent {
    MESS(1000.0),
    LAUNDRY(500.0),
    GYM(300.0);

    private final Money monthly;

    AddOn(double monthly) {
        this.monthly = new Money(monthly);
    }

    @Override
    public Money monthly() {
        return monthly;
    }

    @Override
    public Money deposit() {
        return Money.ZERO;
    }
}