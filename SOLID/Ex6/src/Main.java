public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        EmailSender email = new EmailSender(audit);
        SmsSender sms = new SmsSender(audit);
        WhatsAppSender wa = new WhatsAppSender(audit);

        // build channel-specific notifications using factory (validates/normalizes)
        email.send(NotificationFactory.createEmail("riya@sst.edu", "Welcome", "Hello and welcome to SST!"));
        sms.send(NotificationFactory.createSms("9876543210", "Hello and welcome to SST!"));
        try {
            wa.send(NotificationFactory.createWhatsApp("9876543210", "Hello and welcome to SST!"));
        } catch (RuntimeException ex) {
            // factory may throw validation exception
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
