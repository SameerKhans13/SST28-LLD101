public class StaffDiscountStrategy implements IDiscountStrategy {
    @Override
    public double calculateDiscount(double subtotal, int distinctLines) {
        // staff get 15 off for 3 or more distinct lines, otherwise 5
        if (distinctLines >= 3) return 15.0;
        return 5.0;
    }
}