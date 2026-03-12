import java.nio.charset.StandardCharsets;

public final class PdfEncoder implements Encoder {
    @Override
    public String contentType() {
        return "application/pdf";
    }

    @Override
    public byte[] encode(ExportRequest req) {
        // request has already been normalised (title/body non-null)
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return fakePdf.getBytes(StandardCharsets.UTF_8);
    }
}