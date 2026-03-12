public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        
        Exporter pdf = Exporters.pdf();
        Exporter csv = Exporters.csv();
        Exporter json = Exporters.json();

        System.out.println("PDF: " + safe(pdf, req));
        System.out.println("CSV: " + safe(csv, req));
        System.out.println("JSON: " + safe(json, req));
    }

    private static String safe(Exporter e, ExportRequest r) {
        try {
            ExportResult out = e.export(r);
            return "OK bytes=" + out.bytes.length;
        } catch (RuntimeException ex) {
            return "ERROR: " + ex.getMessage();
        }
    }
}