/**
* interface for I_a that change color.
* @author Arina Yefimova
* @since 2/11/22
*/
public interface ColorChangeable {

   /**
   * Some fish can dynamically change colors, this method should do that.
   */
   public abstract void setColor(String bodyColor, String finColor);
}