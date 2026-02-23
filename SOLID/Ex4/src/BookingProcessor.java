
public class BookingProcessor {
    private final FeeCalculator feeCalculator;
    private final IdGenerator idGenerator;
    private final Money depositAmount;

    public BookingProcessor(FeeCalculator feeCalculator,
                             IdGenerator idGenerator,
                             Money depositAmount) {
        this.feeCalculator = feeCalculator;
        this.idGenerator = idGenerator;
        this.depositAmount = depositAmount;
    }

    /**
     * Computes fees and returns a result; side effects (printing, saving) are
     * the responsibility of the caller.
     */
    public BookingResult process(BookingRequest req) {
        Money monthly = feeCalculator.calculate(req);
        String bookingId = idGenerator.generate();
        return new BookingResult(bookingId, monthly, depositAmount);
    }
}
