
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Execution starts in this class. Holds versioning information and intro to
 * game
 *
 * @author Eric
 * @author Chase
 * @version 0.3.5
 */
public class SamuraiStrike
{
    //update the version number below

    final static String VERSIONNUMBER = "v0.3.5";
    //GAMEINTRO is printed once, at the beginning of the game
    final static String GAMEINTRO = "Welcome to the Software Samurai text-based game, Samurai Strike " + VERSIONNUMBER + "! \n "
            + "Thanks for playing! \n "
            + "Type 'h' for help menu.\n";
    static Scanner keyboard = new Scanner(System.in);

    /**
     * Execution starts here
     *
     * @param args
     */
    public static void main(final String[] args)
    {

        DisplayMenu();
        RunGame();
    }

    /**
     * Prints the introduction to the game
     */
    public static void DisplayMenu()
    {
        System.out.println(GAMEINTRO);
    }

    /**
     * Checks if the player wants to play the game if the input is 1, then
     * recursively call the RunGame() function
     */
    public static boolean PlayAgain()
    {
        System.out.println("Please enter 1 if you would like to play again, or anything else to quit.");
        String input = keyboard.nextLine();
        input = input.trim();
        if (input.equals("1"))
        {
            return true;
        } else
        {
            System.out.println("Thank you for playing Samurai Strike!");
            return false;
        }
    }

    /**
     * Main game loop. Player input is taken here
     */
    public static void RunGame()
    {
        final GameManager manager = new GameManager();
        manager.EnterRoom();
        String input;
        while (true)
        {
            if (!manager.getCurrentRoom().isLeavable())
            {
                manager.getCurrentRoom().metLeaveCond(manager.getPlayer());
            }
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.print(">");
            //This should catch characters that the Scanner does not recognize
            try
            {
                input = keyboard.nextLine();
            } catch (NoSuchElementException e)
            {
                keyboard = new Scanner(System.in);
                input = "";
            }
            //If the player is not currently in an event
            if (input != null && !manager.doingEvent())
            {
                manager.parseInput(input);
            }
            //If the player is dead ask if the user wants to restart
            if (!manager.player.isAlive())
            {
                if (PlayAgain())
                {
                    RunGame();
                }
            }
        }

    }

}
