public interface Encoder {
    /**
     * Convert an export request into a sequence of bytes for transport.
     *
     * <p>Implementations must never return <code>null</code>.  Null fields in the
     * request should be treated as empty strings or otherwise handled safely.
     */
    byte[] encode(ExportRequest request);

    /**
     * Return the MIME content type associated with the bytes produced by this
     * encoder.
     */
    String contentType();
}