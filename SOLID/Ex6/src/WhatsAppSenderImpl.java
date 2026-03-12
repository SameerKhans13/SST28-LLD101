public class WhatsAppSenderImpl implements WhatsAppSender {
    private final AuditLog audit;
    public WhatsAppSenderImpl(AuditLog audit) { this.audit = audit; }

    @Override
    public boolean sendWhatsApp(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            // invalid number, report failure
            return false;
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
        return true;
    }
}