/**
* Exception for I_a class color errors.
* @author Arina Yefimova
*/
public class FishColorException extends RuntimeException {
  
  /**
  * Constructor. 
  * @param newMessage The error message to be sent.
  */
   public FishColorException(String newMessage) {
      super(newMessage);
   }
}
