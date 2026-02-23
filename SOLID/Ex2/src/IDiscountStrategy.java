import java.util.List;

public interface IDiscountStrategy {
    double calculateDiscount(double subtotal, int distinctLines);
}
