
import java.util.Scanner;

/**
 *
 * @author Eric edited by Chase 10/23/2019
 */
public class SamuraiStrike
{
    //update the version number below

    final static String VERSIONNUMBER = "v0.2.0";
    //GAMEINTRO is printed once, at the beginning of the game
    final static String GAMEINTRO = "Welcome to the Software Samurai text-based game, Samurai Strike " + VERSIONNUMBER + "! \n "
            + "Thanks for playing! \n "
            + "Type 'h' for help menu.\n";
    //HELPMESSAGE is printed whenever player types 'h', to explain commands to them.
    final String HELPMESSAGE = "You can use the following commands: \n"
            + "\'n\' will head north. \n"
            + "\'s\' will head south. \n"
            + "\'e\' will head east. \n"
            + "\'w\' will head west. \n"
            + "\'u\' will head up. \n"
            + "\'d\' will head down. \n"
            + "\'take <item>\' will allow you to pick up items in the room\n"
            + "\'l\' will look, providing a description of the room. \n"
            + "\'a\' will appraise an item in the room or your inventory."
            + "\'q\' will quit the game. \n"
            + "";

    public static void main(String[] args)
    {

        DisplayMenu();
        RunGame();
    }

    public static void DisplayMenu()
    {
        System.out.println(GAMEINTRO);
    }

    public void BuildObjects()
    {

    }

    public static void RunGame()
    {
        Scanner keyboard = new Scanner(System.in);
        GameManager manager = new GameManager();

        manager.EnterRoom();
        while (true)
        {
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.print(">");
            //get user input
            manager.parseInput(keyboard.nextLine());
            //Resets game variables if player wants to play again
            if (!manager.isPlayerAlive())
            {
                System.out.println("Do you want to play again? y/n");
                if (keyboard.nextLine().equalsIgnoreCase("n"))
                {
                    break;
                }
                manager = new GameManager();
                manager.EnterRoom();
            }
        }

        keyboard.close();
    }

}
