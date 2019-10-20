//Basic room class. Every room inherits from this.

import java.util.Map;
/**
 *
 * @author Eric
 */
abstract public class Room
{

    //Name of current room
    private String name = null;
    //Description of the room the player is currently in
    private String internalDesc = null;
    //Description of the room as seen form adjacent rooms
    private String externalDesc;
    //Directions the player can move in and the description of the location
    private Map<String, Room> directions = null;
    //Keep the player in a room until a condition is met
    private boolean exit = false;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setInternalDesc(String internalDesc)
    {
        this.internalDesc = internalDesc;
    }

    public void setExternalDesc(String externalDesc)
    {
        this.externalDesc = externalDesc;
    }

    public String getName()
    {
        return name;
    }

    public String getInternalDesc()
    {
        return internalDesc;
    }

    public String getExternalDesc()
    {
        return externalDesc;
    }
}
