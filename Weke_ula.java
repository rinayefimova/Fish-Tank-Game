import java.util.Random;
/**
 * Weke'ula fish class.
 * A type of adult Weke, sibling to Weke'a.
 * Extends Oama.
 * @author Arina Yefimova
 * @since 2/24/25
 */
public class Weke_ula extends Oama {

   /** constant minimum length for the Weke'ula. */
   private static final double WEKEULA_MIN_LENGTH = 7;
   /** constant minimum length for the Weke'ula. */
   private static final double WEKEULA_MAX_LENGTH = 30;
   
   /**
   * constructor with no parameters.
   */
   public Weke_ula() {
     super("Weke'ula", "Yellowfin Goatfish", "Mulloidichthys vanicolensis",
        WEKEULA_MAX_LENGTH, WEKEULA_MIN_LENGTH,
        WEKEULA_MIN_LENGTH, (WEKEULA_MIN_LENGTH * 2), DIET_ITEMS,
        "white with yellow stripe",
        "yellow", "male or female");
      this.initLength();
      this.initSex();
   }
   
   /**
   * constructor that takes a given length.
   * @param length The fish's new length.
   */
   public Weke_ula(double length) {
      super("Weke'ula", "Yellowfin Goatfish", "Mulloidichthys vanicolensis",
        WEKEULA_MAX_LENGTH, WEKEULA_MIN_LENGTH,
        length, (length * 2), DIET_ITEMS, 
        "white with yellow stripe",
        "yellow", "male or female");
      this.initSex();
   }
   
   /**
   * Initialize the sex of the fish randomly.
   */
   protected void initSex() {
      Random ran = new Random();
      if (ran.nextBoolean()) {
         this.sex = "male";
      } else {
         this.sex = "female";
      }
   }
   
   /**
   * method for simulating the fish growing.
   * different from Oama.java because it does not call levelUp().
   */
   protected void grow() {
      Random ran = new Random();
      double lengthIncrease = ran.nextDouble() * growthRate;
   
      //calculate a new length by adding a random value between 0 and growthRate
      this.length = this.length + lengthIncrease;
      this.weight = this.length * 2;
   }
   
   /**
   * new levelUp() method.
   * @return Weke_ula No further subclasses, so it returns itself.
   */
   public Weke_ula levelUp() {
      return this;
   }
   
} //end class