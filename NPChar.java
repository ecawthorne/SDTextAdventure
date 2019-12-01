
import java.util.ArrayList;

/**
 * Class for non player characters. Handled similarly to items so shares some
 * functionality with the item class. Characters are visible and alive by
 * default if they die they are still present but are hidden from the player.
 *
 * @author Eric
 */
public class NPChar extends Item
{

    private boolean talkedTo = false, kill = false;
    private final String DIALOGUE;
    private ArrayList<String> itemStatuses = new ArrayList<>();
    private ArrayList<String> statusMsg = new ArrayList<>();

    /**
     * @param name Name of the character
     * @param desc Description given if character is examined
     * @param dialogue Dialogue returned if the character is spoken with
     * @param kill Whether the character is alive or dead
     */
    NPChar(String name, String desc, String dialogue, boolean kill)
    {
        //Characters are visible if alive and always immovable
        super(name, desc, true, false);
        this.DIALOGUE = dialogue;
        this.kill = kill;
    }

    /**
     * @return If the character has been spoken with
     */
    public boolean isTalkedTo()
    {
        return talkedTo;
    }

    /**
     * @param talkedTo Sets whether the character has been spoken to or not
     */
    public void setTalkedTo(boolean talkedTo)
    {
        this.talkedTo = talkedTo;
    }

    /**
     * @return Status of the character
     */
    public boolean killed()
    {
        return kill;
    }

    /**
     * Hides the character
     */
    public void killChar()
    {
        super.setVisibility(false);
    }

    /**
     * @return The dialogue of the character
     */
    public String getDIALOGUE()
    {
        return DIALOGUE;
    }

    public void addStatus(String status)
    {
        itemStatuses.add(status);
        //This can be improved if more items are added
        if ("SleepingPotion".equalsIgnoreCase(status))
        {
            System.out.println(super.getName() + " has fallen asleep!");
        }
    }
    public ArrayList<String> getStatuses()
    {
        return itemStatuses;
        
    }

}
