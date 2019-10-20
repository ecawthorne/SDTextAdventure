
import java.lang.Object;

/**
 *
 * @author Eric
 */
public class InputParser
{

    private String playerInput = null;

    public InputParser(String playerInput)
    {
        this.playerInput = playerInput.toLowerCase();
    }

    InputParser()
    {
    }

    public boolean isValid(String input)
    {
        if(input != null)
        {
            if (input.length() == 1)
            {
                switch (input.charAt(0))
                {
                    case 'w':
                        return true;
                    case 'n':
                        return true;
                    case 'e':
                        return true;
                    case 's':
                        return true;
                    case 'u':
                        return true;
                    case 'd':
                        return true;
                    case 'h':
                        return true;
                    case 'l':
                        return true;
                    case 'q':
                        return true;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    public char getAction(String input)
    {
        char action = null;
        if (isValid())
        {

        }
    }
}
