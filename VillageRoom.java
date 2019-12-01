
import java.util.Scanner;
import java.util.Random;

/**
 * Village room holding the ogre event
 *
 * @author Eric
 */
public class VillageRoom extends Room
{

    VillageRoom()
    {
        super("The Village", "The farmer boy proceeds outside of his home in"
                + "\nsearch of his dog, and finds the village being destroyed by"
                + "\ngiant ogres and the villagers running amuck. Suddenly, an"
                + "\narrow embeds itself into the wall next to him. The farmer"
                + "\nboy looks up and sees an angry ogre running towards him.");
        super.setEventFlag(true);
    }

    /**
     * The player must type hit in order to kill the ogre and leave the room. If
     * the player fails to type hit then there is a one in five chance for the
     * player to be killed and for the game to end.
     *
     * @param manager
     * @param player
     */
    @Override
    public void doEvent(GameManager manager, Player player)
    {
        Random randNum = new Random();
        int killPlayer = 1;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("The ogre swings his axe towards you! Try to >HIT< him first!");
        System.out.print("What do you want to do?\n>");
        //Continues until the player enters hit or the player is killed
        while (!keyboard.nextLine().equalsIgnoreCase("hit") && player.isAlive())
        {
            //If true, kills the player and ends the loop
            if (randNum.nextInt(4) == killPlayer)
            {
                System.out.println("That didn't work! The last thing you see is "
                        + "the ogre's axe swinging towards your face");
                player.setAlive(false);
                break;
            }
            System.out.println("That didn't work! Luckily the ogre missed!");
            System.out.print("What do you want to do?\n>");
        }
        //If the player entered the correct input, add the item to his inventory
        //and allow the player to leave the room
        if (player.isAlive())
        {
            System.out.println("You stabbed the ogre with the pitchfork, "
                    + "it falls down and dies.");
            System.out.println("Congrats, you just added a sleeping potion to"
                    + " your inventory!");
            player.addItem(new Item("SleepingPotion", "It's a sleeping potion"));
            manager.setEventLive(false);
            super.setLeavable(true);
            super.setEventFlag(false);
        }
    }
}
