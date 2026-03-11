import java.util.Random;

/**
* Uhu class (adult parrotfish).
* Final stage of Parrotfish cycle
* Fish changes colors based on sex
* Females can transform into supermales
* @author Arina Yefimova
* @since 2/20/25
*/
public class Uhu extends Panuhunuhu {
   
   /** Maximum length. */
   private static final double UHU_MAX_LENGTH = 30.0;
   
   /** Minimum length. */
   private static final double UHU_MIN_LENGTH = 12.0;

   /** 
   * Constructor for Uhu with random length.
   * Sets basic properties
   * Initializes length and sex
   */
   public Uhu() {
      super("Uhu", 
         UHU_MAX_LENGTH, UHU_MIN_LENGTH,
         UHU_MIN_LENGTH, (UHU_MIN_LENGTH * 2),
         DIET_ITEMS, "gray", "reddish gray", "none");
      this.initLength(); //initializes random length and updates weight to length*2
      this.initSex(); //calls from inherited method using 0 or 1 to set sex
   }

   /**
   * Constructor with given length.
   * @param length The length of this fish
   * @throws FishSizeException if length is invalid
   */
   public Uhu(double length) {
      super("Uhu", 
         UHU_MAX_LENGTH, UHU_MIN_LENGTH,
         length, (length * 2),
         DIET_ITEMS, "gray", "reddish gray", "none");
      if (length > UHU_MAX_LENGTH || length < UHU_MIN_LENGTH) {
         throw new FishSizeException("Uhu must be between " 
            + UHU_MIN_LENGTH + " and " 
            + UHU_MAX_LENGTH + " inches long.");
      }
      this.initSex();
   }

   /**
   * Constructor with length and sex.
   * Includes chance for females to become supermales
   * @param length The length of this fish
   * @param sex The sex of this fish
   */
   public Uhu(double length, String sex) {
      super("Uhu", 
         UHU_MAX_LENGTH, UHU_MIN_LENGTH,
         length, (length * 2),
         DIET_ITEMS, "gray", "reddish gray", sex);
      
      // If female, random chance to become supermale
      if (sex.equals("female")) {
         Random ran = new Random();
         if (ran.nextDouble() < 0.3) { // 30% chance
            this.sex = "supermale";
            this.bodyColor = "blue-green";
            this.finColor = "blue"; 
         }
      }
   }

   /**
   * Changes fish color.
   * males/females: gray body, reddish gray fins
   * supermales: blue-green body, blue fins
   * @param bodyColor new body color
   * @param finColor new fin color
   * @throws FishColorException if invalid color combo for sex
   */
   public void setColor(String bodyColor, String finColor) {
      if (this.sex.equals("female")) {
         if (bodyColor.equals("blue-green") && finColor.equals("blue")) {         
            this.bodyColor = bodyColor;  
            this.finColor = finColor;
            this.sex = "supermale"; //female becomes supermale when blue
         } else {
            throw new FishColorException("Females can only change to blue-green with blue fins!");
         }
      } else {
         throw new FishColorException("Only female Uhu can change color!");
      }
   }
 
   /**
   * Changes fish's sex.
   * Only females can change sex to supermales
   * Updates colors with sex change to supermale
   * @throws FishSexException if invalid sex change (non-female changes sex)
   */
   public void changeSex() {
      if (this.sex.equals("female")) {
         this.sex = "supermale";
         this.bodyColor = "blue-green";  
         this.finColor = "blue"; 
      } else {
         throw new FishSexException("Only female Uhu can change sex to supermale!");
      }
   }

   /**
   * Levelup returns this since Uhu is final stage.
   * @return this Uhu 
   */
   public FishableI_a levelUp() { //matches return type
      return this;
   }
   
   /**
   * Used by eat method to increase fish length.
   * Overriden for final stage so it doesn't level up
   */
   @Override
   public void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
      /**calculate new length by adding random value between 0 and growth rate.*/
      this.length = this.length + lengthIncrease;
      this.weight = this.length * 2;
   }
   

//============= Required by Fishable (overrides)==================
/** 
* Determines if fish is legal to keep due to length.
* Uhu is legal size if 12 inches or more
* @return true if length is 12 inches or above
*/
   @Override
   public boolean isLegalSize() {
      return this.length >= 12;
   }

/**
* Determines if this fish can be used to eat.
* @return true, Uhu always legal to eat if legal size
*/
   public boolean isGamefish() {
      return true;
   }

/** All other fishable methods inherited from Ohua.*/
}