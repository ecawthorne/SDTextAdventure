import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
class InitialRoom extends Room
{
    private ArrayList<Item> itemList = new ArrayList<>();
    //Sets up room title and descriptions and adds items that will be in the room to
    //the rooms inventory
    InitialRoom()
    {
        super("Your house", "\"Once upon a time, there was a lonely orphan farmer\n"
            + "boy who was having a sentimental dream about his pet dog. \n"
            + "Suddenly, he started sweating from the rising temperatures\n"
            + "in his room. The farmer boy wakes up to see his room up in\n"
            + "flames and hears war cries outside! He calls out for his dog,\n"
            + "\"Bubbles, where are you!?\" The dog doesn\'t respond and is \n"
            + "nowhere to be seen. He sits up and hears ogres making crying\n"
            + "out for war. \"Nobody messes with my dog!\"",
              "There are haystacks up in flames on his right. "
            + "To his left is his wardrobe, and in front of him is a"
            + " mirror. Where should he move?");
        ItemContainer wardrobe = new ItemContainer("Wardrobe", "It's a wardrobe.");
        wardrobe.addItem(new Item("Pitchfork", "The pitchfork can be used in "
                + "battles or when cultivating. Be wise with it!"));
        addItem(wardrobe);
        this.itemList.add(wardrobe);
        addItem(new Item("Mirror", "You look handsome...but burnt."));
        this.itemList.add(new Item("Mirror", "You look handsome...but burnt."));
        setLeavable(false);
    }
    
    @Override
    public String getLeaveCondition()
    {
        return "You need your pitchfork!";
    }

    //This could probably be more efficient; work on it in later sprints
    @Override
    public void metLeaveCond(Player player)
    {
        ArrayList<Item> pItemList = player.getItemList();
        for (int i = 0; i < pItemList.size(); i++)
        {
            if (pItemList.get(i).getName().equals("Pitchfork"))
            {
                setLeavable(true);
            }
        }
    }

    @Override
    public void doRiddle(Player player)
    {
        Scanner keyboard = new Scanner(System.in);
        boolean firstLoop = true;
        int solved = 0;
        while (solved == 0)
        {
            if (firstLoop)
            {
                System.out.println("Enter the next number in the following series:"
                        + " 1, 2, 4, 8…");
                firstLoop = false;
            }
            if (keyboard.nextLine().equals("16"))
            {
                System.out.println("Congratulations! You got the pitchfork");

            } else if (keyboard.nextLine().toLowerCase().equals("q"))
            {
                solved = -1;
            } else
            {
                System.out.println("Try again");
            }
        }
        keyboard.close();
    }
}
