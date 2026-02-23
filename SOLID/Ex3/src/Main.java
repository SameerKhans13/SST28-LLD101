import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);

        RuleInput config = new RuleInput();
        List<EligibilityRule> rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(config.minCgr),
                new AttendanceRule(config.minAttendance),
                new CreditsRule(config.minCredits)
        );

        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore());
        EligibilityEngineResult r = engine.evaluate(s, rules);
        ReportPrinter p = new ReportPrinter();
        p.print(s, r);
        engine.record(s.rollNo, r);

        // demonstration: adding another rule requires no engine changes
        StudentProfile s2 = new StudentProfile("23BCS1002", "Bala", 8.5, 80, 22, LegacyFlags.WARNING);
        List<EligibilityRule> rules2 = new ArrayList<>(rules);
        // rule list can be mutated or extended as needed
        EligibilityEngineResult r2 = engine.evaluate(s2, rules2);
        p.print(s2, r2);
        engine.record(s2.rollNo, r2);
    }
}
