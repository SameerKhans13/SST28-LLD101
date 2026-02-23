public interface Deliverer {
    /**
     * "Delivers" the encoded bytes and returns an {@link ExportResult}.
     *
     * <p>On success the result has status OK, carries the supplied
     * <code>contentType</code> and the original data.  On failure it returns
     * a result with status ERROR and a human‑readable message; callers should
     * not rely on the <code>contentType</code> or <code>bytes</code> in that
     * case.
     *
     * @param data        the encoded payload; never null
     * @param contentType the mime type produced by the encoder; never null
     */
    ExportResult deliver(byte[] data, String contentType);
}