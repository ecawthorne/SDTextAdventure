
/**
 *
 * @author Eric
 */
public class NPChar extends Item
{
    private boolean talkedTo = false, kill = false;
    private final String DIALOGUE;
    NPChar(String name, String desc, String dialogue, boolean kill)
    {
        //Characters are visible if alive and always immovable
        super(name, desc, true, false);
        this.DIALOGUE = dialogue;
        this.kill = kill;
    }
    public boolean isTalkedTo()
    {
        return talkedTo;
    }

    public void setTalkedTo(boolean talkedTo)
    {
        this.talkedTo = talkedTo;
    }

    public boolean killed()
    {
        return kill;
    }

    public void killChar()
    {
        super.setVisibility(false);
    }
    
    public String getDIALOGUE()
    {
        return DIALOGUE;
    }
    
}
