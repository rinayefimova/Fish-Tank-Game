import java.util.Random;
/** 'Ohua class for I'a.
* Smallest variant of the Uhu species.
* @author Arina Yefimova
* @since 02/20/2025
*/

public class Ohua extends FishableI_a implements ColorChangeable, SexChangeable {

   /**Gender for this subclass of the Uhu family. */
   protected static final String REPRODUCTIVE_MODE = "protogynous";
   /**What the fish likes to eat. */
   protected static final String[] DIET_ITEMS = {"algae"};
   /**Minimum length for an Ohua. */
   private static final double OHUA_MIN_LENGTH = 0.01;
   /**Maximum length for an Ohua. */
   private static final double OHUA_MAX_LENGTH = 4.0;
   
   //uses I'a constructor.
   /**Constructor for the Ohua.*/
   public Ohua() {
      super("'Ohua", "Parrotfish", "Scaridae",
         OHUA_MAX_LENGTH, OHUA_MIN_LENGTH, 0.1, 2.0,
         DIET_ITEMS, "gray", "reddish gray", "none");
      this.initLength();
   } //closes constructor with unknown length

   //second constructor with a set length
   /**Ohua constructor for a specific length.
   * @param length the specified length of the fish.
   * @throws FishSizeException if length not within minimum to maximum length range.
   */
   public Ohua(double length) {
      super("'Ohua", "Parrotfish", "Scaridae",
         OHUA_MAX_LENGTH, OHUA_MIN_LENGTH, length, (length * 2),
         DIET_ITEMS, "gray", "reddish gray", "none");
   } //closes constructor with known length
   
   /**Protected constructor, as certain parameters are consistent across subclasses.
   * These parameters will be the English and scientific names.
   * @param name The name of the fish.
   * @param maxLength The longest length an 'Ohua can be.
   * @param minLength The shortest length an 'Ohua can be.
   * @param length The length of this fish.
   * @param weight The weight of this fish.
   * @param diet The food this fish eats.
   * @param bodyCol The colour of this fish's body.
   * @param finCol The colour of this fish's fins.
   * @param sex The sex of this fish.
   * @throws FishSizeException if length not within minimum to maximum length range.
   */
   
   protected Ohua(String name, double maxLength, double minLength,
       double length, double weight, String[] diet,
       String bodyCol, String finCol, String sex) {
   
      super(name, "Parrotfish", "Scaridae", maxLength, minLength,
         length, weight, diet, bodyCol, finCol, sex);
   } //closes protected constructor
   
   //Measurement methods for the 'Ohua.
   /**Sets the initial length of the fish within the length limits. */
   protected void initLength() {
      Random ranLen = new Random();
      this.length = this.minLength
         + (this.maxLength - this.minLength) * ranLen.nextDouble();
      this.weight = this.length * 2;
   }
   
   /** Sets the length of the fish to newLength.
   * @param newLength The new length of this fish.
   * @throws FishSizeException if length not within minimum to maximum length range.
   */
   public void setLength(double newLength) throws FishSizeException {
      
      if (newLength >= this.minLength && newLength < this.maxLength) {
         this.length = newLength;
      } else {
         throw new FishSizeException(this.name + " needs to be within "
            + this.minLength + " and " + this.maxLength + " inches long.");
      }
   } //closes setLength
   
   /**Changes the fish's weight to newWeight.
   * @param newWeight The fish's new weight.
   */
   public void setWeight(double newWeight) {
      this.weight = newWeight;
   }
   
   //Eating and growth methods.
   /**Method to check if the fish can eat for growth.
   * If it can, then it will call grow method.
   * @param food The food the Ohua eats.
   * @throws FishFoodException if the item is not what the fish eats.
   */
   public void eat(String food) {
      boolean isEdible = false;
      for (int i = 0; i < DIET_ITEMS.length; i++) {
         if (food.equals(DIET_ITEMS[i])) {
            isEdible = true;
         }
      }
      if (isEdible) {
         this.grow();
      } else {
         throw new FishFoodException(this.name + " doesn't eat " + food + ".");
      }
      
   }
   
   /**The growth method, called when eat method successful.
   * Determines how much the fish will grow using setLength.
   * @throws FishSizeException if the fish is too big for its name.
   */
   public void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
      double newLen = this.length + lengthIncrease;
      
      if (newLen > this.maxLength) {
         throw new FishSizeException("The fish has outgrown its name,"
            + "it needs to level up.");
      } else {
         this.length = newLen;
         this.weight = 2 * newLen;
      }
   }
   
   /**Levels the 'Ohua up to a Panuhunuhu.
   * @return a new Panuhunuhu of the max 'Ohua length.
   */
   public FishableI_a levelUp() {
      return new Panuhunuhu(this.maxLength);
   }
   /**
   * Changes the fish colors.
   * @param bodyColor new body color
   * @param finColor new fin color
   * @throws FishColorException if invalid color change
   */
   public void setColor(String bodyColor, String finColor) {
      throw new FishColorException("'Ohua cannot change color.");
   }
   
   //Sex-changing functions.
   /**Changes the sex of this 'Ohua.
   * @throws FishSexException when 'Ohua not big enough to grow.
   */
   public void changeSex() {
      throw new FishSexException("'Ohua cannot change sex unless levelling up.");
   }
   
   /**Returns the reproductive mode of this sexchanging fish.
   * @return The fish's reproductive mode.
   */
   public String getReproductiveMode() {
      return REPRODUCTIVE_MODE;
   }

   //============= Required by Fishable ==================
/**
* Determines if fish is legal to keep length.
* Ohya never legal size (must be 12 in or more to take)
* @return false, max length is under 12 inches
*/
   public boolean isLegalSize() {
      return false;
   }

/**
* Determines if fish can be used as a baitfish.
* @return false, this fish is not used for bait
*/
   public boolean isBait() {
      return false;
   }
/** Determines is fish can be used to eat.
* @return false, Ohua never legal to eat
*/
   public boolean isGamefish() {
      return false;
   }
/**
* Determines if fish is in season.
* Season is always open for Uhu family
* @param month the current month 
* @return true, season always open
*/
   public boolean isInSeason(String month) {
      return true;
   }
/**
* Returns the methods of catching this fish.
* @return an array of fishing methods
*/
   public String[] getCatchMethods() {
      return new String[] {"throw net", "spear", "pole"};
   }
/**
* Returns the max number of fish you are allowed.
* @return Integer.MAX_VALUE (no limit)
*/
   public int getBagLimit() {
      return Integer.MAX_VALUE;
   }
}
