
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Chase
 */
public class GameManager
{

    Scanner keyboard = new Scanner(System.in);
    //The room the player is currently in. Player starts in his house which is InitialRoom
    private Room currentRoom = null;
    String input = null;
    //Probably redundant. Check and remove if it is
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
    //No arguements passed when creating GameManager. Creates the currently implemented map
    GameManager()
    {
        constructRooms();
    }

    //Description of then room given to the player when he first enters
    //Consider changing so that intro only prints on first entrance while others print everytime
    public void EnterRoom()
    {
        System.out.println("You have entered " + currentRoom.getName() + "\n");
        if (!currentRoom.getEntered())
        {
            System.out.println(currentRoom.getIntro() + "\n"); //returns null for rooms besides initial room because we don't set it.
            currentRoom.setEntered(true);
        }
        //Iterates through the items in the rooms, printing each elements name
        currentRoom.printItems();
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
        if(currentRoom.hasEvent())
        {
            currentRoom.doEvent(player);
            eventLive = true;
        }
    }

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

    //Posssibly redundant again
    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    //Checks that  the input is valid
    //and performs actions depending on entered values
    public void parseInput(String input)
    {
        String[] command = input.split(" ");
        command[0] = synFinder.getCommand(command[0]);
        //Improve this in future sprints
        //Research possible better ways to parse the players input
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
            } else if (command[0].equalsIgnoreCase("take"))//This will probably cause errors in some cases
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

            } else if (command[0].equalsIgnoreCase("open"))//This will probably cause errors in some cases
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
            } else if (command[0].equalsIgnoreCase("look"))
            {
                look();
            } else if(command[0].equalsIgnoreCase("help"))
            {
                getHelp();
            }
            else if(command[0].equalsIgnoreCase("quit"))
            {
                quitGame();
            }
            else
            {
                System.out.println("I don't understand that. Try again");
            }
        }
    }

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

    public void openItem(String toOpen)
    {
        //find and open an object if it's openable
        if (find(toOpen) != null)
        {
            //have to send the current room to the open function so that it will add the items inside the container to the room's itemlist
            find(toOpen).open(currentRoom);
        }
    }

    public void dropItem(String toDrop)
    {
        if (find(toDrop) != null)
        {
            player.dropItem(currentRoom, find(toDrop));
            System.out.println("You've dropped the " + find(toDrop).getName() + ".");
        }
    }

    //method below to simplify finding items so that we don't have to code all this later on
    //i've also simplified the methods that used the code
    public void look()
    {
        if (currentRoom.getInternalDesc() != null)
        {
            System.out.println(currentRoom.getInternalDesc());
        }
        //turn the below into a method at some point
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
    }

    //Examines the desired item regardless of whether it's in the room or the 
    //players inventory
    public void examineItem(String toExamine)
    {
        if (find(toExamine) != null)
        {
            System.out.println(find(toExamine).getDesc());
        }
    }

    public void takeItem(String toTake)
    {
        if (find(toTake) != null)
        {
            player.addItem(find(toTake));
            System.out.println("You got the " + find(toTake).getName() + ".");
            currentRoom.removeItem(find(toTake));
        }
    }

    public void getHelp()
    {
        System.out.println(HELPMESSAGE);
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
        EnterRoom();
    }

    //Most rooms will have a condition that needs to be met in order to leave it
    //this checks if the condition has been met and allows the player to move it
    //it has
    public void movePlayer(int direction)
    {
        Room toMove = currentRoom.getConnection(direction);
        currentRoom.metLeaveCond(player);
        if (!currentRoom.isLeavable())
        {
            System.out.println(currentRoom.getLeaveCondition());
        } else if (toMove != null)
        {
            setCurrentRoom(toMove);
            EnterRoom();
        } else
        {
            System.out.println("You can't move in that direction.");
        }
    }

    //Constructs the game map and sets all connections
    final public void constructRooms()
    {
        //hardcode rooms built for game
        //example below
        InitialRoom mainRoom = new InitialRoom();
        VillageRoom village = new VillageRoom();
        currentRoom = mainRoom;
        //Direction should be checked. Just setting in order to test
        mainRoom.setConnection(1, village);
    }

    //Likely what we want to use going forward
    //Consider adding different game over messages
    private void quitGame()
    {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    public boolean isPlayerAlive()
    {
        return player.isAlive();
    }
    public boolean doingEvent()
    {
        return eventLive;
    }

}