public class FakeEligibilityStore implements EligibilityStore {
    @Override
    public void save(String roll, String status) {
        // still just print for the sake of the assignment
        System.out.println("Saved evaluation for roll=" + roll);
    }
}
