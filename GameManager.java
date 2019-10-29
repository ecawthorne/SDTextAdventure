
import java.util.Scanner;

/**
 *
 * @author Chase
 */
public class GameManager
{

    //The room the player is currently in. Player starts in his house which is InitialRoom
    private Room currentRoom = null;
    String input = null;
    //Probably redundant. Check and remove if it is
    private boolean gameOver = false;
    //Player Object. Name, player inventory, and player status stored here
    Player player = new Player();
    //Duplicated from SamuraiStrike.java. Probably a better way to do this.
    final String HELPMESSAGE = "You can use the following commands: \n"
            + "\'n\' will head north, when possible. \n"
            + "\'s\' will head south, when possible. \n"
            + "\'e\' will head east, when possible. \n"
            + "\'w\' will head west, when possible. \n"
            + "\'u\' will head up, when possible. \n"
            + "\'d\' will head down, when possible. \n"
            + "\'examine <item>\' will will give a description of the item\n"
            + "\'take <item>\' will allow you to pick up items in the room\n"
            + "\'l\' will look, providing a description of the room. \n"
            + "\'q\' will quit the game. \n";

    //No arguements passed when creating GameManager. Creates the currently implemented map
    GameManager()
    {
        constructRooms();
    }

    //Description of then room given to the player when he first enters
    //Consider changing so that intro only prints on first entrance while others print everytime
    public void EnterRoom()
    {
        System.out.println(currentRoom.getIntro());
        System.out.println("The following items are in this room: ");
        //Iterates through the items in the rooms, printing each elements name
        currentRoom.getItemList().forEach((n) -> System.out.println(n.getName()));
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

        System.out.println("\n");
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

    //Remove this when possible. Probably unneeded
    public void getInput()
    {
        Scanner playerInput = new Scanner(System.in);
        this.input = playerInput.nextLine();
        playerInput.close();
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
                    case 'w':
                        movePlayer(2);
                        break;
                    case 'e':
                        movePlayer(3);
                        break;
                    case 's':
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
                            for (int i = 0; i < player.getItemList().size(); i++)
                            {
                                if (player.getItemList().get(i).getVisibility())
                                {
                                    System.out.println(player.getItemList().get(i).getName());
                                }
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
                        break;
                }
                //Splits the users input into an array with the second element being
                //the item the user attempts to take
            } else if (input.toLowerCase().split(" ")[0].equals("take"))//This will probably cause errors in some cases
            {
                //Move this into a method at some point and fix likely error
                //Research hiding or storing items in other items
                String[] inputArray = input.toLowerCase().split(" ");
                for (int i = 0; i < currentRoom.getItemList().size(); i++)
                {
                    if (currentRoom.getItemList().get(i).getName().toLowerCase().equals(inputArray[1]))
                    {
                        player.addItem(currentRoom.getItemList().get(i));
                        //Overload getItemList in future
                        System.out.println("You got the " + currentRoom.getItemList().get(i).getName());
                        currentRoom.removeItem(i);
                        break; //Maybe find a better way to do this
                    }
                }
                //Implement in future sprint
            } else if (input.toLowerCase().split(" ")[0].equals("open"))//This will probably cause errors in some cases
            {
                //Make certain items openable using ItemContainer
            } else if (input.toLowerCase().split(" ")[0].equals("examine"))
            {
                String[] inputArray = input.toLowerCase().split(" ");
                for (int i = 0; i < currentRoom.getItemList().size(); i++)
                {
                    if(currentRoom.getItemList().get(i).getName().toLowerCase().equals(inputArray[1]))
                    {
                        System.out.println(currentRoom.getItemList().get(i).getDesc());
                    }
                }
            } else
            {
                System.out.println("I don't understand that. Try again");
            }
        }
    }

    public void look()
    {
        
        System.out.println(currentRoom.getInternalDesc());
        System.out.println("You can see these items in the room:");
        for(int i = 0; i < currentRoom.getItemList().size(); i++)
        {
            System.out.println(currentRoom.getItemList().get(i).getName());
        }
    }
    //Examines the desired item regardless of whether it's in the room or the 
    //players inventory
    public void examine(String toExamine)
    {
        for (int i = 0; i < currentRoom.getItemList().size(); i++)
        {
            if (currentRoom.getItemList().get(i).getName().equals(toExamine))
            //These calls are killing me inside. Change this at some point to look nicer
            {
                System.out.println(currentRoom.getItemList().get(i).getDesc());
            } else if (player.getItemList().get(i).getName().equals(toExamine))
            //These calls are killing me inside. Change this at some point to look nicer
            {
                System.out.println(player.getItemList().get(i).getDesc());
            }
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
    //Probably going to remove this soon
    public boolean doAction(String parsedInput)
    {
        boolean done = false;
        switch (parsedInput.charAt(0))
        {
            case 'w':
            case 'n':
            case 'e':
            case 's':
            case 'u':
            case 'd':
                //this currently only checks for if the room exists. need to call the move in that direction variable.
                done = currentRoom.changeRoom(parsedInput);
                break;
            //examine room
            case 'l':
                done = currentRoom.getRoomDesc();
                //future update will check if 'l' is followed by anything, and then provide a description of whatever is being looked at
                break;
            //implement quit functionality
            case 'q':
                player.setAlive(false);
                break;
            //implement help function
            case 'h':
                //displayHelpMessage;
                break;
            default:
                break;
        }
        return done;
    }

}
