
import java.util.ArrayList;

/**
 * Extends the item class. Holds logic for working with items that can hold
 * other items
 *
 * @author Eric
 * @author Chase
 */
//TODO Expand this in future sprint Chests, wardrobes, etc
public class ItemContainer extends Item
{

    private ArrayList<Item> heldItems = null;
    private boolean locked;

    /**
     *
     * @param name Name of the container
     * @param desc Description of the container
     */
    public ItemContainer(String name, String desc)
    {
        //set it so it's default visible
        super(name, desc, true);
        this.heldItems = new ArrayList<>();
        //set it so it's not locked by default
        this.locked = false;

        setContainerStatus(true);
    }

    /**
     *
     * @param name Name of the container
     * @param desc Description of the container
     * @param isVisible Visibility of item
     * @param locked Whether the item can be opened or not
     */
    public ItemContainer(String name, String desc, boolean isVisible, boolean locked)
    {
        super(name, desc, isVisible);
        this.heldItems = new ArrayList<>();
        this.locked = locked;

        setContainerStatus(true);
    }

    /**
     *
     * @param toAdd Item to be placed in the container
     */
    public void addItem(Item toAdd)
    {
        heldItems.add(toAdd);
        toAdd.setVisibility(false);
    }

    /**
     *
     * @return Lock status
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     *
     * @param isLocked Locks or unlocks container
     */
    public void setLock(boolean isLocked)
    {
        this.locked = isLocked;
    }

    /**
     *
     * @return The list of items in the container
     */
    public ArrayList<Item> getItemList()
    {
        return heldItems;
    }

    /**
     *
     */
    public void getItems()
    {

    }

    //TODO Write documentation for javadoc
    /**
     *
     * @param room
     */
    @Override
    public void open(Room room)
    {
        if (heldItems != null && !isLocked())
        {
            heldItems.forEach((n) -> n.setVisibility(true));
            System.out.println("You've opened the " + getName());
            System.out.println("Inside was: ");
            heldItems.forEach((n) -> System.out.println("-" + n.getName()));
            heldItems.forEach((n) -> room.addItem(n));
            heldItems = null;
        } else if (isLocked())
        {
            System.out.println("It's locked!");
        } else
        {
            System.out.println("It's empty!");
        }
    }
}
