# Fishing Game Project
This is my semester project. I presented it at the end of the semester at the Student Undergraduate Research Fair (SURF).
 
 Run file "FishingDriver" to play the game!!!
 
 ## Instructions

 Two player text-based Fishing Tournament game program using your I_a object classes.
   * Two players will alternate turns on the same computer.
   * The players will catch fish and put them into a "sack" (an ArrayList, sorted in descending order by length)
   * After 12 turns each, the player with the three largest fish is the winner.
 

#### Fishable Values for Fish Families
* `Pua_ama` mullet family:
   *  Legal size is 11 inches and above
   *  Season is open April - November, closed December - March
   *  IsGameFish returns true if legal size
   *  Is not bait
   *  No bag limit (number of fish you're allowed to catch). Use `Integer.MAX_VALUE` as the limit.
   *  Valid catch methods for legal size fish are: `"net", "pole"`   
* `Ohua`/`Uhu` fish family:
   *  Legal size is 12 inches and above
   *  Season is always open
   *  IsGameFish if legal size
   *  Is not bait
   *  No bag limit (number of fish you're allowed to catch). Use `Integer.MAX_VALUE` as the limit.
   *  Valid catch methods for legal size fish are: `"throw net", "spear", "pole"`   
* `Oama`/`Weke` fish family:
   *  No minimum legal size 
   *  Season is always open
   *  IsGameFish
   *  IsBait
   *  Bag limit is 50
   *  Valid catch methods for legal size fish are: `"net", "trap", "pole"`  

There are new JUnit tests that test just the Fishable methods for all of the fish types in this repository.

#### Fishing Tournament Phase 1 - Loko i'a (Fish Pond) Setup
  * The first thing that happens in the Fishing Tournament is the Fish Pond (ArrayList) filled up with baby fish of all `Fishable` types, then it should loop for around 2 pretend years (24 months) so the fish can grow to be roughly legal size.
     

#### Fishing Tournament Phase 2 - Lawa i'a (Fishing)
 * In the second (main game play) phase of the game, the fishing tournament lasts for a whole "year" (12 months)
 * The two players should alternate turns. Once both players have had a turn, the "month" will be over and change to the next "month". 
 * When the "month" changes, the fish in the pond should eat and grow once, like in the `FishingDriver.java` example. 
 * During each player's turn, they will get to attempt to catch a fish three times.
    - The player should get to pick their catch method each time ("pole", "net", "spear", "trap", "throw net")
 * Each catch attempt will have three possible outcomes: No fish is caught (random empty ArrayList location hit) or a fish is caught (fish randomly picked from ArrayList pond) and is either landed or gets away.
    * If the fish is landed, the player will then need to decide whether or not to keep it.
        - Optionally, the player could be given an opportunity to view the fishing rules here.
       * If the fish is kept, the fish should be checked for legal size, legal catch method, and legal month.
       * If the kept fish is legal, it should be added to the player's sack and removed from the pond.
       * If the kept fish is not legal, the player will be punished by having all of their fish confiscated (sack of fish emptied) 
 
 Example of game play:
 ```
Player 1, it is your turn! 
 * Choose:
   1. Cast out for a fish
   2. View fishing rules
   3. View sack of fish
   4. Throw back a fish in the sack.
   
   Choice: 1
    You have hooked a fish!
    You have caught a fish!
      Name: Uhu
      English name: Parrotfish
      Scientific name: Scarus Psittacus
      Length: 12.487740994862454
      Weight: 24.97548198972491
      Body color: blue-green
      Fin color: blue
      Sex: supermale
      
  * Do you want to keep this fish y/n? y
      - Possibly it would be nice to be able to view the fishing rules here, after the player knows what the fish is.
   You have kept your fish
   Your fish is legal and has been added to your sack
```
Other outcomes would be:
```
   You have kept your fish
   You kept an illegal fish! It is February and Ama'ama season is closed.
   You got a ticket and all of your fish were confiscated!
```
or 
```
  Your fish got away!
```  
* The option to view the fishing rules should list the rules (see Fishable Values) in an easy to read way.
* The option to view the sack of fish should list the sack of fish in order by length, longest first. 
* The throw back option will list the sack of fish (with the index number too) then ask the player to enter the number of the fish they want to throw back. 
    * The throw back option removes the chosen fish from the player's list and add it back to the fish pond list.
    * The idea behind this is that towards the end of the game, the pond might be getting pretty empty and a player might want to put some fish back in.


Regardless, each player gets to attempt catching a fish three times before their turn is over. They can list and release fish as many times as they want in a turn.
 
#### Fishing Tournament Phase 3 - Game Over
 * Once 12 "months" of turns have been taken, the two players' lists are compared. The player with the largest three fish (sum of the three longest lengths) is the winner! 
  - The winning scores and fish stats are listed out.