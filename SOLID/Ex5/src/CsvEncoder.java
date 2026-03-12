import java.nio.charset.StandardCharsets;

public final class CsvEncoder implements Encoder {
    @Override
    public String contentType() {
        return "text/csv";
    }

    @Override
    public byte[] encode(ExportRequest req) {
        // no more lossy replacement; nulls already normalised
        String csv = "title,body\n" + req.title + "," + req.body + "\n";
        return csv.getBytes(StandardCharsets.UTF_8);
    }
}