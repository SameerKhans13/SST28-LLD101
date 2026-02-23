/**
 * Outcome of an export operation.
 *
 * <p>The result carries a status (OK or ERROR), a content type when successful,
 * the produced bytes, and an optional message when an error occurred.  Using a
 * status enum makes callers rely on the result object instead of magic values.
 */
public class ExportResult {
    public enum Status { OK, ERROR }

    public final Status status;
    public final String contentType;   // meaningful only when status==OK
    public final byte[] bytes;         // may be empty but never null
    public final String message;       // error message when status==ERROR

    private ExportResult(Status status, String contentType, byte[] bytes, String message) {
        this.status = status;
        this.contentType = contentType;
        this.bytes = bytes;
        this.message = message;
    }

    public static ExportResult ok(String contentType, byte[] bytes) {
        if (contentType == null) throw new NullPointerException("contentType");
        if (bytes == null) throw new NullPointerException("bytes");
        return new ExportResult(Status.OK, contentType, bytes, null);
    }

    public static ExportResult error(String message) {
        if (message == null) throw new NullPointerException("message");
        return new ExportResult(Status.ERROR, "", new byte[0], message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExportResult)) return false;
        ExportResult that = (ExportResult) o;
        if (status != that.status) return false;
        if (!contentType.equals(that.contentType)) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return java.util.Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + contentType.hashCode();
        result = 31 * result + java.util.Arrays.hashCode(bytes);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExportResult{" +
                "status=" + status +
                ", contentType='" + contentType + '\'' +
                ", bytes.length=" + (bytes == null ? 0 : bytes.length) +
                (message != null ? ", message='" + message + '\'' : "") +
                '}';
    }
}
