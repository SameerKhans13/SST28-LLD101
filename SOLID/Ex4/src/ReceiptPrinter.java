import java.io.PrintStream;

public class ReceiptPrinter {
    private final PrintStream out;

    public ReceiptPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(BookingRequest req, Money monthly, Money deposit) {
        out.println("Room: " + req.getRoomType() + " | AddOns: " + req.getAddOns());
        out.println("Monthly: " + monthly);
        out.println("Deposit: " + deposit);
        out.println("TOTAL DUE NOW: " + monthly.plus(deposit));
    }
}
