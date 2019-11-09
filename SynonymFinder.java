
import java.util.HashMap;

/**
 *
 * @author Eric
 */
public class SynonymFinder
{
    String[] words = new String[] {"quit", "help", "go", "north", "east", "south",
                                   "west", "take", "drop", "examine", "open", 
                                   "up", "down"};
    //Multi word strings probably break the parser, investigate as soon as possible
    String[][] syns = new String[][] {{"q"}, {"h"},{"g", "move", "walk", "run"}, {"n"},{"e"}, {"s"},
                                          {"w"}, {"t", "pick up", "steal"},
                                          {"put down"}, {"x", "look at", "investigate"},
                                          {"o"}, {"u"}, {"d"}};
    HashMap<String, String> synList = new HashMap<>();
    SynonymFinder()
    {
        for (int i = 0; i < syns.length; i++)
        {
            for(int j = 0; j < syns[i].length; j++)
            {
                synList.put(syns[i][j], words[i]);
            }
        }
        //debug statement
        synList.entrySet().forEach(entry ->
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
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
