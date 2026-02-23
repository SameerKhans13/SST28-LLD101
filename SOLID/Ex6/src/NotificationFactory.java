/**
 * Helper responsible for creating notifications with any required
 * validation/normalization. Keeps business rules out of the POJOs.
 */
public class NotificationFactory {
    private static final int EMAIL_MAX_BODY = 40; // original demo limit

    public static EmailNotification createEmail(String to, String subject, String body) {
        if (to == null) throw new IllegalArgumentException("email address required");
        String trimmedBody = body;
        if (trimmedBody != null && trimmedBody.length() > EMAIL_MAX_BODY) {
            trimmedBody = trimmedBody.substring(0, EMAIL_MAX_BODY);
        }
        return new EmailNotification(to, subject, trimmedBody);
    }

    public static SmsNotification createSms(String phone, String body) {
        if (phone == null) throw new IllegalArgumentException("phone number required");
        return new SmsNotification(phone, body);
    }

    public static WhatsAppNotification createWhatsApp(String phone, String body) {
        if (phone == null || !phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        return new WhatsAppNotification(phone, body);
    }
}