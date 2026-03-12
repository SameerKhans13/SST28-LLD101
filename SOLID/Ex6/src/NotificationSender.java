/**
 * @deprecated retained only for history; not used in new design.
 *             Channel-specific interfaces have replaced this hierarchy.
 */
@Deprecated
public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public abstract void send(Notification n);
}