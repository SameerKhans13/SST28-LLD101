import java.nio.charset.StandardCharsets;

public class CsvEncoder implements Encoder {
    @Override
    public byte[] encode(ExportRequest req) {
        String title = Strings.nullToEmpty(req.getTitle());
        String body = Strings.nullToEmpty(req.getBody());
        // preserve existing behaviour: drop newlines and commas
        body = body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + title + "," + body + "\n";
        return csv.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String contentType() {
        return "text/csv";
    }
}