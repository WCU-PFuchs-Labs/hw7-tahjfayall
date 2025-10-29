/**
 * Code Template Author: David G. Cooper
 * Purpose: A Binary Tree class for Arithmetic evaluation
 * Edit Date: 10/21/2025
 * Editor: Tahj Fayall
 */
 import java.util.Random;

public class Node implements Cloneable {
    private Node left;
    private Node right;
    private Op operation;
    protected int depth;

    public Node(Unop operation) {
        this.operation = operation;
        this.depth = 0;
    }
    
    public Node(Binop operation) {
        this.operation = operation;
        this.depth = 0;
    }

    public Node(Binop operation,Node left, Node right) {
        this.left = left;
        this.right = right;
        this.operation = operation;
        this.depth = 0;
    }
    public double eval(double[] values) {
        if (operation instanceof Unop) {
              return ((Unop)operation).eval(values);
        } else if (operation instanceof Binop) {
              return ((Binop)operation).eval(left.eval(values),right.eval(values));
        } else {
              System.err.println("Error operation is not a Unop or a Binop!");
              return 0.0;
        }
    }
    
    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand)
    {
      if(!(operation instanceof Binop))
      {
         return;
      }
      
      if(depth >= maxDepth)
      {
         left = nf.getTerminal(rand);
         right = nf.getTerminal(rand);
         left.depth = this.depth + 1;
         right.depth = this.depth + 1;
      }
      
      else
      {
         int totalChoices = nf.getNumOps() + nf.getNumIndepVars();
         int leftChoice = rand.nextInt(totalChoices + 1);
         
         if(leftChoice < nf.getNumOps())
         {
            left = nf.getOperator(rand);
            left.depth = this.depth + 1;
            left.addRandomKids(nf, maxDepth, rand);
         }
         
         else
         {
            left = nf.getTerminal(rand);
            left.depth = this.depth + 1;
         }
         
         int rightChoice = rand.nextInt(totalChoices + 1);
         
         if(rightChoice < nf.getNumOps())
         {
            right = nf.getOperator(rand);
            right.depth = this.depth + 1;
            right.addRandomKids(nf, maxDepth, rand);
         }
         
         else
         {
            right = nf.getTerminal(rand);
            right.depth = this.depth + 1;
         }
      }
    }
    
    public Object clone()
    {
      Object o = null;
      try
      {
         o = super.clone();
      }
      
      catch(CloneNotSupportedException e)
      {
         System.out.println("Op can't clone.");
      }
      
      Node b = (Node) o;
      if(left != null)
      {
         b.left = (Node) left.clone();
      }
      
      if(right != null)
      {
         b.right = (Node) right.clone();
      }
      
      if(operation != null)
      {
         b.operation = (Op) operation.clone();
      }
      
      return b;
    }
    
    public String toString()
    {
      if (operation instanceof Unop)
      {
         return operation.toString();
      }
      else
      {
         return "(" + left.toString() + " " + operation.toString() + " " + right.toString() + ")";
      }
    }
}
