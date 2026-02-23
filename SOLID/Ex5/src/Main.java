public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        // build exporters via composition (encoder + deliverer)
        Exporter pdf = new Exporter(new PdfEncoder(),
                                    new SizeLimitedDeliverer(20, "PDF cannot handle content > 20 chars"));
        Exporter csv = new Exporter(new CsvEncoder(), new SimpleDeliverer());
        Exporter json = new Exporter(new JsonEncoder(), new SimpleDeliverer());

        System.out.println("PDF: " + check(pdf, req));
        System.out.println("CSV: " + check(csv, req));
        System.out.println("JSON: " + check(json, req));
    }

    private static String check(Exporter e, ExportRequest r) {
        ExportResult out = e.export(r);
        if (out.status == ExportResult.Status.ERROR) {
            return "ERROR: " + out.message;
        }
        return "OK bytes=" + out.bytes.length;
    }
}
