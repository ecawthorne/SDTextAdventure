//Basic room class. Every room inherits from this.

import java.util.Map;

/**
 *
 * @author Eric
 */
abstract public class Room
{	
	//file variable for containing descriptions
	final FileInputStream ROOMDESCRIPTIONS = new FileInputStream("RoomDescriptions.txt");

	private ArrayList<Item> itemList = new ArrayList<Item>();
    //Name of this room
    private String name = null;
    //Introduction to the room if there is one
    private String intro = null;
    //Description of the room
    private String internalDesc = null;
    //Description of the room as seen form adjacent rooms
    private String externalDesc = null;
    //Directions the player can move in and the description of the location
    private Map<String, Room> doors = null;
    //Keep the player in a room until a condition is met
    private boolean exit = false;
	
	//direction variables for next rooms
	Room northConnection = null;
	Room southConnection = null;
	Room eastConnection = null;
	Room westConnection = null;
	
	Room(string n, int loc)
	{
		setName(n);
		setIntro(searchFile(loc));
		
	}
	Room(string n, int descLoc, Room connectedRoom)
	{
		setName(n);
		setIntro(searchFile(descLoc));
		//set connected rooms in each required direction
		setConnection(1, connectedRoom);		
	}
	
	
	Room(string n, int loc, Room conRoom1, Room conRoom2)
	{
		setName(n);
		setIntro(searchFile(loc));
		//set connected rooms in each required direction
		setConnection(1, conRoom1);
		setConnection(2, conRoom2);
	}
	Room(string n, int loc, Room conRoom1, Room conRoom2, Room conRoom3)
	{
		setName(n);
		setIntro(searchFile(loc));
		//set connected rooms in each required direction
		setConnection(1, conRoom1);
		setConnection(2, conRoom2);
		setConnection(3, conRoom3);
	}
	Room(string n, int loc, Room conRoom1, Room conRoom2, Room conRoom3, Room conRoom4)
	{
		setName(n);
		setIntro(searchFile(loc));
		//set connected rooms in each required direction
		setConnection(1, conRoom1);
		setConnection(2, conRoom2);
		setConnection(3, conRoom3);
		setConnection(4, conRoom4);
	}

    public void setName(String name)
    {
        this.name = name;
    }

    public void getIntro()
    {
        System.out.println(this.intro);
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
	public string searchFile(int descLocator)
	{
		ROOMDESCRIPTIONS.
	}
	//setter method for connections
	public void setConnection(int dir, Room connectedRoom)
	{
		if(dir = 1)
		{
			northConnection = connectedRoom;
			connectedRoom.southConnection = this;
		}
		else if(dir = 2)
		{
			southConnection = connectedRoom;
			connectedRoom.northConnection = this;
		}
		else if(dir = 3)
		{
			eastConnection = connectedRoom;
			connectedRoom.westConnection = this;
		}
		else if(dir = 4)
		{
			westConnection = connectedRoom;
			connectedRoom.eastConnection = this;
		}
		else
		{
			//you put something in wrong exception
		}
	}
	//return a room in the direction specified
	public Room getConnection(int direction)
	{
		switch(direction)
		{
			case(1):
				return northConnection;
			case(2):
				return southConnection;
			case(3):
				return eastConnection;
			case(4):
				return westConnection;
			default:
				return null;
			break;
		}
		
	}
	
	public addItem(Item item)
	{
		itemList.add(item);
	}
	
	public printItems()
	{
		//for loop printing item list
	}
}


