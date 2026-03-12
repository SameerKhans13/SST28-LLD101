/**
 * Tiny self‑test harness.  Use `java ManualTest` to verify key contracts.
 */
public class ManualTest {
    public static void main(String[] args) {
        Exporter pdf = Exporters.pdf();
        Exporter json = Exporters.json();

        ExportRequest shortReq = new ExportRequest("T", "body");
        assert pdf.export(shortReq).bytes.length > 0 : "pdf should produce bytes";

        boolean threw = false;
        try {
            pdf.export(new ExportRequest("T", "012345678901234567890"));
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        assert threw : "pdf should reject long bodies";

        threw = false;
        try {
            json.export(null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        assert threw : "null request should be denied";

        System.out.println("Manual tests passed");
    }
}