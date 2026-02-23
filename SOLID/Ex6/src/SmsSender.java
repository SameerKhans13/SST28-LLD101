/**
 * Sends SMS notifications; ignores subjects since SmsNotification has none.
 */
public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    public void send(SmsNotification n) {
        System.out.println("SMS -> to=" + n.toPhone + " body=" + n.body);
        audit.add("sms sent");
    }
}
