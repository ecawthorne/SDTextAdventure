
import java.util.ArrayList;

/**
 *
 * @author Eric
 * @author Chase
 */
public class Player
{

    //Player name
    private String name = null;
    private ArrayList<Item> itemList = new ArrayList<>();
    private boolean alive = true;

    public Player()
    {
        name = "Torvald";
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
    public void remItem(Item item)
    {
        itemList.remove(item);
    }
    public void dropItem(Room room, Item item)
    {
        room.addItem(item);
        remItem(item);
    }

    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }
}
