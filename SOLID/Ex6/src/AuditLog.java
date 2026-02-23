// simple stub used only for counting events in this assignment.
// In a real system this would be replaced with proper audit storage.
public class AuditLog {
    private int count = 0;
    public void add(String e) { count++; }
    public int size() { return count; }
}
