
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Eric
 * @author Chase
 * @version 0.3.4
 */
public class SamuraiStrike
{
    //update the version number below

    final static String VERSIONNUMBER = "v0.3.4";
    //GAMEINTRO is printed once, at the beginning of the game
    final static String GAMEINTRO = "Welcome to the Software Samurai text-based game, Samurai Strike " + VERSIONNUMBER + "! \n "
            + "Thanks for playing! \n "
            + "Type 'h' for help menu.\n";
    static Scanner keyboard = new Scanner(System.in);

    public static void main(final String[] args)
    {

        DisplayMenu();
        RunGame();
    }

    //prints the introduction to the game
    public static void DisplayMenu()
    {
        System.out.println(GAMEINTRO);
    }

    //checks if the player wants to play the game
    //if the input is 1, then recursively call the RunGame() function
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

    //loop for player input, calling the gamemanager
    public static void RunGame()
    {
        final GameManager manager = new GameManager();
        manager.EnterRoom();
        String input;
        while (true)
        {

            System.out.println();
            System.out.println("What do you want to do?");
            System.out.print(">");
            //get user input
            try
            {
                input = keyboard.nextLine();
            } catch (NoSuchElementException e)
            {
                keyboard = new Scanner(System.in);
                input = "";
            }
            if (input != null && !manager.doingEvent())
            {
                manager.parseInput(input);
            }
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
