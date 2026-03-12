public enum RoomType implements PricingComponent {
    SINGLE(14000.0, 5000.0),
    DOUBLE(15000.0, 5000.0),
    TRIPLE(12000.0, 5000.0),
    DELUXE(16000.0, 5000.0);

    private final Money monthly;
    private final Money deposit;

    RoomType(double monthly, double deposit) {
        this.monthly = new Money(monthly);
        this.deposit = new Money(deposit);
    }

    @Override
    public Money monthly() {
        return monthly;
    }

    @Override
    public Money deposit() {
        return deposit;
    }


}