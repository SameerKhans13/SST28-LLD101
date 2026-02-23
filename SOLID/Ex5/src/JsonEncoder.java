import java.nio.charset.StandardCharsets;

public class JsonEncoder implements Encoder {
    @Override
    public byte[] encode(ExportRequest req) {
        String title = escape(req.getTitle());
        String body = escape(req.getBody());
        String json = "{\"title\":\"" + title + "\",\"body\":\"" + body + "\"}";
        return json.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String contentType() {
        return "application/json";
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}