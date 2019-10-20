
/**
 *
 * @author Eric
 */
class InitialRoom extends Room
{

    Item[] items = new Item[2];

    InitialRoom()
    {
        setName("House");
        setIntro("“Once upon a time, there was a lonely orphan farmer boy who "
                + "\nwas having a sentimental dream about his pet dog."
                + "\nSuddenly, he started sweating from the rising"
                + "\ntemperatures in his room. The farmer boy wakes up to see"
                + "\nhis room up in flames and hears war cries outside! He"
                + "\ncalls out for his dog, “Bubbles, where are you!?”. "
                + "\nThe dog doesn’t respond and is nowhere to be seen. "
                + "\nHe needs to find a way to escape, but first he needs to"
                + "\nfind his pitch fork. “Nobody messes with my dog!\"");
        setInternalDesc("It's your house");
        items[0] = new Item("Wardrobe", "It's a wardrobe.");
        items[1] = new Item("Pitchfork", "Its a pitchfork");
    }

    

}
