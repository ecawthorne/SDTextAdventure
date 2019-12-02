
/**
 *
 * @author Eric
 */
public class ForestRoom extends Room
{

    ForestRoom()
    {
        super("The Forest", "The farmer boy runs into the forest. To the east there is a river.");
        super.addObject(new NPChar("Martin", "It's Father Martin",
                "Father Martin: \"Oh hey farmer boy, I was trying to feed the pigs, when suddenly,\n"
                + "flaming arrows started flying around, destroying the houses in the village.\"\n"
                + "The Ogre army attacked us out of nowhere! I tried to fight them but as you\n"
                + " can see they had the upper hand.\n"
                + "Farmer Boy: \"How unfortunate! Did you happen to see my dog bubbles?\"\n"
                + "Father Martin: \"He tried to bite the ogre to save me. Unfortunately,\n"
                + "he got kidnapped to be the ogres’ next meal.\" \n"
                + "Farmer Boy: \"AGHHHHHH they are going to pay for this!\"\n"
                + "Father Martin dies. ",
                true));
        super.addObject(new NPChar("Woman", "It's a crying woman",
                "Farmer Boy: \"Are you okay?\"\n"
                + "Woman: \"NOOO! My baby is GONE! the banshee stole her from me! She went to the west.\n"
                + "Can you please save her for me, I will help you back in any way possible!\"", false));
        super.setLeavable(false);
        super.setLeaveCond("Is there anyone you haven't talked to?");
    }

    @Override
    public void metLeaveCond(Player player)
    {
        if (super.findChar("Martin").isTalkedTo() && super.findChar("Woman").isTalkedTo())
        {
            this.setConnection(4, new BansheeRoom());
            super.setLeavable(true);
            System.out.println("You can now enter the Banshee's cave");
        }
        for (Item itemList : player.getItemList())
        {
            if (itemList.getName().equalsIgnoreCase("baby"))
            {
                this.setConnection(3, new SwampRoom());
                System.out.println("You see a path that wasn't visible before! You can now enter the swamp");
            }

        }
    }
}
