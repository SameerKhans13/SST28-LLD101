public class SimpleDeliverer implements Deliverer {
    @Override
    public ExportResult deliver(byte[] data, String contentType) {
        // always succeed and use the provided content type
        return ExportResult.ok(contentType, data);
    }
}