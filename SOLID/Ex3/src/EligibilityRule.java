import java.util.Optional;

public interface EligibilityRule {
    /**
     * Evaluate the profile and return an Optional reason if the rule fails.
     * The order in which rules are applied is the order of the list supplied to
     * the engine; the caller is responsible for maintaining any required order.
     */
    Optional<String> evaluate(StudentProfile s);
}