
/**
 *
 * @author Eric
 */
public class VillageRoom extends Room
{

    VillageRoom()
    {
    }

    public VillageRoom(String n, int loc)
    {
        super(n, loc);
    }

    public VillageRoom(String n, int descLoc, Room connectedRoom)
    {
        super(n, descLoc, connectedRoom);
    }

    public VillageRoom(String n, int loc, Room conRoom1, Room conRoom2)
    {
        super(n, loc, conRoom1, conRoom2);
    }

    public VillageRoom(String n, int loc, Room conRoom1, Room conRoom2, Room conRoom3)
    {
        super(n, loc, conRoom1, conRoom2, conRoom3);
    }

    public VillageRoom(String n, int loc, Room conRoom1, Room conRoom2, Room conRoom3, Room conRoom4)
    {
        super(n, loc, conRoom1, conRoom2, conRoom3, conRoom4);
    }

}
