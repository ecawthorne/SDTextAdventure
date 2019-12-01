//Basic item class. All items inherit from this.

/**
 * This is the base class for any items added in the game. Holds basic variables
 * for storing information about items
 *
 * @author Eric
 * @author Chase
 */
public class Item
{

    //Name of the item
    private String name = null;
    //Description of item
    private String desc = null;
    //Work on this in future sprint
    //I set default visibility to true because it's easy to forget they are invisible by default, causing           //debugging difficulties until the object instantiation is finally found and the lack of an isVisible           //declaration is noticed. Can change this later.
    private boolean isVisible = true;
    //going to add this canCarry boolean for future work, will have to be updated to include this eventually
    //private boolean canCarry = false;
    private boolean isContainer = false;

    private boolean movable = true;

    /**
     *
     * @param name Name of the item
     * @param desc Description of the item
     */
    public Item(String name, String desc)
    {
        this(name, desc, true);
    }

    /**
     *
     * @param name Name of the item
     * @param desc Description of the item
     * @param isVisible If the item is visible
     */
    public Item(String name, String desc, boolean isVisible)
    {
        this(name, desc, isVisible, true);
    }

    /**
     *
     * @param name Name of the item
     * @param desc Description of the item
     * @param isVisible If the item is visible
     * @param movable If the item can be moved
     */
    public Item(String name, String desc, boolean isVisible, boolean movable)
    {
        this.name = name;
        this.desc = desc;
        this.isVisible = isVisible;
        this.movable = movable;
    }

    /**
     *
     * @param name Name of the item
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @param desc Description of the item
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     *
     * @return Returns the name of the item
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return Returns the description of the item
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     *
     * @param isVisible Sets the visibility of the item
     */
    public void setVisibility(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    /**
     *
     * @return Returns the visibility of the item
     */
    public boolean getVisibility()
    {
        return isVisible;
    }

    public boolean isMovable()
    {
        return movable;
    }

    public void setMovable(boolean movable)
    {
        this.movable = movable;
    }

    /**
     *
     * @return Returns the type of item
     */
    public boolean getContainerStatus()
    {
        return isContainer;
    }

    /**
     *
     * @param containerStatus Sets the type of item
     */
    public void setContainerStatus(boolean containerStatus)
    {
        isContainer = containerStatus;
    }

    /**
     *
     * @param room
     */
    public void open(Room room)
    {
        System.out.println("That object can't be opened!");
    }

}
