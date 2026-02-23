/**
 * Sends EmailNotification instances. Truncation/normalization is
 * handled externally (factory or caller) so this class has no surprises.
 */
public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    public void send(EmailNotification n) {
        // content already normalized by factory
        System.out.println("EMAIL -> to=" + n.toEmail + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
    }
}
