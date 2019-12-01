/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
public class BansheeRoom extends Room
{

    BansheeRoom(String name, String desc)
    {
        super("Banshee's Cave", "The farmer boy enters the Banshee’s Cave. You see the banshee \n"
                + "facing away from you, towards the wall, with the baby in \n"
                + "her hand. Your goal is to get close to her and take the baby.\n");
        super.addObject(new Item("Baby", "It's a baby", true, false));
        super.addObject(new NPChar("Banshee", "She hasn't seen you yet", "Do you really want to talk to her?", false));
    }

    @Override
    public void doEvent(GameManager manager, Player player)
    {
        if(super.getCharList().get(1).getStatuses().contains("SleepingPotion"))
        {
            System.out.println("The Banshee is asleep. You should take the baby while she can't stop you.");
            super.getItemList().get(0).setMovable(true);
        }

    }
}
