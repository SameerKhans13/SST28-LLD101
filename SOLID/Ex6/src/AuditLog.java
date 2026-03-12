/**
 * Simple audit log contract used by senders.  Implementations may be
 * replaced with stubs for testing or demonstration.
 */
public interface AuditLog {
    void add(String entry);
    int size();
}