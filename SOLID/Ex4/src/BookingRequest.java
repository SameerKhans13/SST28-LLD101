import java.util.*;

public class BookingRequest {
    public final RoomType roomType;
    public final List<AddOn> addOns;

    public BookingRequest(RoomType roomType, List<AddOn> addOns) {
        if (roomType == null) throw new IllegalArgumentException("roomType");
        if (addOns == null) throw new IllegalArgumentException("addOns");
        this.roomType = roomType;
        this.addOns = List.copyOf(addOns);
    }
}