public class FakeBookingRepo implements BookingRepository {
    @Override
    public void save(String id, BookingRequest req, Money monthly, Money deposit) {
        // still just print for the exercise
        System.out.println("Saved booking: " + id);
    }
}
