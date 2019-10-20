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

}
