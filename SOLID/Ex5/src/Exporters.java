/**
 * Simple factory helpers for common exporters.
 *
 */
public final class Exporters {
    private Exporters() { /* utility */ }

    public static Exporter pdf() {
        return new Exporter(new PdfEncoder(), new MaxBodyLengthPolicy(20)) {};
    }

    public static Exporter csv() {
        return new Exporter(new CsvEncoder(), new NoPolicy()) {};
    }

    public static Exporter json() {
        return new Exporter(new JsonEncoder(), new NoPolicy()) {};
    }
}