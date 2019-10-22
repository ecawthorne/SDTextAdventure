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

    public Item(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
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
