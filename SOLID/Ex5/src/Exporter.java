public abstract class Exporter {
    private final Encoder encoder;
    private final DeliveryPolicy policy;

    /**
     * Build an exporter by composing an encoder with an optional delivery policy.
     */
    protected Exporter(Encoder encoder, DeliveryPolicy policy) {
        this.encoder = encoder;
        this.policy = policy;
    }

    /**
     * Perform the export.
     *
     * <p>Contract:
     * <ul>
     *   <li>{@code req} must not be null (checked explicitly).</li>
     *   <li>if {@code req.title} or {@code req.body} are null they are
     *       treated as the empty string.</li>
     *   <li>The returned {@link ExportResult} and its fields are non-null.</li>
     * </ul>
     *
     * <p>The method normalises the request before passing it to the
     * {@linkplain DeliveryPolicy policy} and {@linkplain Encoder encoder},
     * so implementations can assume non-null title/body.  Throwing
     * runtime exceptions for unsupported content (e.g. PDF size limit) is
     * the job of a policy; encoders just encode whatever they receive.
     */
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        // normalise null fields once, rather than each encoder handling them
        ExportRequest safe = new ExportRequest(
                req.title == null ? "" : req.title,
                req.body == null ? "" : req.body);

        policy.validate(safe);
        byte[] bytes = encoder.encode(safe);
        return new ExportResult(encoder.contentType(), bytes);
    }
}