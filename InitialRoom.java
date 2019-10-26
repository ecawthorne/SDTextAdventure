
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
class InitialRoom extends Room
{

    //This is here for testing. Add functionality where all descriptions are
    //stored in and then read from a file 
    final String intro = "“Once upon a time, there was a lonely orphan farmer\n"
            + "boy who was having a sentimental dream about his pet dog. \n"
            + "Suddenly, he started sweating from the rising temperatures\n"
            + "in his room. The farmer boy wakes up to see his room up in\n"
            + "flames and hears war cries outside! He calls out for his dog,\n"
            + "\"Bubbles, where are you!?”. The dog doesn’t respond and is \n"
            + "nowhere to be seen. He sits up and hears ogres making crying\n"
            + "out for war. “Nobody messes with my dog!”";

    InitialRoom()
    {
        setName("House");
        setIntro(this.intro);
        setInternalDesc("There are haystacks up in flames on his right. "
                + "To his left is his wardrobe, and in front of him is a"
                + " mirror. Where should he move?");
        addItem(new Item("Wardrobe", "It's a wardrobe."));
        addItem(new Item("Pitchfork", "The pitchfork can be used in "
                + "battles or when cultivating. Be wise with it!", true));
        addItem(new Item("Mirror", "You look handsome...but burnt"));
    }

//    public InitialRoom(String n, String intro)
//    {
//        super(n, intro);
//    }
//
//    public InitialRoom(String n, int descLoc, Room connectedRoom)
//    {
//        super(n, descLoc, connectedRoom);
//    }
//
//    public InitialRoom(String n, int loc, Room conRoom1, Room conRoom2)
//    {
//        super(n, loc, conRoom1, conRoom2);
//    }
//
//    public InitialRoom(String n, int loc, Room conRoom1, Room conRoom2, Room conRoom3)
//    {
//        super(n, loc, conRoom1, conRoom2, conRoom3);
//    }
//
//    public InitialRoom(String n, int loc, Room conRoom1, Room conRoom2, Room conRoom3, Room conRoom4)
//    {
//        super(n, loc, conRoom1, conRoom2, conRoom3, conRoom4);
//    }
    @Override
    public String getLeaveCondition()
    {
        return "You need your pitchfork!";
    }

    //This could probably be more efficient; work on it in later sprints
    @Override
    public void metLeaveCond(Player player)
    {
        ArrayList<Item> itemList = player.getItemList();
        for (int i = 0; i < itemList.size(); i++)
        {
            if (itemList.get(i).getName().equals("Pitchfork"))
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
