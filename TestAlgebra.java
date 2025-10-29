import java.util.Random;

public class TestAlgebra
{
   public static void main (String [] args)
   {
      double[][] testVals = //Set values for X0, X1, and X2
      {
        {1.0, 2.0, 3.0},
        {4.0, 5.0, 6.0} 
      };
      
      Node expression1 = buildExpression();
      Node expression2 = buildExpression();
      
      for (double[] values : testVals) //Prints value and evals random expression tree
      {
         System.out.println("When {X0, X1, X2} = {" + values[0] + "," + values[1] + "," + values[2] + "}:");
         System.out.println(expression1.toString() + " = " + expression1.eval(values)); //Displays expression and result
         System.out.println();
         
         System.out.println("When {X0, X1, X2} = {" + values[0] + "," + values[1] + "," + values[2] + "}:");
         System.out.println(expression2.toString() + " = " + expression2.eval(values)); //Displays expression and result
         System.out.println();
      }
   }
   
   /**
   * Generates a random terminal node for
   * expression tree
   */
   private static Node randomTerminal()
   {
      if (Math.random() < 0.5) //Flipping virtual coin
      {
         return new Node(new Const((int)(Math.random() * 20 + 1))); //Returns Const between 1 and 20
      }
      
      else
      {
         return new Node(new Variable((int)(Math.random() * 3))); //Returns Variable between 0 and 2
      }
   }
   
   /**
   * Randomly selects a binop and builds a new
   * node by combing two child nodes
   * @param left - child node
   * @param right - child node
   */
   private static Node randomOperator(Node left, Node right)
   {
      int op = (int)(Math.random() * 4); //Between 0 and 3
      
      //Returns new node with operator
      switch (op)
      {
         case 0: return new Node(new Plus(), left, right);
         case 1: return new Node(new Minus(), left, right);
         case 2: return new Node(new Mult(), left, right);
         default: return new Node(new Divide(), left, right);
      }
   }
   
   /**
   * Builds algebraic expression tree with
   * fixed structure
   */
   private static Node buildExpression()
   {
      //Four nodes for test
      Node a1 = randomTerminal();
      Node a2 = randomTerminal();
      Node a3 = randomTerminal();
      Node a4 = randomTerminal();
      
      //Combines to two subtrees
      Node b1 = randomOperator(a1, a2);
      Node b2 = randomOperator(a3, a4);
      
      //Combines subtrees into final node
      return randomOperator(b1, b2);
   }
}