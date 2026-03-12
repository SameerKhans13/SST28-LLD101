public final class NoPolicy implements DeliveryPolicy {
    @Override
    public void validate(ExportRequest req) {
        // nothing to check
    }
}