public interface WhatsAppSender {
    /**
     * Returns false if the phone number is invalid.
     * No exceptions should escape to callers.
     */
    boolean sendWhatsApp(Notification n);
}