/**
 * Sends WhatsApp notifications. Phone number validity is assumed by caller.
 */
public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    public void send(WhatsAppNotification n) {
        System.out.println("WA -> to=" + n.toPhone + " body=" + n.body);
        audit.add("wa sent");
    }
}
