public interface EmailSender {
    /**
     * Send the notification.  Returns true on success, false otherwise.
     * Implementations should not throw runtime exceptions to signal failures.
     */
    boolean sendEmail(Notification n);
}