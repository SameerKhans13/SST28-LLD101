/**
 * An exporter composes an {@link Encoder} and a {@link Deliverer}.
 *
 * <p>The <b>contract</b> for <code>export</code> is documented here so that
 * all consumers and implementations can rely on it:
 *
 * <ul>
 *   <li><b>Preconditions</b> – the request argument must be non-null.  The
 *       request fields may be null; encoders are responsible for handling
 *       that gracefully.</li>
 *   <li><b>Postconditions</b> – the method never throws a runtime exception
 *       for a well-formed request.  Any failure is reflected in the returned
 *       {@link ExportResult}.  The result object itself is non-null and its
 *       <code>bytes</code> field is never null.</li>
 *   <li>Callers are free to substitute one exporter for another without
 *       guarding with <code>instanceof</code> or catching exceptions.  In
 *       other words, the class is Liskov‑substitution–safe.</li>
 * </ul>
 */
import java.util.Objects;

public class Exporter {
    private final Encoder encoder;
    private final Deliverer deliverer;

    public Exporter(Encoder encoder, Deliverer deliverer) {
        this.encoder = encoder;
        this.deliverer = deliverer;
    }

    public ExportResult export(ExportRequest req) {
        Objects.requireNonNull(req, "request");
        byte[] data = encoder.encode(req);
        // encoder contract: never return null
        return deliverer.deliver(data, encoder.contentType());
    }
}
