//Dummy class for the moment

import java.util.Scanner;
import java.util.Random;

/**
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

    @Override
    public void metLeaveCond(Player player)
    {

    }

    @Override
    public void doEvent(Player player)
    {
        //1 in 5 chance to kill the player if they do not type hit
        Random randNum = new Random();
        int killPlayer = 1;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("The ogre swings his axe towards you! Try to >HIT< him first!");
        System.out.print("What do you want to do?\n>");
        while (!keyboard.nextLine().equalsIgnoreCase("hit") && player.isAlive())
        {
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
        if (player.isAlive())
        {
            System.out.println("You stabbed the ogre with the pitchfork, "
                    + "it falls down and dies.");
            System.out.println("Congrats, you just added a sleeping potion to"
                    + " your inventory!");
            player.addItem(new Item("SleepingPotion", "It's a sleeping potion"));
            super.setLeavable(true);
            super.setEventFlag(false);
        }
    }
}
