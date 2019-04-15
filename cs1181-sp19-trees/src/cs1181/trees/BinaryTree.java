/**
 *
 */
package cs1181.trees;

/**
 * @param <T> the type of element in the tree
 */
public class BinaryTree<T extends Comparable<T>>
{
   /** the root of the tree */
   private BinaryNode<T> root;

   /**
    * delete the value from the tree
    *
    * @param  value the value to delete
    * @return       {@code true} if the value was deleted, or {@code false} if the value was not found
    */
   public boolean delete(final T value)
   {
      final BinaryNode<T> nodeToDelete = findNode(root, value);
      if (nodeToDelete != null)
      {
         final BinaryNode<T> replacementNode = getReplacementNode(nodeToDelete);

         // if we found a suitable child to use as a replacement, then remove it from the tree
         BinaryNode<T> parent = null;
         if (replacementNode != null)
         {
            // remove the replacement node from its parent.
            parent = replacementNode.getParent();
            if (parent != null)
            {
               if (parent.getLeft() == replacementNode)
               {
                  parent.setLeft(null);
               }
               else
               {
                  parent.setRight(null);
               }
            }

            // put the replacement where the node to delete was
            replacementNode.setParent(parent);
            replacementNode.setLeft(nodeToDelete.getLeft());
            replacementNode.setRight(nodeToDelete.getRight());
         }

         // replace the node to delete with the replacement node
         parent = nodeToDelete.getParent();
         if (parent != null)
         {
            if (parent.getLeft() == nodeToDelete)
            {
               parent.setLeft(replacementNode);
            }
            else
            {
               parent.setRight(replacementNode);
            }
         }
         else
         {
            root = replacementNode;
         }

         return true;
      }
      return false;

   }

   /**
    * find the node with the given value
    *
    * @param  current the sub-tree to search
    * @param  value   the value to match
    * @return         the node, if found, or {@code null} if not found
    */
   private BinaryNode<T> findNode(final BinaryNode<T> current, final T value)
   {
      if (current == null)
      {
         return null;
      }
      final int rc = value.compareTo(current.getValue());
      if (rc == 0)
      {
         return current;
      }
      else if (rc < 0)
      {
         return findNode(current.getLeft(), value);
      }
      else
      {
         return findNode(current.getRight(), value);
      }
   }

   /**
    * get the left-most child of the given node
    *
    * @param  parent the node to search
    * @return        the node the left most child of the parent
    */
   private BinaryNode<T> getLeftMostNode(final BinaryNode<T> parent)
   {
      if (parent == null)
      {
         return null;
      }

      BinaryNode<T> current = parent.getLeft();
      while (current != null && current.getLeft() != null)
      {
         current = current.getLeft();
      }
      return current;
   }

   /**
    * get the node that is closest in value to the the given node
    *
    * @param  parent the node to search
    * @return        the node closest to the middle of the sub-tree.
    */
   private BinaryNode<T> getReplacementNode(final BinaryNode<T> parent)
   {
      if (parent == null)
      {
         throw new IllegalArgumentException("Null parent");
      }
      BinaryNode<T> replacementNode = parent.getLeft();
      // find the replacement node for the deleted node
      if (replacementNode != null)
      {
         final BinaryNode<T> rightMostNode = getRightMostNode(replacementNode);
         if (rightMostNode != null)
         {
            replacementNode = rightMostNode;
         }
      }
      // no nodes on the the left were found. How about the right?
      if (replacementNode == null)
      {
         replacementNode = parent.getRight();
         final BinaryNode<T> leftMostNode = getLeftMostNode(replacementNode);
         if (leftMostNode != null)
         {
            replacementNode = leftMostNode;
         }
      }
      return replacementNode;
   }

   /**
    * get the right-most child of the parent
    *
    * @param  parent the sub-tree to walk down
    * @return        the right most child of this tree, or {@code null}
    */
   private BinaryNode<T> getRightMostNode(final BinaryNode<T> parent)
   {
      if (parent == null)
      {
         return null;
      }

      BinaryNode<T> current = parent.getRight();
      while (current != null && current.getRight() != null)
      {
         current = current.getRight();
      }

      return current;
   }

   /**
    * @return the root node, if any
    */
   public BinaryNode<T> getRoot()
   {
      return root;
   }

   /**
    * insert the node into the tree in the appropriate location
    *
    * @param value the new value to add to the tree
    */
   public void insert(final T value)
   {
      if (value == null)
      {
         throw new IllegalArgumentException("Cannot insert null values");
      }

      final BinaryNode<T> newNode = new BinaryNode<>(value);
      final BinaryNode<T> current = root;

      if (current == null)
      {
         root = newNode;
      }
      else
      {
         insertNode(current, newNode);
      }
   }

   /**
    * insert the node as a child of the current node
    *
    * @param current the node to compare with the new node
    * @param newNode the new node to add to the tree
    */
   private void insertNode(final BinaryNode<T> current, final BinaryNode<T> newNode)
   {
      final int rc = newNode.getValue().compareTo(current.getValue());
      if (rc < 0)
      {
         final BinaryNode<T> left = current.getLeft();
         if (left != null)
         {
            insertNode(left, newNode);
         }
         else
         {
            current.setLeft(newNode);
            newNode.setParent(current);
         }
      }
      else
      {
         final BinaryNode<T> right = current.getRight();
         if (right != null)
         {
            insertNode(right, newNode);
         }
         else
         {
            current.setRight(newNode);
            newNode.setParent(current);
         }
      }
   }

   /**
    * return the size of the tree
    *
    * @return the size of the tree
    */
   public int size()
   {
      return size(root);
   }

   /**
    * find the size of the sub-tree
    *
    * @param  node the node to count
    * @return      the number of nodes in the sub-tree
    */
   private int size(final BinaryNode<T> node)
   {
      if (node == null)
      {
         return 0;
      }
      return 1 + size(node.getLeft()) + size(node.getRight());
   }

   @Override
   public String toString()
   {
      final StringBuilder builder = new StringBuilder("BinaryTree \nroot: ");
      final String indent = "";
      toString(indent, builder, root);
      return builder.toString();
   }

   /**
    * internal method to print the children and this node
    *
    * @param indent  the indent level
    * @param builder the builder to accept all the output text
    * @param current the sub-tree to print
    */
   private void toString(final String indent, final StringBuilder builder, final BinaryNode<T> current)
   {
      final String newIndent = indent + " | ";

      // deal with the degenerate case
      if (current == null)
      {
         builder.append(indent + "null");
         return;
      }

      // write out the current node
      builder.append(indent);
      builder.append(current.getValue().toString());

      // now print the left children
      builder.append("\n" + indent + "left:\n");
      toString(newIndent, builder, current.getLeft());

      builder.append("\n" + indent + "right:\n");
      toString(newIndent, builder, current.getRight());
   }
}
