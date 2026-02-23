import java.nio.charset.StandardCharsets;

public class PdfEncoder implements Encoder {
    @Override
    public byte[] encode(ExportRequest req) {
        // pdf encoding does not impose size checks; those belong to a deliverer
        String title = Strings.nullToEmpty(req.getTitle());
        String body = Strings.nullToEmpty(req.getBody());
        String fakePdf = "PDF(" + title + "):" + body;
        return fakePdf.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String contentType() {
        return "application/pdf";
    }
}