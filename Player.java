
import java.util.ArrayList;

/**
 *
 * @author Eric edited by Chase 10/22/2019 edited by Chase 10/23/2019
 */
public class Player
{

    //Player name
    private String name = null;
    private ArrayList<Item> itemList = new ArrayList<>();
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

    public void addItem(Item item)
    {
        itemList.add(item);
    }

    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
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
            case 'l':
                done = currentRoom.getRoomDesc(); //future update will check if 'l' is followed by anything, and then provide a description of whatever is being looked at
                break;
            //implement quit functionality
            case 'q':
                setAlive(false);
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
