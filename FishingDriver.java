import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
/**
* Driver for Fishable I_a testing and tournament.
* @author Arina Yefimova
*/
public class FishingDriver {
   /** Maximum pond capacity for random hooking. */
   static final int POND_CAPACITY = 40;
   
   /** Main ArrayList that holds all fish in the pond. */
   private static ArrayList<FishableI_a> lokoi_a;
  
  /** Player 1 in the tournament. */
   private static Player player1;
  
  /** Player 2 in the tournament. */
   private static Player player2;
  
  /** Current month in the simulation (1-12). */
   private static int currentMonth = 1;
  
  /** Make pauses in the game. 
  *@param milliseconds is time measurement
  */
   private static void pause(int milliseconds) {
      try {
         Thread.sleep(milliseconds);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
      }
   }
 
  /** 
  * Array of month names for readable display.
  * index 0 corresponds to jan, index 11 to dec.
  */
   private static final String[] MONTH_NAMES = {
      "January", "February", "March", "April", "May", "June", 
      "July", "August", "September", "October", "November", "December",
   };
   
   /** Main method.
   * Handles phase 1 (setup) and phase 2 (gameplay)
   * 1. Creates and populates fish pond
   * 2. Grows fish for 24 months
   * 3. Sets up players
   * 4. Executes tournament gameplay
   * @param args not used.
   */
   public static void main(String[] args) {
   
     // Phase 1: Fish Pond Setup
      System.out.println("===== FISHING TOURNAMENT =====");
      pause(1000);
      System.out.println("Phase 1: Setting up the fish pond");
      pause(1000);
   
      //ArrayList to simulate a fishpond/lokoi'a
      lokoi_a = new ArrayList<>();
      boolean debug = true; //for printing
            
      lokoi_a = FishingDriver.fillPond();
      if (debug) {
         printFishList(lokoi_a);
      }
      System.out.println("Pond has " + lokoi_a.size() + " baby fish");
      pause(1000);
      //Let fish in pond grow some
      //eat and grow for 24 months
      System.out.println("\nGrowing fish for 24 months...");
      pause(1000);
      FishingDriver.growFish(lokoi_a);
      
      /** Show fish stats after growth period.*/
      System.out.println("\nFish statistics after growth:");
      pause(1000);
      countFishTypes();
      countLegalFish();
      
      /** Setup players.*/
      getPlayerNames();
     
     /** Start phase 2 - tournament gameplay. */
      System.out.println("\n===== PHASE 2: TOURNAMENT GAMEPLAY=====");
      pause(1000);
      System.out.println("Now lets open the pond for fishing!");
      pause(1000);
      FishingDriver.lawai_a(lokoi_a);
   
   } //close main
    
   /**
   * Adds 40 baby I_a to ArrayList.
   * Creates 10 of each species.
   * This should match POND_CAPACITY.
   * @return ArrayList with small I_a in it.
   */
   public static ArrayList<FishableI_a> fillPond() {
      
      ArrayList<FishableI_a> al = new ArrayList<>();
      
      /** Add 10 Moi.*/
      for (int i = 0; i < 10; i++) {
         al.add(new MoiLi_i());
      }
      /** Add 10 Weke.*/
      for (int i = 0; i < 10; i++) {
         al.add(new Oama());
      }
      /** Add 10 Ama'ama.*/
      for (int i = 0; i < 10; i++) {
         al.add(new Pua_ama());
      }
      /** Add 10 Uhu.*/
      for (int i = 0; i < 10; i++) {
         al.add(new Ohua());
      }
      return al;  
   } //fillPond method
   
   /**
   * Runs arraylist of I_a through 24 eating/growing cycles.
   * Part of phase 1:
   * When each fish reach certain sizes, they level up to larger form.
   * @param al the list of fish.
   */
   public static void growFish(ArrayList<FishableI_a> al) {
      FishableI_a ia;
      boolean debug = false; //turned printing off
      for (int m = 0; m < 24; m++) { //process 24 months of growth
      
      /**show progress every 6 months.*/
         if (m % 6 == 0) {
            System.out.println("Month " + (m + 1) + "...");
         } 
      //growth for all fish in the pond
         for (int i = 0; i < al.size(); i++) {
         //loop over array 
            if (debug) {
               System.out.println("==========================");
               System.out.println("Feeding  the fish " + i);
               System.out.println("==========================\n");
            }
            ia = al.get(i);
            if (debug) {
               System.out.println(ia);
            }
            try { //must check for need to levelUp
            //use EnglishName because doesn't change with size
               if (ia.getEnglishName().equals("Striped Mullet")) {
                  ia.eat("algae");
               } else if (ia.getEnglishName().equals("Goatfish") 
                     || ia.getEnglishName().equals("Yellowfin Goatfish") 
                     || ia.getEnglishName().equals("Square-spot Goatfish"))  {
                  ia.eat("worms");
               } else if (ia.getEnglishName().equals("Parrotfish")) {
                  ia.eat("algae");
                  
               } else if (ia.getEnglishName().equals("Six-fingered threadfin")) {
                  ia.eat("crustaceans");
                  
               }
               if (debug) {
                  System.out.println("****After eat and grow: " + ia.getName() 
                        + ": " + ia.getLength() + "\n");
               }
            } catch (FishSizeException fe) {
               /**fish has grown to max size. needs to level up.*/
               ia = ia.levelUp();
               if (debug) {
                  System.out.println(fe.getMessage());
                  System.out.println("**** After levelUp: " + ia + "\n");
               }
            }
            /**update fish in the ArrayList.*/
            al.set(i, ia);
         }
      } // close m loop
      
      System.out.println("Fish have grown for 2 years");
   
   } //close growFish method
   
   /**
   * Counts and displays how many of each fish type are in the pond.
   * Categories are Moi, Weke, Ama'ama, and Uhu fish families.
   */
   static void countFishTypes() {
      int moiCount = 0;
      int wekeCount = 0;
      int amaamaCount = 0;
      int uhuCount = 0;
      
          
      // Loop through fish and count by type
      for (int i = 0; i < lokoi_a.size(); i++) {
         FishableI_a fish = lokoi_a.get(i);
         String fishName = fish.getClass().getName();
          
          // Categorize fish by checking class name
         if (fishName.contains("Moi")) {
            moiCount++;
         } else if (fishName.contains("Weke") || fishName.contains("Oama")) {
            wekeCount++;
         } else if (fishName.contains("Ama") || fishName.contains("Pua_ama")) {
            amaamaCount++;
         } else if (fishName.contains("Uhu") || fishName.contains("Ohua") 
                                      || fishName.contains("Panuhunuhu")) {
            uhuCount++;
         }
      }
      
      // Display total counts
      System.out.println("Total fish: " + lokoi_a.size());
      System.out.println("Moi fish: " + moiCount);
      System.out.println("Weke fish: " + wekeCount);
      System.out.println("Ama'ama fish: " + amaamaCount);
      System.out.println("Uhu fish: " + uhuCount);
   }
  
  /**
   * Counts and displays how many legal size fish are in the pond.
   * Also shows percentage of legal fish and identifies the
   * smallest and largest fish in the pond.
   */
   static void countLegalFish() {
      int legalFish = 0;
      
      // Count legal fish
      for (int i = 0; i < lokoi_a.size(); i++) {
         if (lokoi_a.get(i).isLegalSize()) {
            legalFish++;
         }
      }
      
      // Display legal fish stats
      System.out.println("\nLegal size fish: " + legalFish);
      double percent = (double) legalFish / lokoi_a.size() * 100;
      System.out.println("Percent legal: " + String.format("%.1f", percent) + "%");
      
      // Find and display biggest and smallest fish
      if (lokoi_a.size() > 0) {
         FishableI_a biggest = lokoi_a.get(0);
         FishableI_a smallest = lokoi_a.get(0);
          
         for (int i = 0; i < lokoi_a.size(); i++) {
            FishableI_a currentFish = lokoi_a.get(i);
            if (currentFish.getLength() > biggest.getLength()) {
               biggest = currentFish;
            }
            if (currentFish.getLength() < smallest.getLength()) {
               smallest = currentFish;
            }
         }
          
         System.out.println("Smallest fish: " + smallest.getName() 
                 + " (" + String.format("%.3f", smallest.getLength()) + " inches)");
         System.out.println("Biggest fish: " + biggest.getName()  
                 + " (" + String.format("%.3f", biggest.getLength()) + " inches)");
      }
   }
   
   /**
   * Gets player names from user input and creates Player objects.
   * Sets up player1 and player2 variables for the tournament.
   */
   static void getPlayerNames() {
      Scanner input = new Scanner(System.in);
      
      System.out.println("\n----- PLAYER SETUP -----");
      
      // Get player 1 info
      System.out.print("Player 1 name: ");
      String name1 = input.nextLine();
      player1 = new Player(name1);
      
      // Get player 2 info
      System.out.print("Player 2 name: ");
      String name2 = input.nextLine();
      player2 = new Player(name2);
      
      System.out.println("Welcome " + player1.getName() + " and " + player2.getName() + "!");
   }
  
   
   /**
   * Phase 2: grow fish for one month between rounds.
   * uses same logic as growFish but for one month only
   * @param al the list of fish
   */
   public static void growFishOneMonth(ArrayList<FishableI_a> al) {
      FishableI_a ia;
      boolean debug = false; //turn printing off
      
      //grow all fish in the pond
      for (int i = 0; i < al.size(); i++) {
      //loop over array
         if (debug) {
            System.out.println("==========================");
            System.out.println("Feeding the fish " + i);
            System.out.println("==========================\n");
         }
         ia = al.get(i);
         if (debug) {
            System.out.println(ia);
         }
         try { //must check for need to levelUp
            //use EnglishName because doesn't change with size
            if (ia.getEnglishName().equals("Striped Mullet")) {
               ia.eat("algae");
            } else if (ia.getEnglishName().equals("Goatfish") 
                     || ia.getEnglishName().equals("Yellowfin Goatfish") 
                     || ia.getEnglishName().equals("Square-spot Goatfish"))  {
               ia.eat("worms");
            } else if (ia.getEnglishName().equals("Parrotfish")) {
               ia.eat("algae");
                  
            } else if (ia.getEnglishName().equals("Six-fingered threadfin")) {
               ia.eat("crustaceans");
                  
            }
            if (debug) {
               System.out.println("****After eat and grow: " + ia.getName() 
                        + ": " + ia.getLength() + "\n");
            }
         } catch (FishSizeException fe) {
               /**fish has grown max size. needs to level up.*/
            ia = ia.levelUp();
            if (debug) {
               System.out.println(fe.getMessage());
               System.out.println("**** After levelUp: " + ia + "\n");
            }
         }
            /**update fish in the ArrayList.*/
         al.set(i, ia); 
      } 
      
      System.out.println("Fish have grown for 1 month");
      
   } //close growFishOneMonth method
   
   /**
   * Gets the name of month from its number (1-12).
   * Used for displaying during gameplay.
   * @param monthNum the month number
   * @return the month name
   */
   public static String getMonthName(int monthNum) {
      return MONTH_NAMES[monthNum - 1]; //adjusts for array indexing
   }
 
   /**
   * Phase 2: Prompts user to choose fishing method each time.
   * Avaiable methods: pole, net, throw net, spear, trap
   * @param scanner the Scanner object for players input
   * @return the selected fishing method
   */
   public static String chooseCatchMethod(Scanner scanner) {
      String method = "";
      boolean validInput = false;
      
      System.out.println("\nChoose a fishing method:");
      System.out.println("1. Pole");
      System.out.println("2. Net");
      System.out.println("3. Throw Net");
      System.out.println("4. Spear");
      System.out.println("5. Trap");
      
      while (!validInput) {
         String input = scanner.nextLine().trim();
       
         switch(input) {
            case "1":
               method = "pole";
               validInput = true;
               break;
            case "2":
               method = "net";
               validInput = true;
               break;
            case "3":
               method = "throw net";
               validInput = true;
               break;
            case "4":
               method = "spear";
               validInput = true;
               break;
            case "5":
               method = "trap";
               validInput = true;
               break;
            default:
               System.out.println("Invalid option. please enter 1-5.");
               break;
         }
      }
       
      System.out.println("You selected: " + method);
      return method;
   }
   
   /**
   * Checks if a catch method is allowed for a specific fish.
   * @param chosenMethod the method the player chose
   * @param fish is hooked fish from tank
   * @return true if method is valid
   */
   
   public static boolean isCatchMethodValid(String chosenMethod, FishableI_a fish) {
    // Get the list of valid catch methods for this fish
      String[] validMethods = fish.getCatchMethods();
    
    // Check if the chosen method is in the list of valid methods
      for (String method : validMethods) {
         if (method.equals(chosenMethod)) {
            return true;  // The method is valid for this fish
         }
      }
    
      return false;  // The method is not valid for this fish
   }

     
   /**
   * Displays the fish in a player's sack by length (longest first).
   * @param player the player whose fish to display
   */
   public static void viewPlayerSack(Player player) {
      if (player.getSack().size() == 0) {
         System.out.println("\n" + player.getName() + "'s sack is empty.");
         return;
      }
      System.out.println("\n" + player.getName() + "'s Sack of Fish:");
      
      /**Create copy of sack to sort.*/
      ArrayList<FishableI_a> sortedSack = new ArrayList<>(player.getSack());
     
     /**Sort using collections like in assignment 5.*/
      Collections.sort(sortedSack, Collections.reverseOrder());
     
     /**Display sorted sack.*/
      for (int i = 0; i < sortedSack.size(); i++) {
         FishableI_a fish = sortedSack.get(i);
         System.out.println((i + 1) + ": " + fish.getName() + "\t\t" 
               + String.format("%.3f", fish.getLength()) + " inches");
      } 
   }      
   
   /**
   * Allows player to throw back a fish from their stack.
   * @param player the player throwing back a fish
   * @param fishPond the pond to return the fish to
   * @param scanner scanner for users input
   */
   public static void throwBackFish(Player player, ArrayList<FishableI_a> fishPond, 
                                    Scanner scanner) {
      if (player.getSack().size() == 0) {
         System.out.println("\n" + player.getName() 
                            + "'s sack is empty. You don't have any fish to throw back!");
         return;
      }
     
   /**Display the sorted sack.*/
      ArrayList<FishableI_a> sortedSack = new ArrayList<>(player.sack);
      Collections.sort(sortedSack, Collections.reverseOrder());
   
   
      System.out.println("\n" + player.getName() + "'s Fish (select number to throw back): ");
      for (int i = 0; i < sortedSack.size(); i++) {
         FishableI_a fish = sortedSack.get(i);
         System.out.println(i + ": " + fish.getName() + "\t\t" 
               + String.format("%.3f", fish.getLength()) + " inches");  
      
      }
     
      System.out.println("Enter the number of fish to throw back (or -1 to cancel):");
      try {
         int choice = Integer.parseInt(scanner.nextLine().trim());
      
         if (choice == -1) {
            System.out.println("No fish thrown back.");
            return;
         }
         if (choice >= 0 && choice < player.getSack().size()) {
         /**get fish from sorted list.*/
            FishableI_a fishtoThrowBack = sortedSack.get(choice);
         
         /**remove from players sack.*/
            player.getSack().remove(fishtoThrowBack);
         
         /**add fish back to pond.*/
            fishPond.add(fishtoThrowBack);
            System.out.println("You threw back the " + fishtoThrowBack.getName()  
                  + " (" + fishtoThrowBack.getLength() + " inches)");
         
         } else {
            System.out.println("Invalid fish number.");
         }   
      } catch (NumberFormatException e) {
         System.out.println("Please enter a valid number.");
      }      
   }
     
   /**
   * Phase 2: Simulate fishing/lawai'a for tournament.
   * Players alternate turns for 12 months with 3 catch attempts
   * @param fishPond arrayList of fish to be caught
   */
   public static void lawai_a(ArrayList<FishableI_a> fishPond) {
      Random randGen = new Random();
      FishableI_a ia;
      int chosenFish = 0;
      boolean isCaught = false;
      boolean isLegal = false;
      String option = "";
      String choice = "";
      boolean makeChoice = false;
      Scanner userIn = new Scanner(System.in);
      
      //for each month of the year
      for (int m = 1; m <= 12; m++) {
         currentMonth = m;
         String month = getMonthName(m);
         System.out.println("\n----- Month: " + month.toUpperCase() + "-----");
         
         /** Each player takes turn each month.*/
         for (int playerTurn = 1; playerTurn <= 2; playerTurn++) {
         // get current player
            Player currentPlayer = (playerTurn == 1) ? player1 : player2;
            System.out.println("\n" + currentPlayer.getName() + ", it's your turn!");
         
         /** Each player gets 3 catch attempts.*/
            int attempts = 0;
            while (attempts < 3) {
               boolean keepPlaying = false;
            
               while (!keepPlaying) {
                  System.out.println("\nChoose:");
                  System.out.println("1. Cast out for a fish (Attempt " 
                                      + (attempts + 1) + " of 3)");
                  System.out.println("2. View fishing rules");
                  System.out.println("3. View sack of fish");
                  System.out.println("4. Throw back a fish in the sack");
               
                  option = userIn.nextLine().trim(); //read in from user as a String -less errors
               
                  switch(option) { 
                     case "1":
                     //get fish catching method
                        String catchMethod = chooseCatchMethod(userIn);
                     //try to catch a fish
                        chosenFish = randGen.nextInt(POND_CAPACITY);
                        try {
                           ia = fishPond.get(chosenFish);
                           System.out.println("You have hooked a fish!");
                           String engName = ia.getEnglishName();
                           if (ia.getEnglishName().equals("Six-fingered threadfin")) {
                              System.out.print("  o\n");
                              System.out.print("o      ______/~/~/~/__           /((\n");
                              pause(300);
                              System.out.print("  o  // __            ====__    /_((\n");
                              pause(300);
                              System.out.print(" o  //  @))       ))))      ===/__((\n");
                              pause(300);
                              System.out.print("    ))           )))))))        __((\n");
                              pause(300);
                              System.out.print("    \\\\     \\)     ))))    __===\\ _((\n");
                              pause(300);
                              System.out.print("     \\\\_______________====      \\_((\n");
                              pause(300);
                              System.out.print("                                 \\((\n");
                           } else if (engName.contains("Goatfish") 
                                      || engName.equals("Yellowfin Goatfish") 
                                      || engName.equals("Square-spot Goatfish")) {
                              System.out.print("/\\\n" + "    _/./\n");
                              pause(300);
                              System.out.print(" ,-'    `-:..-'/\n");
                              pause(300);
                              System.out.print(": o )      _  (\n");
                              pause(300);
                              System.out.print("\"`-....,--; `-.\\\n");
                              pause(300);
                              System.out.print("    `'          \n");
                           } else if (engName.equals("Striped mullet")) {
                              System.out.print("  o                 o\n");
                              System.out.print("                  o\n");
                              pause(300);
                              System.out.print("         o   ______      o\n");
                              pause(300);
                              System.out.print("           _/  (   \\_\n");
                              pause(300);
                              System.out.print(" _       _/  (       \\_  O\n");
                              pause(300);
                              System.out.print("| \\_   _/  (   (    0  \\\n");
                              pause(300);
                              System.out.print("|== \\_/  (   (          |\n");
                              pause(300);
                              System.out.print("|=== _ (   (   (        |\n");
                              pause(300);
                              System.out.print("|==_/ \\_ (   (          |\n");
                              pause(300);
                              System.out.print("|_/     \\_ (   (    \\__/\n");
                              pause(300);
                              System.out.print("          \\_ (      _/\n");
                              pause(300);
                              System.out.print("            |  |___/\n");
                              pause(300);
                              System.out.print("           /__/     \n");
                           } else if (engName.equals("Parrotfish")) {
                              System.out.print("                         _.'.__\n");
                              System.out.print("                      _.'      .\n");
                              pause(300);
                              System.out.print("':'.               .''   __ __  .\n");
                              pause(300);
                              System.out.print("  '.:._          ./  _ ''     \"-.__\n");
                              pause(300);
                              System.out.print(".'''-: \"\"\"\"-._    | .                \"-\"._\n");
                              pause(300);
                              System.out.print(" '.     .    \"._.'                       \"\n");
                              pause(300);
                              System.out.print("    '.   \"-.___ .        .'          .  :o'.\n");
                              pause(300);
                              System.out.print("      |   .----  .      .           .'     (\n");
                              pause(300);
                              System.out.print("       '|  ----. '   ,.._                _-'\n");
                              pause(300);
                              System.out.print("        .' .---  |.\"\"  .-:;.. _____.----'\n");
                              pause(300);
                              System.out.print("        |   .-\"\"\"\"    |      '\n");
                              pause(300);
                              System.out.print("      .'  _'         .'    _'\n");
                              pause(300);
                              System.out.print("     |_.-'            '-.'\n");
                           }                           
                           // ia is the current fish object
                           boolean legalMethod = isCatchMethodValid(catchMethod, ia);                         
                           if (legalMethod) {
                              System.out.println("You have caught a fish!");
                              System.out.println(ia);
                           
                              makeChoice = false;
                              while (!makeChoice) { //keep asking to choose until the user chooses
                                 System.out.println("Do you want to keep this fish y/n?" 
                                                     + "(type \"rules\" to see the rules " 
                                                     + "of the game)");
                              //read in from user as a String -- much less errors can happen!
                                 choice = userIn.nextLine().trim();    
                              
                                 if (choice.equals("rules")) {
                                    printRules(); 
                                 } else if (choice.equals("y")) {
                                 //check if fish is legal to keep first
                                    boolean legalSize = ia.isLegalSize();
                                    boolean legalMonth = ia.isInSeason(getMonthName(currentMonth));
                                                                     
                                    fishPond.remove(chosenFish);
                                    currentPlayer.addFish(ia); 
                                    makeChoice = true;  //take fish out of the pond
                                 
                                 /**Consequences if fish is not legal.*/
                                    if (legalSize && legalMonth) {
                                       System.out.println("Your fish is legal and it was" 
                                                           + " added to your sack!");
                                    } else {
                                       System.out.println("You kept an illegal fish!");
                                       if (!legalSize) {
                                          System.out.println("The fish is under the legal " 
                                                              + "size limit.");
                                       }
                                       if (!legalMonth) {
                                          System.out.println("It is " + month + " and season " 
                                                              + "is closed for this fish.");
                                       }
                                       System.out.println("You got a ticket and all of your " 
                                                           + "fish were confiscated!");
                                       currentPlayer.emptySack();
                                    }
                                 } else if (choice.equals("n")) {
                                    System.out.println("You threw the fish back.");
                                    makeChoice = true;
                                 }
                              }
                           } else {
                              System.out.println("Oh no...it got away..." 
                                                 + "\nYou can't catch a fish with the " 
                                                 + catchMethod);
                           }
                        } catch (IndexOutOfBoundsException ie) { //fish has been removed already
                           System.out.println("You didn't hook anything.");
                        }
                     //counts as an attempt
                        attempts++;
                        keepPlaying = true;
                        break;
                  
                     case "2":
                        printRules(); //view fishing rules
                        break;
                  
                     case "3":
                        viewPlayerSack(currentPlayer); //view fish sack
                        break;
                  
                     case "4":
                        throwBackFish(currentPlayer, fishPond, userIn); //throw back fish
                        break;
                  
                     default:
                        System.out.println("Invalid option. Please choose 1-4."); //invalid choice
                        break; 
                  } 
               } 
            } // close for loop, 2 players play for 12 months
         
            System.out.println("\nYou've completed your 3 fishing attempts for this month!");
         } //close players loop
      
      /** after both players had turns, grow fish for one month.*/
         System.out.println("\nFish are growing for the month...");
         growFishOneMonth(fishPond);
      
      }
   
   /**End of tournament.*/
      System.out.println("\n===== END OF TOURNAMENT =====");
      displayTournamentResults();
   }
 
 /**
 * Phase 3: Displays the final results and declares winner.
 * After 12 months of turns, players lists are compared
 * Winner is based on sum of player's 3 longest fish
 */
   public static void displayTournamentResults() {
      System.out.println("\n----- TOURNAMENT RESULTS -----");
   
   /**sorted sacks for each player.*/
      ArrayList<FishableI_a> player1SortedSack = new ArrayList<>(player1.sack);
      ArrayList<FishableI_a> player2SortedSack = new ArrayList<>(player2.sack);
   
   /**sort fish by largest length first.*/
      Collections.sort(player1SortedSack, Collections.reverseOrder());
      Collections.sort(player2SortedSack, Collections.reverseOrder());
   
   /**calculate score by sum of top 3 fish lengths.*/
      double player1Score = calculateTop3Score(player1SortedSack);
      double player2Score = calculateTop3Score(player2SortedSack);
   
   /**display player 1's fish score.*/
      System.out.println("\n" + player1.getName() + "'s Final Catch:");
      displayPlayerFinalCatch(player1SortedSack);
      System.out.println("\n" + player1.getName() + "'s score (top 3 fish total): " 
            + String.format("%.2f", player1Score) + " inches");
   
   /**display player 2's fish score.*/
      System.out.println("\n" + player2.getName() + "'s Final Catch:");
      displayPlayerFinalCatch(player2SortedSack);
      System.out.println("\n" + player2.getName() + "'s score (top 3 fish total): " 
            + String.format("%.2f", player2Score) + " inches");
   
   /**determine winner or tie.*/
      System.out.print("\n-----FINAL RESULTS-----");
      if (player1Score > player2Score) {
         System.out.println(player1.getName() + " WINS with a score of " 
               + String.format("%.2f", player1Score) + " inches!");
      } else if (player2Score > player1Score) {
         System.out.println(player2.getName() + " WINS with a score of " 
               + String.format("%.2f", player2Score) + " inches!");
      } else {
         System.out.println("The tournament ends in a TIE! Both players scored " 
               + String.format("%.2f", player1Score) + " inches!");
      }
   }
   
   /**
   * Calculates player's score based on length of top 3 fish.
   * @param sortedSack player's sack sorted by largest length first.
   * @return sum of top 3 fish lengths or fewer (if sack less than 3)
   */
   public static double calculateTop3Score(ArrayList<FishableI_a> sortedSack) {
      double score = 0.0;
      int fishtoCount = Math.min(3, sortedSack.size());
      
      for (int i = 0; i < fishtoCount; i++) {
         score += sortedSack.get(i).getLength();
      }
      return score;
   }
    
   /**
   *Phase 3: List out more final fish stats of players.
   *@param sortedSack player's sack sorted by largest length first
   */
   
   private static void displayPlayerFinalCatch(ArrayList<FishableI_a> sortedSack) {
      if (sortedSack.isEmpty()) {
         System.out.println("No fish Caught!");
         return;
      }
   
   /**highlighting the top 3 fish counted for scoring.*/
      int fishToHighlight = Math.min(3, sortedSack.size());
      
      for (int i = 0; i < sortedSack.size(); i++) {
         FishableI_a fish = sortedSack.get(i);
         String fishDetails = fish.getName() + " (" + String.format("%.3f", fish.getLength()) 
                              + " inches)";
         if (i < fishToHighlight) {
            System.out.println((i + 1) + ": " + fishDetails + " *SCORING FISH*");
         } else {
            System.out.println((i + 1) + ": " + fishDetails);
         }
      }
   
   /**count total fish caught.*/
      int totalFish = sortedSack.size();
      System.out.println("Total fish caught: " + totalFish);
   
   /**find the biggest and smallest fish.*/
      if (totalFish > 0) {
         FishableI_a biggest = sortedSack.get(0); //already sorted, first is biggest
         FishableI_a smallest = sortedSack.get(totalFish - 1); //last is smallest
      
         System.out.println("Largest fish: " + biggest.getName() 
               + " (" + biggest.getLength() + " inches)");
         System.out.println("Smallest fish: " + smallest.getName() 
               + " (" + smallest.getLength() + " inches)");
      }
   
   /**calculate average length.*/
      double totalLength = 0.0;
      for (FishableI_a fish: sortedSack) {
         totalLength += fish.getLength();
      }
      if (totalFish > 0) {
         double averageLength = totalLength / totalFish;
         System.out.println("Average fish length: " 
                            + String.format("%.2f", averageLength) + "inches");
      }
   
   /**count fish by type.*/
      int moiCount = 0;
      int wekeCount = 0;
      int amaamaCount = 0;
      int uhuCount = 0;
   
      for (FishableI_a fish: sortedSack) {
         String fishName = fish.getClass().getName();
      
         if (fishName.contains("Moi")) {
            moiCount++;
         } else if (fishName.contains("Weke") || fishName.contains("Oama")) {
            wekeCount++;
         } else if (fishName.contains("Ama") || fishName.contains("Pua_ama")) {
            amaamaCount++;
         } else if (fishName.contains("Uhu") || fishName.contains("Ohua")) {
            uhuCount++;
         }
      }
      
      /**Display number of caught fish types.*/
      System.out.println("\nFish types caught: ");
      System.out.println("Moi fish: " + moiCount);
      System.out.println("Weke fish: " + wekeCount);
      System.out.println("Ama'ama fish: " + amaamaCount);
      System.out.println("Uhu fish: " + uhuCount);
      
   }
  
  /**
  * Displays legal requirements for keeping a fish.
  */
   public static void printRules() {
      System.out.println("\n----- FISHING RULES -----");
      pause(1000);
    // Pua_ama (Mullet Family)
      System.out.println("\n**Pua_ama (Mullet Family):**");
      System.out.println("**Legal Size:** 11 inches and above");
      System.out.println("**Season:** Open from April to November, closed from December to March");
      pause(1000);
    // Ohua / Uhu (Fish Family)
      System.out.println("\n**Ohua / Uhu (Fish Family):**");
      System.out.println("**Legal Size:** 12 inches and above");
      System.out.println("**Season:** Always open, you can fish for this fish anytime!");
      pause(1000);
    // Oama / Weke (Fish Family)
      System.out.println("\n**Oama / Weke (Fish Family):**");
      System.out.println("**Legal Size:** No minimum size. You can catch Oama/Weke at any size!");
      System.out.println("**Season:** Always open, you can fish for this fish anytime!");
      pause(1000);
   }

   
   /**
   * prints fish info in readable format.
   * for debugging purposes to fix printing error.
   * @param fishList the list of fish to print
   */
   private static void printFishList(ArrayList<FishableI_a> fishList) {
      System.out.println("Fish in pond:");
      for (int i = 0; i < fishList.size(); i++) {
         FishableI_a fish = fishList.get(i);
         System.out.println((i + 1) + ": " + fish.getName() 
                            + " (" + String.format("%.2f", fish.getLength()) + " inches)");
      }
   }
    
  /**
   * Player class for the tournament.
   * Stores player information including name and caught fish.
   */
   static class Player {
      /** Player's name. */
      private String name;
      
      /** Collection of fish caught by this player. */
      private ArrayList<FishableI_a> sack;
      
      /**
       * Constructor for creating a new player.
       * @param name The player's name
       */
      Player(String name) {
         this.name = name;
         this.sack = new ArrayList<FishableI_a>();
      }
      
      /**
       * Gets the player's name.
       * @return The player's name
       */
      public String getName() {
         return name;
      }
      /**
          * Gets the player's sack of fish.
          * @return The player's fish collection
          */
      public ArrayList<FishableI_a> getSack() {
         return sack;
      }
      
      /**
       * Adds a caught fish to the player's sack.
       * @param fish The fish to add to the player's collection
       */
      public void addFish(FishableI_a fish) {
         sack.add(fish);
      }
      
      /**
       * Empties the player's sack of all fish.
       * Used when player is penalized for keeping illegal fish.
       */
      public void emptySack() {
         sack.clear();    
      }
   }
}