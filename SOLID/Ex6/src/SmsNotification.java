/**
 * Data carrier for SMS notifications. No subject field exists.
 */
public class SmsNotification {
    public final String toPhone;
    public final String body;

    public SmsNotification(String toPhone, String body) {
        if (toPhone == null) throw new IllegalArgumentException("phone number required");
        this.toPhone = toPhone;
        this.body = body;
    }
}