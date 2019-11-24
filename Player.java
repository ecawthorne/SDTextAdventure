
import java.util.ArrayList;

/**
 * Holds basic information for the player character
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

    /**
     * Sets the name of the player
     */
    public Player()
    {
        name = "Torvald";
    }

    /**
     *
     * @return Returns the name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Not currently used
     *
     * @param name Sets the name of the player
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return Whether the player is alive or dead
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     *
     * @param alive Sets the status of the player
     */
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    /**
     *
     * @param item Adds items to the players inventory
     */
    public void addItem(Item item)
    {
        itemList.add(item);
    }

    /**
     *
     * @param item Removes items from the players inventory
     */
    public void remItem(Item item)
    {
        itemList.remove(item);
    }

    /**
     *
     * @param room Current room that the player is in
     * @param item Item to be dropped
     */
    public void dropItem(Room room, Item item)
    {
        room.addItem(item);
        remItem(item);
    }

    /**
     *
     * @return Returns the items the player is currently holding
     */
    public ArrayList<Item> getItemList()
    {
        return itemList;
    }

    /**
     *
     * @param itemList Sets the items the player is currently holding
     */
    public void setItemList(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }
}
