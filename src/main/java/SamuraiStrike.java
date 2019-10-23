
import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class SamuraiStrike
{

    public static void main(String[] args)
    {
        final String intro = "Welcome to SamuraiStrike";
        Player player1 = new Player();
        InputParser inp = new InputParser();
        Scanner keyboard = new Scanner(System.in);
        String input;
        boolean gameOver = false;
        //menu followed by
        //gameplay loop
        System.out.println(intro);
		//true being replaced by boolean for continuePlaying
        while (true && player1.isAlive())
        {
            //Move this or add flag so it only outputs once
            player1.getCurrentRoom().getIntro();
            input = keyboard.nextLine();
            while (!inp.isValid(input))
            {
                System.out.println("I don't understand that. Try again");
                input = keyboard.nextLine();
            }
            player1.doAction(input);
        }

    }

}
