/**
* Exception for I_a class food errors.
* @author Arina Yefimova
*/
public class FishFoodException extends RuntimeException {
  
  /**
  * Constructor. 
  * @param newMessage The error message to be sent.
  */
   public FishFoodException(String newMessage) {
      super(newMessage);
   }
}
