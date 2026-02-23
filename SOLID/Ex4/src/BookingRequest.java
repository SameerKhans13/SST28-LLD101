import java.util.*;

public class BookingRequest {
    private final RoomType roomType;
    private final List<AddOn> addOns;

    public BookingRequest(RoomType roomType, List<AddOn> addOns) {
        this.roomType = roomType;
        // defensively copy to prevent external mutation
        this.addOns = new ArrayList<>(addOns);
    }

    public RoomType getRoomType() { return roomType; }
    public List<AddOn> getAddOns() { return Collections.unmodifiableList(addOns); }
}
