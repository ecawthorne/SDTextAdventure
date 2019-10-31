//Basic item class. All items inherit from this.

/**
 *
 * @author Eric
 * edited by Chase 10/22/2019
 * edited by Chase 10/23/2019
 */
public class Item
{

    //Name of the item
    private String name = null;
    //Description of item
    private String desc = null;
    //Work on this in future sprint
    //I set default visibility to true because it's easy to forget they are invisible by default, causing debugging difficulties until the object instantiation is finally found and the lack of an isVisible declaration is noticed. Can change this later.
    private boolean isVisible = true;
    //going to add this canCarry boolean for future work, will have to be updated to include this eventually
    //private boolean canCarry = false;
    private boolean isContainer = false;
    public Item()
    {

    }
    public Item(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }

    public Item(String name, String desc, boolean isVisible)
    {
        this.name = name;
        this.desc = desc;
        this.isVisible = isVisible;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getName()
    {
        return name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setVisibility(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public boolean getVisibility()
    {
        return isVisible;
    }

    public boolean getContainerStatus()
    {
        return isContainer;
    }

    public void setContainerStatus(boolean containerStatus)
    {
        isContainer = containerStatus;
    }

    public void open(Room room)
    {
        System.out.println("That object can't be opened!");
    }

}
