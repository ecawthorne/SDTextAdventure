//Basic room class. Every room inherits from this.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Base class for the rooms in the game. Holds basic information about the rooms
 * and the more generic methods that child classes use
 *
 * @author Eric
 * @author Chase
 */
abstract public class Room
{

    //List of items in the room
    private ArrayList<Item> itemList = new ArrayList<>();
    //List of characters in the room
    private ArrayList<NPChar> charList = new ArrayList<>();
    //Name of this room
    private String name = null;
    //Introduction to the room if there is one
    private String intro = null;
    //Description of the room
    private String internalDesc = null;
    //Description of the room as seen form adjacent rooms
    private String externalDesc = null;
    //Keep the player in a room until a condition is met
    private boolean leavable = true;
    //Instructions or hints for leaving the room
    private String leaveCond = null;
    //determine whether or not to print the intro based on if the player has been in the room before
    private boolean enteredBefore = false;
    //Some rooms may have events that can kill the player or have some other
    //function not performable by the metLeaveCond function
    private boolean event = false;

    //direction variables for next rooms
    Room northConnection = null;
    Room southConnection = null;
    Room eastConnection = null;
    Room westConnection = null;

    /**
     * @param n Name of the room
     * @param intro Intro for the room that the player sees when first entering
     * the room
     */
    Room(String n, String intro)
    {
        this.name = n;
        this.intro = intro;
    }

    /**
     * @param n Name of the room
     * @param intro Intro for the room that the player sees when first entering
     * the room
     * @param desc Description of the room
     */
    Room(String n, String intro, String desc)
    {
        this.name = n;
        this.intro = intro;
        this.internalDesc = desc;
    }

    /**
     *
     * @param name Name of the room
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return Return the intro to the room
     */
    public String getIntro()
    {
        return this.intro;
    }

    /**
     *
     * @param intro Sets the intro to the room
     */
    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    /**
     *
     * @param internalDesc Internal description of room
     */
    public void setInternalDesc(String internalDesc)
    {
        this.internalDesc = internalDesc;
    }

    //TODO Add external descriptions for rooms. These would be descriptions
    //seen from adjacent rooms
    /**
     *
     * @param externalDesc Descriptions of rooms seen from adjacent rooms
     */
    public void setExternalDesc(String externalDesc)
    {
        this.externalDesc = externalDesc;
    }

    /**
     *
     * @return Status of the condition for the player being able to leave the
     * room
     */
    public String getLeaveCond()
    {
        return leaveCond;
    }

    /**
     *
     * @param leaveCond Condition for the player being allowed to leave the room
     */
    public void setLeaveCond(String leaveCond)
    {
        this.leaveCond = leaveCond;
    }

    /**
     *
     * @return Returns the name of the room
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return Description of the room
     */
    public String getInternalDesc()
    {
        return internalDesc;
    }

    /**
     *
     * @return Returns the external description of the room
     */
    public String getExternalDesc()
    {
        return externalDesc;
    }

    /**
     * @param The direction in which to move
     */
    boolean changeRoom(String direction)
    {
        return true;
    }

    /**
     * Accepts a direction and a room object. Connects the room calling this
     * method to the room passed as an argument. Sets the connection on both
     * sides so the room passed is also connected to the room calling this
     * method
     *
     * @param dir Direction to set the connection in
     * @param connectedRoom Room to be connected to the room calling this method
     */
    public void setConnection(int dir, Room connectedRoom)
    {
        switch (dir)
        {
            case 1:
                northConnection = connectedRoom;
                connectedRoom.southConnection = this;
                break;
            case 2:
                southConnection = connectedRoom;
                connectedRoom.northConnection = this;
                break;
            case 3:
                eastConnection = connectedRoom;
                connectedRoom.westConnection = this;
                break;
            case 4:
                westConnection = connectedRoom;
                connectedRoom.eastConnection = this;
                break;
            //you put something in wrong exception
            default:
                break;
        }
    }
    //return a room in the direction specified

    /**
     *
     * @param direction Direction to check
     * @return Returns the room connected in the direction passed
     */
    public Room getConnection(int direction)
    {
        switch (direction)
        {
            case (1):
                return northConnection;
            case (2):
                return southConnection;
            case (3):
                return eastConnection;
            case (4):
                return westConnection;
            default:
                return null;
        }

    }

    /**
     *
     * @param item Adds the item to the room's inventory
     */
    public void addObject(Item item)
    {
        this.itemList.add(item);
    }

    /**
     *
     * @param item Adds the container to the room's inventory
     */
    public void addObject(ItemContainer item)
    {
        this.itemList.add(item);
    }

    /**
     *
     * @param npc Adds the character to the room's inventory
     */
    public void addObject(NPChar npc)
    {
        this.charList.add(npc);
    }

    /**
     *
     * @param index Removes item at the passed index
     */
    public void removeItem(int index)
    {
        itemList.remove(index);
    }

    /**
     *
     * @param item Removes all items matching the passed item
     */
    public void removeItem(Item item)
    {
        itemList.remove(item);
    }

    /**
     *
     * @return Returns the room's inventory
     */
    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    /**
     *
     * @param itemList Sets the room's inventory
     */
    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    //TODO Improve this. Seems redundant with the getItems function already written
    /**
     * Prints all items present in the room
     */
    public void printItems()
    {
        if (!itemList.isEmpty())
        {
            System.out.print("The following items are in this room: ");
            System.out.println(getItems());
        }
    }

    /**
     *
     * @return Returns a list of items in the room as a string
     */
    public String getItems()
    {
        String visibleItems = "";
        for (int i = 0; i < itemList.size(); i++)
        {

            if (itemList.get(i).getVisibility())
            {
                visibleItems += "\n -" + itemList.get(i).getName();
                //check to see if the object in the item list is a container
                if (itemList.get(i).getContainerStatus())
                {
                    //if it is an item list, this below method will run
                    ArrayList<Item> containerList = ((ItemContainer) itemList.get(i)).getItemList();
                    //add each item inside the container to the visible list, if it isn't invisible for some                        //reason
                    if (containerList != null)
                    {
                        for (int j = 0; j < containerList.size(); j++)
                        {
                            if (containerList.get(j).getVisibility())
                            {
                                visibleItems += "\n" + containerList.get(j).getName();
                            }
                        }
                    }

                }
            }
        }
        visibleItems += "\n";
        //for loop printing item list
        return visibleItems;
    }

    //Finds an item by name
    public Item findItem(String ItemName)
    {
        for (Item itemList1 : itemList)
        {
            if (itemList1.getName().equalsIgnoreCase(ItemName))
            {
                return itemList1;
            }
        }
        return null;
    }

    //Finds an character by name
    public NPChar findChar(String charName)
    {
        for (NPChar npc : charList)
        {
            if (npc.getName().equalsIgnoreCase(charName))
            {
                return npc;
            }
        }
        return null;
    }

    public void printChars()
    {
        if (!charList.isEmpty())
        {
            System.out.println("The following characters are in this room: ");
            System.out.println(getChars());
        }
    }

    public String getChars()
    {
        String visibleChars = "";
        if (!charList.isEmpty())
        {
            for (int i = 0; i < charList.size(); i++)
            {
                if (charList.get(i).getVisibility())
                {
                    visibleChars += "-" + charList.get(i).getName() + " \n";
                }
            }
        }
        return visibleChars;
    }

    //TODO Research this. XML tags a possiblility for descriptions and loger strings
    /**
     * Not currently in use
     *
     */
    public FileInputStream getFile()
    {
        try
        {
            FileInputStream roomFile = new FileInputStream("roomDescriptions.txt");
            return roomFile;
        } catch (FileNotFoundException e)
        {
            System.out.println("Room descriptions not found");
            return null;
        }
    }

    /**
     *
     * @return Checks whether the player can leave the room or not
     */
    public boolean isLeavable()
    {
        return leavable;
    }

    /**
     *
     * @param flag Sets whether the player can leave the room or not
     */
    public void setLeavable(boolean flag)
    {
        leavable = flag;
    }

    /**
     * Condition for leaving the room. Override this in all subclasses
     *
     * @param player Player object
     */
    public void metLeaveCond(Player player)
    {
    }

    /**
     * Override this in all subclasses
     *
     * @return Returns the leave condition
     */
    public String getLeaveCondition()
    {
        return null;
    }

    //return the enteredBefore variable
    /**
     * Used to check if the player has already been in a room. Prevents the
     * room's intro and events from being played every time the player enters a
     * room
     *
     * @return Returns whether the player has entered a room before or not
     */
    public boolean getEntered()
    {
        return enteredBefore;
    }

    /**
     *
     * @param enteredStatus Becomes true the first time the player enters a room
     */
    public void setEntered(boolean enteredStatus)
    {
        enteredBefore = enteredStatus;
    }

    /**
     *
     * @return Checks if the room has an event
     */
    public boolean hasEvent()
    {
        return event;
    }

    /**
     *
     * @param flag Sets if the room has an event
     */
    public void setEventFlag(boolean flag)
    {
        event = flag;
    }
    //Override this in all subclasses

    /**
     * Executes the event if the current room has one
     *
     * @param manager Passed in order to set certain flags
     * @param player Passed in order to modify the player's status or inventory
     */
    public void doEvent(GameManager manager, Player player)
    {

    }
}
