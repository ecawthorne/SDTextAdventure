
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

    GameManager()
    {
        constructRooms();
        currentRoom = new InitialRoom();
    }
	
	public void EnterRoom()
	{
		System.out.println(currentRoom.getIntro());
		System.out.println("The following items are in this room: ");
		System.out.println(currentRoom.getItems());
		for(int i = 1; i <= 4; i++)
		{
			string direction;
			switch(i)
			{
				case(1):
					direction = "north";
					break;
				case(2):
					direction = "south";
					break;
				case(3):
					direction = "east";
					break;
				case(4):
					direction = "west";
					break;
				default:
					direction = "ERROR: PLEASE REPORT DIRECTION BUG TO DEVELOPER.";
					break;
			}
			connectedRoom = currentRoom.getConnection(i)
			if(connectedRoom !+ null)
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
        } else
        {
            System.out.println("I don't understand that. Please enter only one"
                    + " character");
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
        //Are we making each room a unique object?
        Room village = new Room("the village", 2, null, mainRoom, null, null);
    }

    private void quitGame()
    {
        gameOver = true;
    }

}
