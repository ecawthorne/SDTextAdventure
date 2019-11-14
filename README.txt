====================
README AND CHANGELOG
====================
Edited by Chase for v0.2 on 10/29/2019 at 23:08
===============
PROGRAM SUMMARY
===============
Text-based adventure game for CSCI 3315. Current name: Samurai Strike.
The player will solve puzzles and riddles in order to advance further throughout the world of Samurai Strike, encountering enemies and finding love along the way!
==================
SamuraiStrike v0.1
==================
-Currently the Room class is in development, but they are doubly linked, so it is possible to pass between rooms. Also have leave conditions for some rooms. Leavable defaults to false.
-Item class is able to contain a name and description.
-Player class contains an inventory and can add items to it.
-ItemContainer not yet implemented. Should eventually function as "chests" and contain other items.
-GameManager has many functions that can be optimized. Will implement a find() function to reduce for loops and iterations for us to type.


KNOWN BUG(S): 
-For some reason, passing south back to the main room returns null. Issue will be patched in next version.

==================
SamuraiStrike v0.2
==================
-ItemContainer now contains items that are unable to be seen until opened. Does not allow player to put them back yet.
-Drop function implemented so the player can remove items from inventory.
-Find function implemented so we do not need to iterate constantly throughout the code. Makes the code easier to read with doThis(find(namedItem)) functions rather than huge loops.
-Commands changed from letters to actual words so the game is more sensible in English.
-Rooms have been changed so leavable defaults to true. This way we can have empty rooms simply for descriptions.
-Game start message was displaying each time you entered the first room, so a check has been added to see if it's the first time the player has been there.
-Game will now catch failures for things like "open" followed by a blank, and force you to enter something.


FIXED BUG(S):
-Inability to pass back to previous room fixed. Direction commands now all work correctly. Cardinal Direction numbers were confused in the code.

KNOWN BUG(S):
-All items, including the wardrobe(ItemContainer) can be taken, and will contain the item inside them until opened. Will be addressed in future versions.

==================
SamuraiStrike v0.3
==================
-SynonymFinder class added to allow players to enter various different commands for the same results.
-Ogre encounter now implemented.
-Main game loop handling has been changed.

KNOWN BUG(S):
-Ogre encounter will break the game if player is killed.
  +Suspected reason: Main game loop not recursive and operating strangely.
-All items, including the wardrobe(ItemContainer) can be taken, and will contain the item inside them until opened. Will be addressed in future versions.

