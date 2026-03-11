import java.util.Random;

/**
* Anae Fish Object Class. 
* Mullet Family. 
* @author Arina Yefimova
* @since 02/25/2025
*/


public class Anae extends Ama_ama {

   /** anae max length. */
   private static final double ANAE_MAX_LENGTH = 27.00;
   /** anae min length. */
   private static final double ANAE_MIN_LENGTH = 12.00;

   /**
   * Constructor for making 'Anae
   * uses I_a superclass constructor
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Anae() {
      super("'Anae", ANAE_MAX_LENGTH, ANAE_MIN_LENGTH, 
      ANAE_MIN_LENGTH, (ANAE_MIN_LENGTH * 2), DIET_ITEMS, "silver", 
         "silver", "male or female");   
      this.initSex();
      this.initLength();
   }
   
   /**
   * Constructor for making 'Anae.
   * uses I_a superclass constructor
   * @param length The length of this fish
   * randomly sets length within allowed values after calling constructor.
   * This is the juvenile of the species and has no sex determination.
   */
   public Anae(double length) {
      super("'Anae", ANAE_MAX_LENGTH, ANAE_MIN_LENGTH, 
      length, (length * 2), DIET_ITEMS, "silver", 
         "silver", "male or female");   
      this.initSex();
   }
      
   /**
   * Constructor for making 'Anae with a specific length.
   * uses I_a superclass constructor
   * @param length The length of this fish
   * @param sex sex
   * @throws FishSizeException if length > maxLength or < minLength.
   */
   public Anae(double length, String sex) {
      super("'Anae", ANAE_MAX_LENGTH, ANAE_MIN_LENGTH, length, (length * 2), 
         DIET_ITEMS, "silver", "silver", sex);  
   }
   
   /**
   * Should be used by eat method to increase length of fish. 
   * Should determine a new length and internally call setLength
   * Does not throw FishSizeException because this is the final type
   */
   protected void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
   
      //calculate a new length by adding a random value between 0 and growthRate
      this.length = this.length + lengthIncrease;
      this.weight = this.length * 2;
   }
}
   
