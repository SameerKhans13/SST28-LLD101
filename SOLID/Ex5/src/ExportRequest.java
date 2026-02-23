/**
 * Immutable request data for an export.  Title and body are non-null but may
 * be empty; this makes validation explicit and prevents downstream null checks.
 */
public class ExportRequest {
    private final String title;
    private final String body;

    public ExportRequest(String title, String body) {
        if (title == null) throw new NullPointerException("title");
        if (body == null) throw new NullPointerException("body");
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
