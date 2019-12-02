/**
 *
 * @author Eric
 * @author Chase
 */
public class BansheeRoom extends Room
{
    private NPChar banshee = new NPChar("Banshee", "She hasn't seen you yet", "Do you really want to talk to her?", false);
    private Item baby = new Item("Baby", "It's a baby", true, true);

    BansheeRoom()
    {
        super("Banshee's Cave", "The farmer boy enters the Banshee’s Cave. You see the banshee \n"
                + "facing away from you, towards the wall, with the baby in \n"
                + "her hand. Your goal is to get close to her and take the baby.\n");
        super.addObject(banshee);
    }

    @Override
    public void metLeaveCond(Player player)
    {
        if(super.getCharList().get(0).hasStatus("sleepingpotion") && !super.getItemList().contains(baby))
        {
            super.addObject(baby);
            System.out.println("The Banshee is asleep! You should take the baby while she can't stop you.\n");
            super.getCharList().get(0).setDesc("The Banshee is asleep! You should take the baby while she can't stop you.");
            this.setConnection(2, eastConnection);
        }

    }
}
