import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Problem: Originally, IncidentTicket was mutable. Anyone could change the values using setters 
 * after the object was created (violating data integrity). This was very risky for audit logs 
 * because the data could change randomly anywhere in the code.
 *
 * Solution: We made the class Immutable and used the Builder Design Pattern. 
 * Now, once a ticket is created, it cannot be changed. If we need to "update" something, 
 * we create a brand new ticket instance using the toBuilder() method. This makes our design very robust.
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Use service to "update" ticket: new instances are returned
        t = service.assign(t, "agent@example.com");
        t = service.escalateToCritical(t);
        System.out.println("\nAfter service updates: " + t);

        // Attempt external mutation: tags list should be unmodifiable
        List<String> tags = t.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal mutation prevented: " + e);
        }
        System.out.println("\nFinal ticket: " + t);

        // Starter compiles; after refactor, you should redesign updates to create new objects instead.
    }
}