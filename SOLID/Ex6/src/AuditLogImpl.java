import java.util.ArrayList;
import java.util.List;

public class AuditLogImpl implements AuditLog {
    private final List<String> entries = new ArrayList<>();
    @Override
    public void add(String e) { entries.add(e); }
    @Override
    public int size() { return entries.size(); }
}