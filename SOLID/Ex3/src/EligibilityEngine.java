import java.util.*;

public class EligibilityEngine {
    private final EligibilityStore store;

    public EligibilityEngine(EligibilityStore store) {
        this.store = store;
    }

    /**
     * Runs evaluation using the provided rules, prints via caller, and persists.
     * Separation keeps the engine testable and rules open for extension.
     */
    public EligibilityEngineResult evaluate(StudentProfile s, List<EligibilityRule> rules) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule r : rules) {
            Optional<String> maybe = r.evaluate(s);
            maybe.ifPresent(reasons::add);
        }
        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }

    public void record(String roll, EligibilityEngineResult result) {
        store.save(roll, result.status);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
