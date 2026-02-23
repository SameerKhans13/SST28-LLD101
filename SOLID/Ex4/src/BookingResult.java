public class BookingResult {
    public final String bookingId;
    public final Money monthly;
    public final Money deposit;

    public BookingResult(String bookingId, Money monthly, Money deposit) {
        this.bookingId = bookingId;
        this.monthly = monthly;
        this.deposit = deposit;
    }
}