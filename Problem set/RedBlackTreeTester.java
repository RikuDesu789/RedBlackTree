public class RedBlackTreeTester {
 public static void main(String[] args) {
  RedBlackTree rbt = new RedBlackTree();
  RedBlackNode node1 = new RedBlackNode(14);
  RedBlackTree.inOrder(rbt.root);
  RedBlackTree.insert(rbt, node1);
  // rbt.delete(node2);
  // rbt.inOrder();
 }
}
