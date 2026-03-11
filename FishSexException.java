/**
* Exception for I_a class sex errors.
* @author Arina Yefimova
*/
public class FishSexException extends RuntimeException {
  
  /**
  * Constructor. 
  * @param newMessage The error message to be sent.
  */
   public FishSexException(String newMessage) {
      super(newMessage);
   }
}
