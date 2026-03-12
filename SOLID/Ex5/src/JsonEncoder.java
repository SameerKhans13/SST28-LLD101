import java.nio.charset.StandardCharsets;

public final class JsonEncoder implements Encoder {
    @Override
    public String contentType() {
        return "application/json";
    }

    @Override
    public byte[] encode(ExportRequest req) {
        // req fields are non-null
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return json.getBytes(StandardCharsets.UTF_8);
    }

    private String escape(String s) {
        // input never null after normalisation
        return s.replace("\"", "\\\"");
    }
}