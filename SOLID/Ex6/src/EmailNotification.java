/**
 * Simple data carrier for an email notification.
 * Caller is responsible for ensuring the body length fits channel limits.
 */
public class EmailNotification {
    public final String toEmail;
    public final String subject;
    public final String body;

    public EmailNotification(String toEmail, String subject, String body) {
        if (toEmail == null) throw new IllegalArgumentException("email address required");
        this.toEmail = toEmail;
        this.subject = subject == null ? "" : subject;
        this.body = body;
    }
}