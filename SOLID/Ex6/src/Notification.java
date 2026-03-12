import java.util.Objects;

/**
 * Immutable value representing a notification request.  Factories
 * ensure only the relevant fields are populated based on channel.
 */
public class Notification {
    public final Channel channel;
    public final String subject;   // may be null for SMS/WA
    public final String body;
    public final String email;     // only for EMAIL
    public final String phone;     // only for SMS/WA

    private Notification(Channel channel, String subject, String body,
                         String email, String phone) {
        this.channel = Objects.requireNonNull(channel);
        this.subject = subject; // subject may legitimately be null
        this.body = Objects.requireNonNull(body);
        this.email = email;
        this.phone = phone;

        // channel-specific invariants
        switch (channel) {
            case EMAIL -> {
                if (email == null) throw new IllegalArgumentException("email required");
            }
            case SMS -> {
                if (phone == null) throw new IllegalArgumentException("phone required for SMS");
            }
            case WA -> {
                if (phone == null) throw new IllegalArgumentException("phone required for WhatsApp");
            }
        }
    }

    public static Notification forEmail(String subject, String body, String email) {
        return new Notification(Channel.EMAIL, subject, body, Objects.requireNonNull(email), null);
    }

    public static Notification forSms(String body, String phone) {
        return new Notification(Channel.SMS, null, body, null, Objects.requireNonNull(phone));
    }

    public static Notification forWhatsApp(String body, String phone) {
        return new Notification(Channel.WA, null, body, null, Objects.requireNonNull(phone));
    }
}