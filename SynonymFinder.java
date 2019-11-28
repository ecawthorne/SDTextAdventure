
import java.util.HashMap;

/**
 * Creates a hashmap of synonyms. Each possible synonym is a key linked to the
 * corresponding command which will be returned if found. Returns the users
 * input if not found.
 *
 * @author Eric
 */
public class SynonymFinder
{

    /**
     * These are the commands that the parser can recognize
     */
    String[] words = new String[]
    {
        "quit", "help", "go", "north", "east", "south",
        "west", "take", "drop", "examine", "open",
        "up", "down", "look", "talk"
    };
    //ToDo: Multi word strings probably break the parser, investigate as soon as possible
    /**
     * Alternative words or characters that the player can enter and that will
     * be converted to commands
     */
    String[][] syns = new String[][]
    {
        {
            "q", "exit", "stop", "terminate", "finish"
        },
        {
            "h"
        },
        {
            "g", "move", "walk", "run"
        },
        {
            "n"
        },
        {
            "e"
        },
        {
            "s"
        },
        {
            "w"
        },
        {
            "t", "pick up", "steal"
        },
        {
            "put down"
        },
        {
            "x", "look at", "investigate"
        },
        {
            "o"
        },
        {
            "u"
        },
        {
            "d"
        },
        {
            "l"
        },
        {
            "speak"
        }
    };
    HashMap<String, String> synList;

    /**
     * Matches the commands to the alternative words or characters entered
     */
    SynonymFinder()
    {
        synList = new HashMap<>();
        for (int i = 0; i < syns.length; i++)
        {
            for (int j = 0; j < syns[i].length; j++)
            {
                synList.put(syns[i][j], words[i]);
            }
        }
    }

    /**
     *
     * @param input Accepts the alternative words or characters
     * @return Returns the commands to be parsed
     */
    public String getCommand(String input)
    {
        String found = synList.get(input);
        if (found != null)
        {
            return found;
        } else
        {
            return input;
        }
    }

}
