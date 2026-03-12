public final class MaxBodyLengthPolicy implements DeliveryPolicy {
    private final int max;

    public MaxBodyLengthPolicy(int max) {
        this.max = max;
    }

    @Override
    public void validate(ExportRequest req) {
        if (req.body != null && req.body.length() > max) {
            throw new IllegalArgumentException("PDF cannot handle content > " + max + " chars");
        }
    }
}