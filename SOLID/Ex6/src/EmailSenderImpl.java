public class EmailSenderImpl implements EmailSender {
    private static final int MAX_BODY = 40;
    private final AuditLog audit;
    public EmailSenderImpl(AuditLog audit) { this.audit = audit; }

    @Override
    public boolean sendEmail(Notification n) {
        if (n.email == null) return false;
        String body = n.body;
        if (body.length() > MAX_BODY) body = body.substring(0, MAX_BODY);
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
        return true;
    }
}