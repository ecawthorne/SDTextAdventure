
/**
 *
 * @author Eric
 * edited by Chase 10/22/2019
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
                done = currentRoom.changeRoom(parsedInput);
                break;
            //examine room
            case 'x':
                done = currentRoom.getRoomDesc();
                break;
        //implement quit functionality
            case 'q':
                break;
        //implement help function
            case 'h':
                break;
            default:
                break;
        }
        return done;
    }
}
