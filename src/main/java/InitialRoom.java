
/**
 *
 * @author Eric
 */
class InitialRoom extends Room
{

    Item[] items;

    InitialRoom()
    {
        setName("House");
        setIntro("“Once upon a time, there was a lonely orphan farmer boy who "
                + "\nwas having a sentimental dream about his pet dog."
                + "\n Suddenly, he started sweating from the rising"
                + "\n temperatures in his room. The farmer boy wakes up to see"
                + "\n his room up in flames and hears war cries outside! He"
                + "\n calls out for his dog, “Bubbles, where are you!?”. "
                + "\nThe dog doesn’t respond and is nowhere to be seen. "
                + "\nHe needs to find a way to escape, but first he needs to"
                + "\n find his pitch fork. “Nobody messes with my dog!\"");
        setInternalDesc("It's your house");
        setItem(new Item("Wardrobe", "It's a wardrobe."));
        setItem(new Item("Pitchfork", "Its a pitchfork"));
    }

    

}
