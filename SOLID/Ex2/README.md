# Ex2 — SRP: Campus Cafeteria Billing

## 1. Context
A cafeteria billing console generates invoices for student orders. It currently handles menu definition, tax logic, discount logic, invoice formatting, and persistence.

## 2. Current behavior
- Uses an in-memory menu
- Builds an order (hard-coded in `Main`)
- Computes subtotal, tax, discount, and total
- Prints an invoice and writes it to a file-like store (in-memory)

## 3. What’s wrong with the design (at least 5 issues)
1. `CafeteriaSystem.checkout` mixes menu lookup, pricing, tax rules, discount rules, printing, and persistence.
2. Tax rules are hard-coded and not extensible.
3. Discounts are hard-coded with ad-hoc conditions.
4. Invoice formatting is mixed with money calculations.
5. Persistence is a concrete class; hard to test without writing.
6. `Main` depends on too many internal details.

## 4. Your task (refactor plan)
Checkpoint A: Run and capture output.
Checkpoint B: Separate pricing/tax/discount computations into dedicated components.
Checkpoint C: Move invoice formatting out of `CafeteriaSystem`.
Checkpoint D: Introduce small abstractions to decouple persistence and rules.
Checkpoint E: Keep output identical.

## 5. Constraints
- Preserve exact invoice text and line order.
- Keep `MenuItem` and `OrderLine` public fields unchanged.
- No external libraries.

## 6. Acceptance criteria
- `CafeteriaSystem` orchestrates only; it should not format strings or encode tax/discount specifics.
- Adding a new discount should not require editing a long method.

## 7. How to run
```bash
cd SOLID/Ex2/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Cafeteria Billing ===
Invoice# INV-1001
- Veg Thali x2 = 160.00
- Coffee x1 = 30.00
Subtotal: 190.00
Tax(5%): 9.50
Discount: -10.00
TOTAL: 189.50
Saved invoice: INV-1001 (lines=7)
```

## 9. Hints (OOP-only)
- Keep “rules” behind interfaces so new rules can be added without editing a big method.
- Keep formatting as a separate responsibility.

## 10. Stretch goals
- Add a second invoice for a staff member with different discount policy.

---

## 11. What was the Problem? (The "Before" State)

In the initial problem statement, the code suffered from a severe violation of the Single Responsibility Principle. The `CafeteriaSystem` class—specifically its `checkout` method—was acting as a **"God Object"**, meaning it was trying to do everything at once.

The major issues included:

- **Mixed Responsibilities:** The `checkout` method handled looking up menu prices, doing math, formatting text, and saving files all in one place.

- **Hard-coded Rules:** Tax rules (e.g., a flat 5%) and discount conditions were hard-coded directly into the method using `if/else` statements. If a new tax law passed or a new promotional discount was created, you would have to modify the core checkout logic.

- **Coupled Formatting:** Business logic (calculating money) was mixed with presentation logic (string concatenation and layout). If you wanted to change the invoice format from plain text to JSON or HTML, you would risk breaking the mathematical calculations.

- **Concrete Persistence:** The system relied on a concrete, in-memory storage class rather than an interface. This made the system rigidly tied to one way of saving data and made unit testing difficult because you couldn't easily "mock" the database.

---

## 12. How it was Solved (The Refactored State)

The solution fixed these issues by breaking the giant `CafeteriaSystem` apart into smaller, dedicated components, ensuring each class only had **one reason to change**.

### A. Extracting Tax and Discount Rules (Strategy Pattern)

Instead of hard-coding the math, the solution abstracted the rules behind interfaces (e.g., `ITaxStrategy`, `IDiscountStrategy`).

Concrete classes like `StudentTaxStrategy` and `StudentDiscountStrategy` were created.

> **Why this helps:** If you want to accomplish the stretch goal (adding a staff member discount), you simply create a new `StaffDiscountStrategy` class that implements the interface. The core `CafeteriaSystem` doesn't need to be modified at all.

### B. Isolating Invoice Formatting

The string generation was completely removed from the billing calculations and moved into a dedicated `InvoiceFormatter` component.

The billing logic now calculates the raw numbers (Subtotal, Tax, Discount, Total) and passes a raw data object (`InvoiceData`) to the formatter.

> **Why this helps:** The exact text alignment, line order, and headers are now managed entirely by the formatter. Changing the format never touches the business logic.

### C. Abstracting Persistence

The direct dependency on the concrete file-store was replaced with the `IInvoiceStore` interface.

> **Why this helps:** `CafeteriaSystem` now just calls `store.save(invoice)`. It doesn't care if the invoice is being saved to an in-memory list, a SQL database, or a text file.

### D. Reducing `CafeteriaSystem` to an Orchestrator

In the final solution, the `CafeteriaSystem.checkout()` method no longer does the heavy lifting. Instead, it acts as a **pure coordinator**:

1. It asks the **Rules Components** (`BillCalculator`, `TaxRules`, `DiscountRules`) for the tax and discount amounts.
2. It asks the **Formatter** (`InvoiceFormatter`) to turn those numbers into an invoice string.
3. It asks the **Repository** (`IInvoiceStore`) to save the result.
