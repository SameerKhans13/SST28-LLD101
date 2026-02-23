import java.util.Optional;

public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public Optional<String> evaluate(StudentProfile s) {
        if (s.cgr < minCgr) {
            return Optional.of(String.format("CGR below %.1f", minCgr));
        }
        return Optional.empty();
    }
}