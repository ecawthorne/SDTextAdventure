//Basic item class. All items inherit from this.
/**
 *
 * @author Eric
 */
abstract public class Item
{

    //Name of the item
    private String name = null;
    //Description of item
    private String desc = null;

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
