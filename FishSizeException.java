/**
* Exception for I_a class length errors.
* @author Arina Yefimova
*/
public class FishSizeException extends RuntimeException {
  
  /**
  * Constructor. 
  * @param newMessage The error message to be sent.
  */
   public FishSizeException(String newMessage) {
      super(newMessage);
   }
}
