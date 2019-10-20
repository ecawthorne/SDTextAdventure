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
    //Introduction to the room if there is one
    private String intro = null;
    //Description of the room the player is currently in
    private String internalDesc = null;
    //Description of the room as seen form adjacent rooms
    private String externalDesc = null;
    //Directions the player can move in and the description of the location
    private Map<String, Room> doors = null;
    //All items in room
    private Item[] items;
    //Keep the player in a room until a condition is met
    private boolean exit = false;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
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
    //Handle exception here or limit input
    public Item getItem(int slot)
    {
        return items[slot];
    }

    public void setItem(Item addedItem)
    {
        //Make sure this works. Might have off by one or other error
        items[this.items.length] = addedItem;
    }
    

    boolean changeRoom(String direction)
    {
        return true;
    }

    public boolean getRoomDesc()
    {
        /* Need to implement a way to hide certain items before using this
        System.out.println(getInternalDesc());
        for(int i = 0; i < items.length; i++)
        {
            System.out.println("You see a " + items[i].getName());
        }
         */
        return true;
    }
}
