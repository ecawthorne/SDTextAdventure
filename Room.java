//Basic room class. Every room inherits from this.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
abstract public class Room
{
//    file variable for containing descriptions
//    Gets file and handles possible exception. Do this in third sprint
//    final FileInputStream ROOMDESCRIPTIONS = getFile();

    private ArrayList<Item> itemList = new ArrayList<>();
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

    //direction variables for next rooms
    Room northConnection = null;
    Room southConnection = null;
    Room eastConnection = null;
    Room westConnection = null;

    Room()
    {

        this("a generic room", "The room is generic.");
    }

    Room(String n, String intro)
    {
        setName(n);
        setIntro(intro);
      
    public void setName(String name)
    {
        this.name = name;
    }

    public String getIntro()
    {
        return this.intro;
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

    public String getLeaveCond()
    {
        return leaveCond;
    }

    public void setLeaveCond(String leaveCond)
    {
        this.leaveCond = leaveCond;
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
    //unfinished method below searches files

//    private String searchFile(int descLocator)
//    {
//        String text = null;
//       Scanner search = new Scanner(ROOMDESCRIPTIONS);
//        while (search.hasNext())
//        {
//
//        }
//        search.close();
//        return text;
//    }
    //setter method for connections
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

    public void addItem(Item item)
    {
        itemList.add(item);
    }
    
    public void addItem(ItemContainer item)
    {
        itemList.add(item);
    }
    
    public void removeItem(int index)
    {
        itemList.remove(index);
    }
    
    public void removeItem(Item item)
    {
        itemList.remove(item);
    }
    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    public void printItems()
    {
        System.out.println(getItems());
    }

    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    public String getItems()
    {
        String visibleItems = "";
        for (int i = 0; i < itemList.size(); i++)
        {

            if (itemList.get(i).getVisibility())
            {
                visibleItems += "\n -" + itemList.get(i).getName();
                //check to see if the object in the item list is a container
                if(itemList.get(i).getContainerStatus())
                {
                    //if it is an item list, this below method will run
                    ArrayList<Item> containerList = ((ItemContainer) itemList.get(i)).getItemList();
                    //add each item inside the container to the visible list, if it isn't invisible for some reason
                    if(containerList != null)
                    {
                        for(int j = 0; j < containerList.size(); j++)
                        {
                            if(containerList.get(j).getVisibility())
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
    //Research this. XML tags a possiblility for descriptions and loger strings
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

    public boolean isLeavable()
    {
        return leavable;
    }

    public void setLeavable(boolean flag)
    {
        leavable = flag;
    }

    //Override this in all subclasses
    //Condition for leaving the room
    public void metLeaveCond(Player player)
    {
    }

    //Override this in all subclasses
    //Riddles to open locks, leave rooms, get items, etc.
    public void doRiddle(Player player)
    {

    }

    //Override this in all subclasses
    public String getLeaveCondition()
    {
        return null;
    }

    //return the enteredBefore variable
    public boolean getEntered()
    {
        return enteredBefore;
    }
    public void setEntered(boolean enteredStatus)
    {
        enteredBefore = enteredStatus;
    }
}
