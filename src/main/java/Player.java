
/**
 *
 * @author Eric
 */
public class Player
{

    //Player name
    private String name = null;

    private boolean alive = true;
    private Room currentRoom = null;

    public Player()
    {
        name = "Torvald";
        currentRoom = new InitialRoom();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    public boolean doAction(String parsedInput)
    {
        boolean done = false;
        if (parsedInput.charAt(0) == 'w' || parsedInput.charAt(0) == 'n'
                || parsedInput.charAt(0) == 'e' || parsedInput.charAt(0) == 's'
                || parsedInput.charAt(0) == 'u' || parsedInput.charAt(0) == 'd')
        {
            done = currentRoom.changeRoom(parsedInput);
        }else if(parsedInput.charAt(0) == 'x')
        {
            done = currentRoom.getRoomDesc();
        }
        else if (parsedInput.charAt(0) == 'q')
        {
            //implement quir functionality
        }else if(parsedInput.charAt(0) == 'h')
        {
            //implement help function
        }
        return done;
    }
}
