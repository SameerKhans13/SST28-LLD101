/**
 * Data carrier for WhatsApp notifications.
 * Phone validation must occur before construction (e.g. via factory).
 */
public class WhatsAppNotification {
    public final String toPhone;
    public final String body;

    public WhatsAppNotification(String toPhone, String body) {
        this.toPhone = toPhone;
        this.body = body;
    }
}