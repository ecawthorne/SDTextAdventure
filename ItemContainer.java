
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
//Expand this in future sprint Chests, wardrobes, etc
public class ItemContainer
{

    private ArrayList<Item> heldItems;
    private boolean locked;

    public ItemContainer(String name, String desc, boolean locked)
    {
        this.heldItems = new ArrayList<>();
        this.locked = locked;
    }

    public boolean IsLocked()
    {
        return locked;
    }

    public void setLock(boolean isLocked)
    {
        this.locked = isLocked;
    }

    public void open()
    {

    }

}
