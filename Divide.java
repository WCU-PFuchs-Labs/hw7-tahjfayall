/**
* Author: Tahj Fayall
* Program: Divide
* Date: 09/24/2025
* Edit Date: 10/11/2025
* Editor: Tahj Fayall
**/
import java.util.Random;

public class Divide extends Binop //Inherits abstract methods in Binop class

{
   
   /**
   * Overrides eval() method in Binop
   * Performs division of left by right
   * @return the result of left / right
   */
   public double eval(double left, double right) 
   {
      return left / right; 
   }
   
   /**
   * Overrides default toString method
   * @returns division symbol
   */
   public String toString() 
   {
      return "/"; 
   }
}
