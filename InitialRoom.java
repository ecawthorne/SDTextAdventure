
import java.util.ArrayList;

/**
 * The first room in the game
 *
 * @author Eric
 */
class InitialRoom extends Room
{

    /**
     * Sets up room title and descriptions and adds items that will be in the
     * room to the rooms inventory
     */
    InitialRoom()
    {
        super("Your house", "\"Once upon a time, there was a lonely orphan farmer\n"
                + "boy who was having a sentimental dream about his pet dog. \n"
                + "Suddenly, he started sweating from the rising temperature\n"
                + "in his room. The farmer boy wakes up to see his room up in\n"
                + "flames and hears war cries outside! He calls out for his dog,\n"
                + "\"Bubbles, where are you!?\" The dog doesn\'t respond and is \n"
                + "nowhere to be seen. He sits up and hears ogres crying\n"
                + "out for war. \"Nobody messes with my dog!\"",
                "There are haystacks up in flames on his right. "
                + "To his left is his wardrobe, and in front of him is a"
                + " mirror. Where should he move?");
        ItemContainer wardrobe = new ItemContainer("Wardrobe", "It's a wardrobe.");
        wardrobe.addItem(new Item("Pitchfork", "The pitchfork can be used in "
                + "battles or when cultivating. Be wise with it!"));
        super.addObject(wardrobe);

        super.addObject(new Item("Mirror", "You look handsome...but burnt."));

        super.setLeavable(false);
    }

    /**
     * @return The condition that must be met to leave the room
     */
    @Override
    public String getLeaveCondition()
    {
        return "You need your pitchfork!";
    }

    /**
     * Checks the players inventory for the pitchfork and allows them to leave
     * if they have it
     *
     * @param player For checking inventory for required item
     */
    @Override
    public void metLeaveCond(Player player)
    {
        ArrayList<Item> pItemList = player.getItemList();
        for (int i = 0; i < pItemList.size(); i++)
        {
            if (pItemList.get(i).getName().equals("Pitchfork"))
            {
                super.setLeavable(true);
            }
        }
    }
}
