# Ex5 — LSP: File Exporter Hierarchy

## 1. Context
A reporting tool exports student performance data to multiple formats.

## 2. Current behavior
- `Exporter` has `export(ExportRequest)` that returns `ExportResult`
- `PdfExporter` throws for large content (tightens preconditions)
- `CsvExporter` silently changes meaning by dropping newlines and commas poorly
- `JsonExporter` returns empty on null (inconsistent contract)
- `Main` demonstrates current behavior

## 3. What’s wrong (at least 5 issues)
1. Subclasses violate expectations of the base `Exporter` contract.
2. `PdfExporter` throws for valid requests (from base perspective).
3. `CsvExporter` changes semantics of fields (data corruption risk).
4. `JsonExporter` handles null differently than others.
5. Callers cannot rely on substitutability; they need format-specific workarounds.
6. Contract is not documented; behavior surprises are runtime.

## 4. Your task
Checkpoint A: Run and capture output.

Checkpoint B: Define a clear base contract (preconditions/postconditions).
The contract should require a non-null request, guarantee a non-null
result, and forbid runtime exceptions; errors must be reported via the return
value.

Checkpoint C: Refactor the export code so the two concerns are separated:
* **encoding** (format-specific byte generation) and
* **delivery** (policies such as size limits).
Clients should be able to assemble an exporter from an encoder+deliverer and
use it without `instanceof` or catching exceptions.  Prefer composition over
inheritance; if a format cannot adhere to the base contract, it should not be a
subclass of `Exporter` at all.

Checkpoint D: Keep observable outputs identical for current inputs.

## 5. Constraints
- Keep `Main` outputs unchanged for the given samples.
- No external libraries.
- Default package (a real project would use a package but that's out of scope).

## 6. Acceptance criteria
- Base contract is explicit and enforced consistently (null request forbidden, result always non-null).
- No exporter tightens preconditions compared to base contract.
- Caller should not need `instanceof` to be safe.
- *Results* must expose a status enum (OK/ERROR); error details are not encoded in content type.
- `ExportRequest` constructor validates its fields.


## 6. Acceptance criteria
- Base contract is explicit and enforced consistently (null request forbidden, result always non-null).
- No exporter tightens preconditions compared to base contract.
- Caller should not need `instanceof` to be safe.
- *Results* must expose a status enum (OK/ERROR); error details are not encoded in content type.

## 7. How to run
```bash
cd SOLID/Ex5/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Export Demo ===
PDF: ERROR: PDF cannot handle content > 20 chars
CSV: OK bytes=54
JSON: OK bytes=63
```

## 9. Hints (OOP-only)
- If a subtype cannot support the base contract, reconsider inheritance.
- Prefer composition: separate “format encoding” from “delivery constraints”.

## 10. Stretch goals
- Add a new exporter without changing existing exporters.
