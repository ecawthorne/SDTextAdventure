
import java.util.Scanner;

/**
 *
 * @author Eric
 * edited by Chase 10/23/2019
 */
public class SamuraiStrike
{
	//update the version number below
	final String VERSIONNUMBER = "v0.0.1";
	//GAMEINTRO is printed once, at the beginning of the game
	final String GAMEINTRO =	"Welcome to the Software Samurai text-based game, Samurai Strike " + VERSIONNUMBER + "! \n " + 
								"Thanks for playing! \n " +
								"\n Type 'h' for help menu." +
								"";
	//HELPMESSAGE is printed whenever player types 'h', to explain commands to them.
	final String HELPMESSAGE = "You can use the following commands: " +
								"\'n\' will head north, when possible. \n" +
								"\'s\' will head south, when possible. \n" +
								"\'e\' will head east, when possible. \n" +
								"\'w\' will head west, when possible. \n" +
								"\'u\' will head up, when possible. \n" +
								"\'d\' will head down, when possible. \n" +
								"\'l\' will look, providing a description of the room. \n" +
								"\'q\' will quit the game. \n" +
								"";
	//

    public static void main(String[] args)
    {
		DisplayMenu();
		BuildObjects();		
		GameManager.EnterRoom();
        RunGame();
    }
	
	public void DisplayMenu()
	{
		System.out.println(GAMEINTRO);
	}
	
	public void BuildObjects()
	{
		Player player1 = new Player();
		GameManager manager = new GameManager();	
	}
	
	public void RunGame()
	{		
        Scanner keyboard = new Scanner(System.in);	
        String input;		
        while (player1.isAlive())
        {
			//get user input
			input = keyboard.GetInput();
            player1.doAction(input);			
        }
	}
	
	

}
