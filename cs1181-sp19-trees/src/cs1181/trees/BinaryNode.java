package cs1181.trees;
/**
 *
 */

/**
 * @param <T> The value class for the node
 */
public class BinaryNode<T>
{
   /** the left sub-tree */
   private BinaryNode<T> left;
   /** the parent node */
   private BinaryNode<T> parent;
   /** thre right sub-tree */
   private BinaryNode<T> right;

   /** the current value */
   private T value;

   /**
    * @param value the value for this new node
    */
   public BinaryNode(final T value)
   {
      this.value = value;
   }

   /**
    * @return the left sub-tree
    */
   public BinaryNode<T> getLeft()
   {
      return left;
   }

   /**
    * @return the parent
    */
   public BinaryNode<T> getParent()
   {
      return parent;
   }

   /**
    * @return the right sub-tree
    */
   public BinaryNode<T> getRight()
   {
      return right;
   }

   /**
    * @return the value
    */
   public T getValue()
   {
      return value;
   }

   /**
    * @param left the left to set
    */
   public void setLeft(final BinaryNode<T> left)
   {
      this.left = left;
   }

   /**
    * @param parent the parent to set
    */
   public void setParent(final BinaryNode<T> parent)
   {
      this.parent = parent;
   }

   /**
    * @param right the right to set
    */
   public void setRight(final BinaryNode<T> right)
   {
      this.right = right;
   }

   /**
    * @param value the value to set
    */
   public void setValue(final T value)
   {
      this.value = value;
   }

   @Override
   public String toString()
   {
      return "BinaryNode [value=" + value + ", parent=" + parent + ", left=" + left + ", right=" + right + "]";
   }
}
