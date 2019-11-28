
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds almost all of the logic of this game. Holds the player
 * object, and handles input given to the program.
 *
 * @author Chase
 */
public class GameManager
{

    Scanner keyboard = new Scanner(System.in);
    //The room the player is currently in. Player starts in his house which is InitialRoom
    private Room currentRoom = null;
    String input = null;
    //TODO Probably redundant. Check and remove if it is
    private boolean gameOver = false;
    //Player Object. Name, player inventory, and player status stored here
    Player player = new Player();
    //Stores a list of possible synonyms. Should only be used inside of parseInput
    SynonymFinder synFinder = new SynonymFinder();
    //Duplicated from SamuraiStrike.java. Probably a better way to do this.
    final String HELPMESSAGE = "You can use the following commands: "
            + "-\'go\' will go in the cardinal direction you enter\n"
            + "-\'take <item>\' will allow you to pick up items in the room\n"
            + "-\'drop <item\' will remove an item from your inventory."
            + "-\'i\' will display your inventory."
            + "-\'open <item>\' will allow you to open a container.\n"
            + "-\'examine <item>\' will provide more detail about an item."
            + "-\'l\' will look, providing a description of the room. \n"
            + "-\'q\' will quit the game. \n";

    private boolean eventLive = false;

    /**
     * Default and only constructor. Creates the currently implemented map
     */
    GameManager()
    {
        constructRooms();
    }

    //Description of then room given to the player when he first enters
    //Consider changing so that intro only prints on first entrance while others print everytime
    /**
     * Executes when the player enters a room. Runs through events in the room
     * if there they are present. Lists intro to the room and and items present
     */
    public void EnterRoom()
    {
        System.out.println("You have entered " + currentRoom.getName() + "\n");
        if (!currentRoom.getEntered())
        {
            System.out.println(currentRoom.getIntro() + "\n"); //returns null for rooms besides initial room                //because we don't set it.
            currentRoom.setEntered(true);
        }
        //Iterates through the items and characters in the rooms, printing each elements name
        currentRoom.printItems();
        currentRoom.printChars();
        //Checks if the current room has exits in each direction, if it does,
        //print the room name in that direction
        for (int i = 1; i <= 4; i++)
        {
            String direction;
            switch (i)
            {
                case (1):
                    direction = "north";
                    break;
                case (2):
                    direction = "south";
                    break;
                case (3):
                    direction = "east";
                    break;
                case (4):
                    direction = "west";
                    break;
                default:
                    direction = "ERROR: PLEASE REPORT DIRECTION BUG TO DEVELOPER.";
                    break;
            }
            Room connectedRoom = currentRoom.getConnection(i);
            if (connectedRoom != null)
            {
                System.out.println(connectedRoom.getName() + " is to your " + direction + ".");
            }
        }
        if (currentRoom.hasEvent())
        {
            setEventLive(true);
            currentRoom.doEvent(this, player);

        }
    }

    /**
     * Loop through items in the room and items in the inventory returns null if
     * nothing found, or the item
     * <p>
     * Always check if method returns null first, or errors will occur.
     *
     * @param toFind string name of item to be found
     * @return item object matching searched name, or null
     */
    public Item find(String toFind)
    {
        ArrayList<Item> roomItemList = currentRoom.getItemList();
        ArrayList<Item> playerItemList = player.getItemList();
        Item toReturn = null;
        boolean itemFound = false;
        //search for the item
        for (int i = 0; i < roomItemList.size(); i++)
        {
            if (roomItemList.get(i).getName().equalsIgnoreCase(toFind))
            {
                itemFound = true;
                toReturn = roomItemList.get(i);
            }
        }
        for (int i = 0; i < playerItemList.size(); i++)
        {
            if (playerItemList.get(i).getName().equalsIgnoreCase(toFind))
            {
                itemFound = true;
                toReturn = playerItemList.get(i);
            }
        }
        //return either the item or a null with a message
        if (itemFound)
        {
            return toReturn;
        } else
        {
            System.out.println("That item doesn't exist!");
            return toReturn;
        }

    }

    /**
     * Parses input and executes command. Holds most of the logic for the game
     *
     * @param input Command to be parsed
     */
    public void parseInput(String input)
    {
        String[] command = input.split(" ");
        command[0] = synFinder.getCommand(command[0]);
        if (command[0].isEmpty())
        {
            System.out.println("Enter a command or type \'h\' for help");
        } else
        {
            if (command[0].equalsIgnoreCase("go"))
            {
                String[] inputArray = command;
                inputArray = forceArraySize(inputArray);
                if (inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    switch (command[1])
                    {
                        case ("north"):
                            movePlayer(1);
                            break;
                        case ("south"):
                            movePlayer(2);
                            break;
                        case ("east"):
                            movePlayer(3);
                            break;
                        case ("west"):
                            movePlayer(4);
                            break;
                        case ("up"):
                            movePlayer(5);
                            break;
                        case ("down"):
                            movePlayer(6);
                            break;
                        default:
                            System.out.println("That's not a valid direction!");
                            break;
                    }
                }
            } else if (command[0].equalsIgnoreCase("take"))
            {
                String[] inputArray = command;
                inputArray = forceArraySize(inputArray);
                if (inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    takeItem(inputArray[1]);
                } else
                {
                    System.out.println("Take what?\n>");
                    takeItem(keyboard.nextLine());
                }

            } else if (command[0].equalsIgnoreCase("open"))
            {
                String[] inputArray = command;
                inputArray = forceArraySize(inputArray);
                if (inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    openItem(inputArray[1]);
                } else
                {
                    System.out.println("Open what?\n>");
                    openItem(keyboard.nextLine());
                }
            } else if (command[0].equalsIgnoreCase("drop"))
            {
                String[] inputArray = command;
                inputArray = forceArraySize(inputArray);
                if (inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    dropItem(inputArray[1]);
                } else
                {
                    System.out.println("Drop what?\n>");
                    dropItem(keyboard.nextLine());
                }
            } else if (command[0].equalsIgnoreCase("examine"))
            {
                String[] inputArray = command;
                inputArray = forceArraySize(inputArray);
                if (inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    examineItem(inputArray[1]);
                } else
                {
                    System.out.println("Examine what?");
                    examineItem(keyboard.nextLine());
                }
            } else if (command[0].equalsIgnoreCase("talk"))
            {
                String[] inputArray = command;
                inputArray = forceArraySize(inputArray);
                if (inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    talkTo(inputArray[1]);
                }
            } else if (command[0].equalsIgnoreCase("look"))
            {
                look();
            } else if (command[0].equalsIgnoreCase("help"))
            {
                getHelp();
            } else if (command[0].equalsIgnoreCase("quit"))
            {
                quitGame();
            } else
            {
                System.out.println("I don't understand that. Try again");
            }
        }
    }

    /**
     * Used to prevent out of bounds exceptions and also make comparing inputs
     * easier.
     * <p>
     * Called in parseInput() method.
     *
     * @param splitArray array to be broken into two
     * @return the corrected array
     */
    public String[] forceArraySize(String[] splitArray)
    {
        String[] fixedArray = new String[2];
        fixedArray[0] = null;
        fixedArray[1] = null;

        if (splitArray.length >= fixedArray.length)
        {
            fixedArray = splitArray;
        } else
        {
            for (int i = 0; i < splitArray.length; i++)
            {
                fixedArray[i] = splitArray[i];
            }
        }

        return fixedArray;
    }

    /**
     * Opens a specified container (if it exists) and unloads all the contents
     * into the room itemList
     *
     * @param toOpen item to be located and opened
     */
    public void openItem(String toOpen)
    {
        //find and open an object if it's openable
        if (find(toOpen) != null)
        {
            //have to send the current room to the open 
            //function so that it will add the items inside 
            //the container to the room's itemlist
            find(toOpen).open(currentRoom);
        }
    }

    /**
     * Drops a specified item (if it exists) and adds it to the room itemList
     * ToDo: fix drop method so a player cannot drop an item they don't have
     *
     * @param toDrop item to be removed from player itemlist and added to room
     */
    public void dropItem(String toDrop)
    {
        if (find(toDrop) != null)
        {
            player.dropItem(currentRoom, find(toDrop));
            System.out.println("You've dropped the " + find(toDrop).getName() + ".");
        }
    }

    /**
     * Prints description of current room Describes the rooms in each direction,
     * if they exist Currently only prints the name of the room
     */
    public void look()
    {
        if (currentRoom.getInternalDesc() != null)
        {
            System.out.println(currentRoom.getInternalDesc());
        }
        for (int i = 1; i <= 4; i++)
        {
            String direction;
            switch (i)
            {
                case (1):
                    direction = "north";
                    break;
                case (2):
                    direction = "south";
                    break;
                case (3):
                    direction = "east";
                    break;
                case (4):
                    direction = "west";
                    break;
                default:
                    direction = "ERROR: PLEASE REPORT DIRECTION BUG TO DEVELOPER.";
                    break;
            }
            Room connectedRoom = currentRoom.getConnection(i);
            if (connectedRoom != null)
            {
                System.out.println(connectedRoom.getName() + " is to your " + direction + ".");
            }
        }
        currentRoom.printItems();
        currentRoom.printChars();
    }

    /**
     * Finds a targeted item and prints its description
     *
     * @param toExamine string name of item to be found and examined
     */
    public void examineItem(String toExamine)
    {
        if (find(toExamine) != null)
        {
            System.out.println(find(toExamine).getDesc());
        }
    }

    /**
     * Adds item, if it exists, by string name to player itemlist
     *
     * @param toTake string name of item to add to inventory
     */
    public void takeItem(String toTake)
    {
        if (find(toTake) != null)
        {
            if (!find(toTake).isMovable())
            {
                System.out.println("You can't move that");
            } else
            {
                player.addItem(find(toTake));
                System.out.println("You got the " + find(toTake).getName() + ".");
                currentRoom.removeItem(find(toTake));
            }
        }
    }

    //Talks to the character, set the talked to flag and kill the character if necessary
    public void talkTo(String name)
    {
        if (currentRoom.findChar(name) != null && currentRoom.findChar(name).isVisible())
        {
            System.out.println(currentRoom.findChar(name).getDIALOGUE());
            currentRoom.findChar(name).setTalkedTo(true);
            if (currentRoom.findChar(name).killed())
            {
                currentRoom.findChar(name).killChar();
            }
        } else
        {
            System.out.println("That character doesn't exist!");
        }
    }

    /**
     * Prints the help message
     */
    public void getHelp()
    {
        System.out.println(HELPMESSAGE);
    }

    /**
     *
     * @return Returns the current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * @param currentRoom new room object to be defined as current room
     */
    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
        EnterRoom();
    }

    /**
     * If the room has a condition to leave, it is checked Allows the player to
     * move if condition has been met Otherwise, it prints what needs to be
     * done. If a room doesn't exist, then it informs the player
     *
     * @param direction Direction to move
     */
    public void movePlayer(int direction)
    {
        Room toMove = currentRoom.getConnection(direction);
        //Checks that the condition for leaving the room has been met and allows the player
        //to leave if the condition has been met
        currentRoom.metLeaveCond(player);

        if (!currentRoom.isLeavable())
        {
            System.out.println(currentRoom.getLeaveCondition());
        } else if (toMove != null)
        {
            setCurrentRoom(toMove);
            //i think this enterroom function was being double called?
            //EnterRoom();
        } else
        {
            System.out.println("You can't move in that direction.");
        }
    }

    /**
     * Constructs the initial game map and sets all connections
     */
    final public void constructRooms()
    {
        //hardcode rooms built for game
        //example below
        InitialRoom mainRoom = new InitialRoom();
        VillageRoom village = new VillageRoom();
        ForestRoom forest = new ForestRoom();
        currentRoom = mainRoom;
        //Direction should be checked. Just setting in order to test
        mainRoom.setConnection(1, village);
        village.setConnection(1, forest);
    }

    //ToDo: Consider adding different game over messages
    private void quitGame()
    {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    /**
     *
     * @return Player status
     */
    public boolean isPlayerAlive()
    {
        return player.isAlive();
    }

    /**
     *
     * @return If the player is currently in an event
     */
    public boolean doingEvent()
    {
        return eventLive;
    }

    /**
     *
     * @param eventStatus Sets this flag when the player enters an event
     */
    public void setEventLive(boolean eventStatus)
    {
        eventLive = eventStatus;
    }

}
