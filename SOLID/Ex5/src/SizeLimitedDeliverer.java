public class SizeLimitedDeliverer implements Deliverer {
    private final int maxBytes;
    private final String errorMessage;

    public SizeLimitedDeliverer(int maxBytes, String errorMessage) {
        this.maxBytes = maxBytes;
        this.errorMessage = errorMessage;
    }

    @Override
    public ExportResult deliver(byte[] data, String contentType) {
        if (data.length > maxBytes) {
            return ExportResult.error(errorMessage);
        }
        return ExportResult.ok(contentType, data);
    }
}