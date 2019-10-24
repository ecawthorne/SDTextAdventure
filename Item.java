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
    private boolean isVisible = false;

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

}
