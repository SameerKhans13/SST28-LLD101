import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Routes notifications to a handler.  New channels can be registered
 * without touching existing code.
 */
public class NotificationService {
    private final Map<Channel, Function<Notification, Boolean>> handlers =
            new EnumMap<>(Channel.class);

    public NotificationService(EmailSender email, SmsSender sms, WhatsAppSender wa) {
        handlers.put(Channel.EMAIL, email::sendEmail);
        handlers.put(Channel.SMS, sms::sendSms);
        handlers.put(Channel.WA, wa::sendWhatsApp);
    }

    public boolean send(Notification n) {
        Function<Notification, Boolean> h = handlers.get(n.channel);
        if (h == null) throw new IllegalArgumentException("no handler for channel " + n.channel);
        return h.apply(n);
    }
}