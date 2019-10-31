
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
        if(!currentRoom.getEntered())
        {            
            System.out.println(currentRoom.getIntro() + "\n"); //returns null for rooms besides initial room because we don't set it.
            currentRoom.setEntered(true);
        }
        System.out.print("The following items are in this room: ");
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
    }

    public Item find(String toFind)
    {
        ArrayList<Item> roomItemList = currentRoom.getItemList();
        ArrayList<Item> playerItemList = player.getItemList();
        toFind = toFind.toUpperCase();
        Item toReturn = null;
        boolean itemFound = false;
        //search for the item
        for(int i = 0; i < roomItemList.size(); i++)
        {
            if(roomItemList.get(i).getName().toUpperCase().equals(toFind))
            {
                itemFound = true;
                toReturn = roomItemList.get(i);
            }
        }
        for(int i = 0; i < playerItemList.size(); i++)
        {
            if (playerItemList.get(i).getName().toUpperCase().equals(toFind))
            {
                itemFound = true;
                toReturn = playerItemList.get(i);
            }
        }
        //return either the item or a null with a message
        if(itemFound)
        {
            return toReturn;
        }
        else
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
        //Improve this in future sprints
        //Research possible better ways to parse the players input
        if (input.isEmpty())
        {
            System.out.println("Enter a command or type \'h\' for help");
        } else
        {
            if (input.length() == 1)
            {
                switch (input.charAt(0))
                {
                    case 'n':
                        movePlayer(1);
                        break;
                    case 's':
                        movePlayer(2);
                        break;
                    case 'e':
                        movePlayer(3);
                        break;
                    case 'w':
                        movePlayer(4);
                        break;
                    case 'u':
                        movePlayer(5);
                        break;
                    case 'd':
                        movePlayer(6);
                        break;
                    case 'l':
                        look();
                        break;
                    //Prints the players inventory
                    case 'i':
                        if (!player.getItemList().isEmpty())
                        {
                            System.out.println("Your inventory: ");
                            for (int i = 0; i < player.getItemList().size(); i++)
                            {
                                System.out.println("-" + player.getItemList().get(i).getName());
                            }
                        } else
                        {
                            System.out.println("You dont have any items!");
                        }
                        break;
                    case 'h':
                        getHelp();
                        break;
                    case 'q':
                        quitGame();
                        break;
                    default:
                        System.out.println("I don't understand that, try again.");
                        break;
                }
                //Splits the users input into an array with the second element being
                //the item the user attempts to take
            } else if(input.toUpperCase().split(" ")[0].equals("GO"))
            {
                String[] inputArray = input.toUpperCase().split(" ");
                inputArray = forceArraySize(inputArray);
                if(inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    switch(inputArray[1])
                    {
                    case("NORTH"):
                    case("N"):
                        movePlayer(1);
                        break;
                    case("SOUTH"):
                    case("S"):
                        movePlayer(2);
                        break;
                    case("EAST"):
                    case("E"):
                        movePlayer(3);
                        break;
                    case("WEST"):
                    case("W"):
                        movePlayer(4);
                        break;
                    case("UP"):
                    case("U"):
                        movePlayer(5);
                        break;
                    case("DOWN"):
                    case("D"):
                        movePlayer(6);
                        break;
                    default:
                        System.out.println("That's not a valid direction!");
                        break;
                    }
                }
                else
                {
                    System.out.println("Please enter a direction after the \'go\' command.");
                }

            }else if (input.toUpperCase().split(" ")[0].equals("TAKE"))//This will probably cause errors in some cases
            {
                String[] inputArray = input.toUpperCase().split(" ");
                inputArray = forceArraySize(inputArray);
                if(inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    takeItem(inputArray[1]);
                }
                else
                {
                    System.out.println("Take what?\n>");
                    takeItem(keyboard.nextLine());
                }

            } else if (input.toUpperCase().split(" ")[0].equals("OPEN"))//This will probably cause errors in some cases
            {
                String[] inputArray = input.toUpperCase().split(" ");
                inputArray = forceArraySize(inputArray);
                if(inputArray[1] != null && !inputArray[1].isEmpty())
                {                    
                    openItem(inputArray[1]);
                }
                else
                {
                    System.out.println("Open what?\n>");
                    openItem(keyboard.nextLine());
                }
            } else if (input.toUpperCase().split(" ")[0].equals("DROP"))
            {             
                String[] inputArray = input.toUpperCase().split(" ");
                inputArray = forceArraySize(inputArray);
                if(inputArray[1] != null && !inputArray[1].isEmpty())
                {                    
                    dropItem(inputArray[1]);
                }
                else
                {
                    System.out.println("Drop what?\n>");
                    dropItem(keyboard.nextLine());
                }
            } else if(input.toUpperCase().split(" ")[0].equals("EXAMINE"))
            {             
                String[] inputArray = input.toUpperCase().split(" ");
                inputArray = forceArraySize(inputArray);
                if(inputArray[1] != null && !inputArray[1].isEmpty())
                {
                    examineItem(inputArray[1]);  
                }
                else
                {
                    System.out.println("Examine what?");
                    examineItem(keyboard.nextLine());
                }
                              
            } else
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

        if(splitArray.length >= fixedArray.length)
        {
            fixedArray = splitArray;
        }
        else
        {
            for(int i = 0; i < splitArray.length; i++)
                fixedArray[i] = splitArray[i];
        }

        return fixedArray;
    }

    public void openItem(String toOpen)
    {
        //find and open an object if it's openable
        if(find(toOpen) != null)
        {
            //have to send the current room to the open function so that it will add the items inside the container to the room's itemlist
            find(toOpen).open(currentRoom);
        }
    }
    public void dropItem(String toDrop)
    {
        if(find(toDrop) != null)
        {
            player.dropItem(currentRoom, find(toDrop));
            System.out.println("You've dropped the " + find(toDrop).getName() + ".");
        }
    }

    //method below to simplify finding items so that we don't have to code all this later on
    //i've also simplified the methods that used the code
   
    public void look()
    {
        if(currentRoom.getInternalDesc() != null)
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
        //turn the above into a method at some point

        System.out.print("The following items are in the room:");
        System.out.println(currentRoom.getItems());
    }
    //Examines the desired item regardless of whether it's in the room or the 
    //players inventory
    public void examineItem(String toExamine)
    {
        if(find(toExamine) != null)
        {
            System.out.println(find(toExamine).getDesc());
        }
    }

    public void takeItem(String toTake)
    {
        if(find(toTake) != null)
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
        } else
        {
            System.out.println("You can't move in that direction.");
        }
    }

    //Constructs the game map and sets all connections
    public void constructRooms()
    {
        //hardcode rooms built for game
        //example below
        InitialRoom mainRoom = new InitialRoom();
        VillageRoom village = new VillageRoom();
        currentRoom = mainRoom;
        //Direction should be checked. Just setting in order to test
        mainRoom.setConnection(1, village);
        //Are we making each room a unique object?        
        //Room village = new Room("the village", 2, null, mainRoom);
        //example of adding items below, to be moved to another method or class
        village.addItem(new Item("foo", "jar"));
    }
    //Likely what we want to use going forward
    //Consider adding different game over messages
    private void quitGame()
    {
        player.setAlive(false);
    }

    public boolean isPlayerAlive()
    {
        return player.isAlive();
    }

}
