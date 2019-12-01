import java.util.Scanner;
import java.util.Random;

/**
 * @author Chase
 */


public class SwampRoom extends Room
{
    
    Item heroSword = new Item("Hero's Sword", "This excellent sword glows"
    + " with a holy light. " 
    + "It is sharper than the night.", false, false);

    SwampRoom(String name, String desc)
    {
        
        super("Swamp", "In front of you, is a murky swamp, with a"
        + " massive alligator relaxing within.");
        super.setInternalDesc("Your boots sink into the murk. Ew.");
        super.setExternalDesc("Looks wet over there. Is something moving?");
        super.addObject(heroSword);
        super.addObject(new NPChar("Alligator", "The large reptilian"
        + " looks very wise. His eyes glow with the forces of time and" 
        + " space, and King Arthur himself has dubbed him the"
        + " Tornado-Harnessing Knight who Vanquishes the Forever"
        + " Dark... Or maybe you're just hallucinating"
        + ".", "Alligator: Go save your friend, hero.", false));
    }

    @Override
    public void doEvent(GameManager manager, Player player)
    {
        Random randNum = new Random();
        int killPlayer = 1;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Alligator: Hello hero-to-be, I know your purpose in coming here." 
        + " I will provide to you the greatest sword ever forged, if"
        + " you can simply answer my riddle. Else, I may dine upon you,"
        + " should it strike my fancy.");
        System.out.println("Are you ready? Here is my riddle..." 
        + "\n\nIf the red house is made of red bricks,"
        + " and the yellow house is made of yellow bricks,"
        + " what is the greenhouse made of?");
        while(!keyboard.nextLine().equalsIgnoreCase("glass") && player.isAlive())
        {
            if (randNum.nextInt(7) == killPlayer)
            {
                System.out.println("Alligator: Well, I grow tired of this.");
                System.out.println("The alligator opens his mighty jaws,"
                + "and chomps down on you.");
                player.setAlive(false);
                break;
            }
            System.out.println("Alligator: That's incorrect. Try again.");
            System.out.println("What do you want to do?\n");
        }
        if (player.isAlive())
        {
            System.out.println("Alligator: Well, I must say I'm amazed." 
            + " I thought my riddle was ineffable. Perhaps you are indeed"
            + " the hero I saw you to be. Take this holy blade, and save Bubbles."
            + ".");

            heroSword.setVisibility(true);
            heroSword.setMovable(true);
        }
        keyboard.close();
    }
}