
import java.util.Scanner;

/**
 *
 * @author Chase
 */
public class GameManager
{
    //room player is currently in

    private Room currentRoom = null;
    String input = null;
    private boolean gameOver = false;
    Player player = new Player();

    GameManager()
    {
        constructRooms();
    }

    public void EnterRoom()
    {
        System.out.println(currentRoom.getIntro());
        System.out.println("The following items are in this room: ");
        currentRoom.getItemList().forEach((n) -> System.out.println(n.getName()));

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

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

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
                    case 'q':
                        quitGame();
                        break;
                    default:
                        break;
                }
            } else if (input.toLowerCase().split(" ")[0].equals("take"))//This will probably cause errors in some cases
            {
                //Move this into a method at some point and fix likely error
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
            } else if (input.toLowerCase().split(" ")[0].equals("open"))//This will probably cause errors in some cases
            {
                
            } else
            {
                System.out.println("I don't understand that. Try again");
            }
        }
    }

    public void look()
    {
        System.out.println(currentRoom.getRoomDesc());
        System.out.println(currentRoom.getItems());
    }

    public void examine(/**/)
    {
        System.out.println(/*Item description*/);
    }

    public void getHelp()
    {
        System.out.println("Helpful info");
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

    //Possibly redundant
    private void quitGame()
    {
        player.setAlive(false);
    }

    public boolean isPlayerAlive()
    {
        return player.isAlive();
    }

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
