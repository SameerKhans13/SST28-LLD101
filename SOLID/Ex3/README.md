# Ex3 — OCP: Placement Eligibility Rules Engine

## 1. Context
Placement eligibility depends on multiple rules: CGR threshold, attendance percentage, earned credits, and disciplinary flags. More rules will be added later.

## 2. Current behavior
- Evaluates a `StudentProfile`
- Returns ELIGIBLE or NOT_ELIGIBLE with reasons
- Prints a report

## 3. What’s wrong with the design (at least 5 issues)
1. `EligibilityEngine.evaluate` is a long if/else chain with mixed responsibilities.
2. Adding a new rule requires editing the same method (risk of regressions).
3. Rule configuration is hard-coded.
4. Reasons formatting is mixed with evaluation.
5. Engine does persistence-ish logging via `FakeEligibilityStore`.
6. Type/flag logic is scattered.

## 4. Your task
Checkpoint A: Run and capture output.
Checkpoint B: Move each rule to its own unit (class) behind a shared abstraction.
Checkpoint C: Make it possible to add a new rule without editing the main evaluation logic.
Checkpoint D: Keep report text identical.

## 5. Constraints
- Keep `StudentProfile` fields unchanged.
- Preserve order of reasons in output.
- No external libraries.

## 6. Acceptance criteria
- New eligibility rule can be added by creating a new class and wiring it with minimal edits.
- No giant conditional chains.

## 7. How to run
```bash
cd SOLID/Ex3/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Placement Eligibility ===
Student: Ayaan (CGR=8.10, attendance=72, credits=18, flag=NONE)
RESULT: NOT_ELIGIBLE
- attendance below 75
Saved evaluation for roll=23BCS1001
```

## 9. Hints (OOP-only)
- Use a list of rule objects and iterate.
- Keep rules small and single-purpose.

## 10. Stretch goals
- Read rule thresholds from a config object without editing rule logic.

---

## 11. What was the Problem? (The "Before" State)

In the initial repository, the `EligibilityEngine.evaluate` method was a massive block of `if/else` statements checking different conditions (CGR, attendance, credits, disciplinary flags).

The major issues were:

- **OCP Violation (The Giant Conditional):** To add a new placement rule (e.g., "Must have completed 2 internships"), you would have to open the `EligibilityEngine` class and add another `if/else` block. This risks breaking existing, working logic (regressions).

- **Hard-coded Thresholds:** The minimum CGR, attendance (75%), and required credits were hard-coded inside the `if` statements. Changing the attendance requirement to 80% meant digging into the code to find the magic number.

- **SRP Violations:** Just like Exercise 2, the engine was doing too much — evaluating rules, building strings for the reasons, printing the report, and saving data to `FakeEligibilityStore`.

- **Scattered Logic:** Disciplinary flags and academic scores were all evaluated in the same messy method, making it hard to read and test independently.

---

## 12. How it was Solved (The Refactored State)

The solution transformed the rigid `if/else` chain into a dynamic **Rules Engine** using the **Strategy Pattern**.

### A. Abstracting the Rules (The Core Fix)

Instead of writing logic directly in the engine, the `EligibilityRule` interface was created, mandating methods like `boolean isSatisfied(StudentProfile profile)` and `String getFailureReason()`.

Every single rule became its own isolated class: `CgrRule`, `AttendanceRule`, `CreditsRule`, and `DisciplinaryRule`.

> **Why this helps:** Each rule class has exactly one reason to change — its own specific logic. Rules are independently readable and testable.

### B. Closing the Engine to Modification

The `EligibilityEngine` was rewritten so it no longer contains any business logic. Instead, it accepts a `List<EligibilityRule>`.

When `evaluate` is called, the engine simply loops through the list of rules. If a rule fails, it collects the failure reason.

> **Why this helps:** This is OCP in action. To add a new rule tomorrow, you just create a new class that implements `EligibilityRule` and add it to the list. You never have to edit `EligibilityEngine` again.

### C. Removing Hard-coded Configurations (Stretch Goal)

Instead of hard-coding `75` for attendance inside the class, the rules accept their thresholds via a constructor (e.g., `new AttendanceRule(75)`).

> **Why this helps:** The rules become reusable and configurable. If the threshold changes next semester, you change the configuration where the rule is instantiated — not the rule logic itself.

### D. Extracting Formatting and Logging

The logic for printing the exact report text (`=== Placement Eligibility ===`, `RESULT: NOT_ELIGIBLE`) and logging to `FakeEligibilityStore` was moved out of the engine into a dedicated `ReportPrinter` and `EligibilityStore` abstraction.

The engine now simply returns an object containing a boolean result and a list of failure reasons. A separate `ReportPrinter` handles presentation and saving.

> **Why this helps:** By turning a static wall of `if/else` statements into a plug-and-play list of independent rule objects, the system became highly extensible, modular, and much easier to test.
