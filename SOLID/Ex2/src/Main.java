import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        CafeteriaSystem sys = new CafeteriaSystem(new FileStore());
        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        // standard student invoice
        sys.checkout(
            List.of(new StudentTaxStrategy()),
            List.of(new StudentDiscountStrategy()),
            order
        );

        // stretch: second invoice for a staff member with additional rule
        List<OrderLine> staffOrder = List.of(
                new OrderLine("M1", 1),
                new OrderLine("C1", 1),
                new OrderLine("S1", 1) // three distinct lines triggers staff discount
        );
        sys.checkout(
            List.of(new StudentTaxStrategy()), // maybe same tax for students/staff in this example
            List.of(new StudentDiscountStrategy(), new StaffDiscountStrategy()),
            staffOrder
        );
    }
}
