public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLogImpl();

        // build notifications using helpers to ensure invariants
        Notification emailNotif = Notification.forEmail(
                "Welcome", "Hello and welcome to SST!", "riya@sst.edu");
        Notification smsNotif = Notification.forSms(
                "Hello and welcome to SST!", "9876543210");
        Notification waNotif = Notification.forWhatsApp(
                "Hello and welcome to SST!", "9876543210");

        NotificationService service = new NotificationService(
                new EmailSenderImpl(audit),
                new SmsSenderImpl(audit),
                new WhatsAppSenderImpl(audit));

        // note: send returns boolean; record error if false
        if (!service.send(emailNotif)) {
            System.out.println("EMAIL ERROR");
            audit.add("EMAIL failed");
        }
        if (!service.send(smsNotif)) {
            System.out.println("SMS ERROR");
            audit.add("SMS failed");
        }
        if (!service.send(waNotif)) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}