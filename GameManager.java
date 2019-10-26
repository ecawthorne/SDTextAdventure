
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
        System.out.println(currentRoom.getItems());
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

    public void parseInput(String input)
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
                case 'h':
                    getHelp();
                case 'q':
                    quitGame();
                    break;
                default:
                    break;
            }
        } else if (input.toLowerCase().split(" ")[0].equals("take"))//This will cause errors in some cases
        {
            String[] inputArray = input.toLowerCase().split(" ");
            for (int i = 0; i < currentRoom.getItemList().size(); i++)
            {
                if (currentRoom.getItemList().get(i).getName().equals(inputArray[1]))
                {
                    //This probably won't work but check just in case i.e. reference vs. copy
                    player.getItemList().add(currentRoom.getItemList().get(i));
                    currentRoom.getItemList().remove(i);
                    break;//Maybe find a better way to do this
                }
            }
        } else
        {
            System.out.println("I don't understand that. Try again");
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
        if(!currentRoom.isLeavable())
        {
            System.out.println(currentRoom.getLeaveCondition());
        }
        if (toMove != null)
        {
            setCurrentRoom(toMove);
        } else
        {
            System.out.println("You can't move in that direction.");
        }
    }

    public void constructRooms()
    {
        //hardcode rooms built for game
        //example below
        InitialRoom mainRoom = new InitialRoom();
        VillageRoom village = new VillageRoom();
        currentRoom = mainRoom;
        //Are we making each room a unique object?        
        //Room village = new Room("the village", 2, null, mainRoom);
        //example of adding items below, to be moved to another method or class
        village.addItem(new Item("foo", "jar"));
    }

    private void quitGame()
    {
        gameOver = true;
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
