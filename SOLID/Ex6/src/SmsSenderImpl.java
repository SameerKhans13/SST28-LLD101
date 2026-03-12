public class SmsSenderImpl implements SmsSender {
    private final AuditLog audit;
    public SmsSenderImpl(AuditLog audit) { this.audit = audit; }

    @Override
    public boolean sendSms(Notification n) {
        if (n.phone == null) return false;
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return true;
    }
}