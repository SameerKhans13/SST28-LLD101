import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public Optional<String> evaluate(StudentProfile s) {
        if (s.earnedCredits < minCredits) {
            return Optional.of("credits below " + minCredits);
        }
        return Optional.empty();
    }
}