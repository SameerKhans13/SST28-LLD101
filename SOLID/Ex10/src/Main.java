public class Main {
    public static void main(String[] args) {
        System.out.println("=== Transport Booking ===");
        TripRequest req = new TripRequest("23BCS1010", new GeoPoint(12.97, 77.59), new GeoPoint(12.93, 77.62));

        // concrete implementations; easily swapped for tests
        IDistanceCalculator dist = new DistanceCalculator();
        IDriverAllocator alloc = new DriverAllocator();
        IPaymentGateway pay = new PaymentGateway();
        IFareCalculator fareCalc = new DefaultFareCalculator();

        TransportBookingService svc = new TransportBookingService(dist, alloc, pay, fareCalc);
        svc.book(req);
    }
}
