import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cs1181.trees.BinaryNode;
import cs1181.trees.BinaryTree;

/**
 *
 */

/**
 *
 */
@SuppressWarnings("static-method")
public class TestBinaryTree
{
   /**
    * make a tree using the given values.
    *
    * @param  values the values in the order to add to the tree
    * @return        the filled out tree
    */
   public BinaryTree<Integer> makeTree(final int... values)
   {
      final BinaryTree<Integer> tree = new BinaryTree<>();
      for (final int v : values)
      {
         tree.insert(Integer.valueOf(v));
      }
      return tree;
   }

   /**
    * make a tree using the given values.
    *
    * @param  values the values in the order to add to the tree
    * @return        the filled out tree
    */
   public BinaryTree<String> makeTree(final String... values)
   {
      final BinaryTree<String> tree = new BinaryTree<>();
      for (final String v : values)
      {
         tree.insert(v);
      }
      return tree;
   }

   /**
    * test the tree by adding a node smaller than the root
    */
   @Test
   public void testAddLeftNode()
   {
      final BinaryTree<String> tree = new BinaryTree<>();

      final String expectedRoot = "TestAddLeftNode";
      tree.insert(expectedRoot);

      final String expectedLeft = "Left";
      tree.insert(expectedLeft);

      System.out.println(tree.toString());

      final BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRoot, root.getValue());

      assertNotNull(root.getLeft());
      assertEquals(expectedLeft, root.getLeft().getValue());

      assertNull(root.getRight());
   }

   /**
    * test the names in the tree <br>
    * should look like:<br>
    *
    * <pre>
    *                 Steve
    *               /       \
    *          Alice         Ted
    *               \
    *                Carol
    *               /
    *            Bob
    * </pre>
    */
   @Test
   public void testAddNames()
   {
      final BinaryTree<String> tree = new BinaryTree<>();
      System.out.println("testAddNames");
      final String[] values =
      { "Steve", "Alice", "Carol", "Bob", "Ted" };

      for (final String v : values)
      {
         tree.insert(v);
      }
      System.out.println(tree.toString());

      final BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertNotNull(root.getLeft());
      assertNotNull(root.getRight());
      assertEquals("Steve", root.getValue());

      final BinaryNode<String> alice = root.getLeft();
      assertNotNull(alice);
      assertNull(alice.getLeft());
      assertNotNull(alice.getRight());
      assertEquals("Alice", alice.getValue());

      final BinaryNode<String> carol = alice.getRight();
      assertNotNull(carol);
      assertNotNull(carol.getLeft());
      assertNull(carol.getRight());
      assertEquals("Carol", carol.getValue());

      final BinaryNode<String> bob = carol.getLeft();
      assertNotNull(bob);
      assertNull(bob.getLeft());
      assertNull(bob.getRight());
      assertEquals("Bob", bob.getValue());

      final BinaryNode<String> ted = root.getRight();
      assertNotNull(ted);
      assertNull(ted.getLeft());
      assertNull(ted.getRight());
      assertEquals("Ted", ted.getValue());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testAddNull()
   {
      final BinaryTree<String> tree = new BinaryTree<>();

      assertThrows(IllegalArgumentException.class, () -> tree.insert(null));
   }

   /**
    * test the empty tree
    */
   @Test
   public void testAddRightAndLeftNode()
   {

      final String expectedRoot = "MtestAddRightAndLeftNode";
      final String expectedRight = "Right";
      final String expectedLeft = "Left";

      final BinaryTree<String> tree = makeTree(expectedRoot, expectedRight, expectedLeft);
      System.out.println(tree.toString());

      final BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRoot, root.getValue());

      final BinaryNode<String> right = root.getRight();
      assertNotNull(right);
      assertEquals(expectedRight, right.getValue());

      final BinaryNode<String> left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeft, left.getValue());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testAddRightNode()
   {

      final String expectedRoot = "MTestAddRightNode";
      final String expectedRight = "Right";

      final BinaryTree<String> tree = makeTree(expectedRoot, expectedRight);
      System.out.println(tree.toString());

      final BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRoot, root.getValue());

      assertNotNull(root.getRight());
      assertEquals(expectedRight, root.getRight().getValue());

      assertNull(root.getLeft());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testAddRoot()
   {
      final String value = "testAddRoot";

      final BinaryTree<String> tree = makeTree(value);
      System.out.println(tree.toString());

      final BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(value, root.getValue());
      assertNull(root.getLeft());
      assertNull(root.getRight());
      assertEquals(1, tree.size());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testDeleteEmptyTree()
   {
      final BinaryTree<String> tree = new BinaryTree<>();

      System.out.println(tree.toString());

      assertFalse(tree.delete("bob"));
      assertNull(tree.getRoot());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testDeleteLeftLeaf()
   {
      final String expectedRootValue = "root";
      final String expectedLeftValue = "left";

      final BinaryTree<String> tree = makeTree(expectedRootValue, expectedLeftValue);
      System.out.println(tree.toString());

      BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRootValue, root.getValue());

      assertNotNull(root.getLeft());
      assertEquals(expectedLeftValue, root.getLeft().getValue());

      assertNull(root.getRight());

      // ok, now delete the left node
      assertTrue(tree.delete(expectedLeftValue));

      root = tree.getRoot();
      assertNotNull(root);
      assertNull(root.getLeft());
      assertNull(root.getRight());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testDeleteLeftParentBoth()
   {
      final String expectedRootValue = "mroot";
      final String expectedLeftValue = "leftMiddle";
      final String expectedLeftLeftValue = "left";
      final String expectedLeftRightValue = "leftRight";

      final BinaryTree<String> tree = makeTree(expectedRootValue, expectedLeftValue, expectedLeftLeftValue,
            expectedLeftRightValue);

      System.out.println("testDeleteLeftParentBoth");
      System.out.println(tree.toString());

      BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRootValue, root.getValue());

      BinaryNode<String> left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeftValue, left.getValue());

      assertNull(root.getRight());

      // ok, now delete the left node
      assertTrue(tree.delete(expectedLeftValue));

      System.out.println("After");
      System.out.println(tree.toString());

      root = tree.getRoot();
      assertNotNull(root);

      left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeftLeftValue, left.getValue());
      assertNull(left.getLeft());
      assertNotNull(left.getRight());

      assertEquals(expectedLeftRightValue, left.getRight().getValue());
      assertNull(root.getRight());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testDeleteLeftParentLeftOnly()
   {
      final String expectedRootValue = "mroot";
      final String expectedLeftValue = "leftMiddle";
      final String expectedLeftLeftValue = "left";

      final BinaryTree<String> tree = makeTree(expectedRootValue, expectedLeftValue, expectedLeftLeftValue);
      System.out.println(tree.toString());

      BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRootValue, root.getValue());

      BinaryNode<String> left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeftValue, left.getValue());

      assertNull(root.getRight());

      // ok, now delete the left node
      assertTrue(tree.delete(expectedLeftValue));

      root = tree.getRoot();
      assertNotNull(root);

      left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeftLeftValue, left.getValue());
      assertNull(left.getLeft());
      assertNull(left.getRight());

      assertNull(root.getRight());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testDeleteLeftParentRightOnly()
   {
      final String expectedRootValue = "mroot";
      final String expectedLeftValue = "leftMiddle";
      final String expectedLeftRightValue = "leftRight";

      final BinaryTree<String> tree = makeTree(expectedRootValue, expectedLeftValue, expectedLeftRightValue);
      System.out.println(tree.toString());

      BinaryNode<String> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(expectedRootValue, root.getValue());

      BinaryNode<String> left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeftValue, left.getValue());

      assertNull(root.getRight());

      // ok, now delete the left node
      assertTrue(tree.delete(expectedLeftValue));

      root = tree.getRoot();
      assertNotNull(root);

      left = root.getLeft();
      assertNotNull(left);
      assertEquals(expectedLeftRightValue, left.getValue());
      assertNull(left.getLeft());
      assertNull(left.getRight());

      assertNull(root.getRight());
   }

   /**
    * test delete when the replacement node has children
    *
    * <pre>
    *   2
    *    \
    *     7
    *    / \
    *   5   9
    *  / \   \
    * 2   6   11
    *  \
    *   5
    *  /
    * 4
    * </pre>
    */
   @Test
   public void testDeleteReplacementHasChildren()
   {
      System.out.println("testDeleteReplacementHasChildren");
      final BinaryTree<Integer> tree = makeTree(2, 7, 5, 2, 6, 9, 5, 11, 4);
      System.out.println(tree);
      assertEquals(9, tree.size());

      final Integer valueToDelete = Integer.valueOf(5);
      tree.delete(valueToDelete);
      System.out.println("After" + tree);

      // walk down the to the node that should be deleted
      final BinaryNode<Integer> root = tree.getRoot();
      assertNotNull(root);
      assertEquals(Integer.valueOf(2), root.getValue());

      assertNull(root.getLeft());
      BinaryNode<Integer> child = root.getRight();
      assertNotNull(child);
      assertEquals(Integer.valueOf(7), child.getValue());

      assertNotNull(child.getLeft());
      assertNotNull(child.getRight());

      // ok, this is the child that was deleted. Bad news, the replacement node has the same value.
      child = child.getLeft();
      assertEquals(Integer.valueOf(4), child.getValue());

      // the replacement node should have the same children. 2 and 6.
      assertNotNull(child.getLeft());
      assertNotNull(child.getRight());

      final BinaryNode<Integer> child6 = child.getRight();
      assertEquals(Integer.valueOf(6), child6.getValue());

      child = child.getLeft();
      assertEquals(Integer.valueOf(2), child.getValue());

      // the new child of 2 should be 4 after the replacement was moved
      assertNull(child.getLeft());
      assertNull(child.getRight());

      child = child6.getLeft();
      assertEquals(Integer.valueOf(5), child.getValue());

   }

   /**
    * test the empty tree
    */
   @Test
   public void testDeleteRoot()
   {
      final BinaryTree<String> tree = new BinaryTree<>();
      final String value = "root";
      tree.insert(value);

      System.out.println(tree.toString());

      assertNotNull(tree.getRoot());
      assertTrue(tree.delete(value));
      assertNull(tree.getRoot());
   }

   /**
    * test the empty tree
    */
   @Test
   public void testEmptyTree()
   {
      final BinaryTree<String> tree = new BinaryTree<>();

      System.out.println(tree.toString());

      assertNull(tree.getRoot());
      assertEquals(0, tree.size());
   }
}
