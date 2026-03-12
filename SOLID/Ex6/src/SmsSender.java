public interface SmsSender {
    /**
     * SMS ignores subject; callers should not rely on it.
     */
    boolean sendSms(Notification n);
}