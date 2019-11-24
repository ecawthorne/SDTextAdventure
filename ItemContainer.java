import java.util.ArrayList;

/**
 *
 * @author Eric
 * @author Chase
 */
//Expand this in future sprint Chests, wardrobes, etc
public class ItemContainer extends Item
{

    private ArrayList<Item> heldItems = null;
    private boolean locked;

    public ItemContainer(String name, String desc)
    {
        //set it so it's default visible
        super(name, desc, true);
        this.heldItems = new ArrayList<>();
        //set it so it's not locked by default
        this.locked = false;

        setContainerStatus(true);
    }
    public ItemContainer(String name, String desc, boolean isVisible, boolean locked)
    {
        super(name, desc, isVisible);
        this.heldItems = new ArrayList<>();
        this.locked = locked;

        setContainerStatus(true);
    }

    public void addItem(Item toAdd)
    {
        heldItems.add(toAdd);
        toAdd.setVisibility(false);
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void setLock(boolean isLocked)
    {
        this.locked = isLocked;
    }

    public ArrayList<Item> getItemList()
    {
        return heldItems;
    }
    public void getItems()
    {

    }
    @Override
    public void open(Room room)
    {
        if(heldItems != null && !isLocked())
        {
                heldItems.forEach((n) -> n.setVisibility(true));
                System.out.println("You've opened the " + getName());
                System.out.println("Inside was: ");
                heldItems.forEach((n) -> System.out.println("-" + n.getName()));
                heldItems.forEach((n) -> room.addItem(n));
                heldItems = null;
        }
        else if(isLocked())
        {
            System.out.println("It's locked!");
        }
        else
        {
            System.out.println("It's empty!");
        }
    }
}
