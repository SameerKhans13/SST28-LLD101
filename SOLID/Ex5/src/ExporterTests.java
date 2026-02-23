public class ExporterTests {
    public static void main(String[] args) {
        testNullRequest();
        testSizeLimit();
        testEncoders();
        testDeliverers();
        System.out.println("All tests passed");
    }

    private static void testNullRequest() {
        Exporter e = new Exporter(new JsonEncoder(), new SimpleDeliverer());
        try {
            e.export(null);
            throw new AssertionError("Expected NPE on null request");
        } catch (NullPointerException ex) {
            // ok
        }
        // also verify that ExportRequest itself guards its fields
        try {
            new ExportRequest(null, "x");
            throw new AssertionError("Expected NPE on null title");
        } catch (NullPointerException ex) {
            // ok
        }
        try {
            new ExportRequest("x", null);
            throw new AssertionError("Expected NPE on null body");
        } catch (NullPointerException ex) {
            // ok
        }
        ExportResult res = e.export(new ExportRequest("t", "b"));
        if (!"application/json".equals(res.contentType)) {
            throw new AssertionError("Incorrect content type");
        }
        if (res.status != ExportResult.Status.OK) {
            throw new AssertionError("Expected OK status");
        }
    }

    private static void testSizeLimit() {
        Exporter pdf = new Exporter(new PdfEncoder(),
                                    new SizeLimitedDeliverer(5, "too big"));
        ExportRequest req = new ExportRequest("x", "123456");
        ExportResult res = pdf.export(req);
        if (res.status != ExportResult.Status.ERROR || !"too big".equals(res.message)) {
            throw new AssertionError("Size limit not enforced");
        }
    }

    private static void testEncoders() {
        ExportRequest req = new ExportRequest("A", "B,C\nD");
        PdfEncoder pdf = new PdfEncoder();
        byte[] pdfBytes = pdf.encode(req);
        if (pdfBytes.length == 0) throw new AssertionError("PdfEncoder empty");
        CsvEncoder csv = new CsvEncoder();
        String csvStr = new String(csv.encode(req), java.nio.charset.StandardCharsets.UTF_8);
        if (!csvStr.contains("A")) throw new AssertionError("CsvEncoder wrong");
        JsonEncoder json = new JsonEncoder();
        String jsonStr = new String(json.encode(req), java.nio.charset.StandardCharsets.UTF_8);
        if (!jsonStr.contains("\"title\"")) throw new AssertionError("JsonEncoder wrong");
    }

    private static void testDeliverers() {
        byte[] data = new byte[10];
        ExportResult ok = new SimpleDeliverer().deliver(data, "foo/bar");
        if (ok.status != ExportResult.Status.OK || ok.bytes.length != 10) {
            throw new AssertionError("SimpleDeliverer failed");
        }
        ExportResult err = new SizeLimitedDeliverer(5, "nah").deliver(data, "x");
        if (err.status != ExportResult.Status.ERROR || !"nah".equals(err.message)) {
            throw new AssertionError("SizeLimitedDeliverer failed");
        }
    }
}