
import java.util.HashMap;

/**
 * Creates a hashmap of synonyms. Each possible synonym is a key linked to the
 * corresponding command which will be returned if found. Returns the users input
 * if not found.
 * @author Eric
 */
public class SynonymFinder
{
    String[] words = new String[] {"quit", "help", "go", "north", "east", "south",
                                   "west", "take", "drop", "examine", "open", 
                                   "up", "down", "look"};
    //ToDo: Multi word strings probably break the parser, investigate as soon as possible
    String[][] syns = new String[][] {{"q", "exit", "stop", "terminate", "finish"},
                                          {"h"},{"g", "move", "walk", "run"}, {"n"},{"e"}, {"s"},
                                          {"w"}, {"t", "pick up", "steal"},
                                          {"put down"}, {"x", "look at", "investigate"},
                                          {"o"}, {"u"}, {"d"}, {"l"}};
    HashMap<String, String> synList;
    SynonymFinder()
    {
        synList = new HashMap<>();
        for (int i = 0; i < syns.length; i++)
        {
            for(int j = 0; j < syns[i].length; j++)
            {
                synList.put(syns[i][j], words[i]);
            }
        }
    }
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
