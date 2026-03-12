import java.io.PrintStream;
import java.util.*;

public class ReceiptPrinter {
    /**
     * Print a receipt to the provided stream. Using a stream makes the class
     * testable and removes the hard dependency on System.out.
     */
    public static void print(BookingRequest req, Money monthly, Money deposit, PrintStream out) {
        out.println("Room: " + req.roomType.name() + " | AddOns: " + req.addOns);
        out.println("Monthly: " + monthly);
        out.println("Deposit: " + deposit);
        out.println("TOTAL DUE NOW: " + monthly.plus(deposit));
    }
}