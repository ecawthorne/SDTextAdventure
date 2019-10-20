/**
 *
 * @author Eric
 */
class InitialRoom extends Room
{
    Item[] items;
    
    InitialRoom()
    {
        setInternalDesc("It's your house");
        items[0].setName("Wardrobe");
        items[0].setDesc("It's a wardrobe.");
        //Hidden in wardrobe
        items[1].setName("Pitchfork");
        items[2].setDesc("Its a pitchfork");
    }
    public void getRoomDesc()
    {
        /* Need to implement a way to hide certain items before using this
        System.out.println(getInternalDesc());
        for(int i = 0; i < items.length; i++)
        {
            System.out.println("You see a " + items[i].getName());
        }
        */
    }
    
}
