
import java.util.ArrayList;


/**
 *
 * @author Eric
 */
class InitialRoom extends Room
{

    ArrayList<Item> inventory = new ArrayList<>();

    InitialRoom()
    {
        super("House", 1);
        setInternalDesc("It's your house");
        inventory.add(new Item("Wardrobe", "It's a wardrobe."));
        inventory.add(new Item("Pitchfork", "Its a pitchfork.", true));
    }
}
