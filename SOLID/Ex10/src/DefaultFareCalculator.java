public class DefaultFareCalculator implements IFareCalculator {
    @Override
    public double computeFare(double km) {
        double fare = 50.0 + km * 6.6666666667; // business rule
        // round to two decimals
        fare = Math.round(fare * 100.0) / 100.0;
        return fare;
    }
}