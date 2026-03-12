public interface Encoder {
    /**
     * MIME content type produced by this encoder.
     */
    String contentType();

    /**
     * Encode the request into a byte array. Caller ensures req is non-null.
     */
    byte[] encode(ExportRequest req);
}