//Basic item class. All items inherit from this.

/**
 *
 * @author Eric
 */
public class Item
{

    //Name of the item
    private String name = null;
    //Description of item
    private String desc = null;
    boolean isHidden = false;

    public Item(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }
    public Item(String name, String desc, boolean isHidden)
    {
        this.name = name;
        this.desc = desc;
        this.isHidden = true;
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

}
