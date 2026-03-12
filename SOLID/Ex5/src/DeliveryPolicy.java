public interface DeliveryPolicy {
    /**
     * Validate that the request meets any extra delivery constraints.
     *
     * @throws IllegalArgumentException if the request cannot be serviced
     */
    void validate(ExportRequest req);
}