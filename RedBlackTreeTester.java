class RedBlackTreeTester {
  public static void main(String[] args) {
    RedBlackTree rbt = new RedBlackTree(8);
    RedBlackNode node1 = new RedBlackNode(18);
    RedBlackNode node2 = new RedBlackNode(5);
    RedBlackNode node3 = new RedBlackNode(15);
    RedBlackNode node4 = new RedBlackNode(17);
    RedBlackNode node5 = new RedBlackNode(25);
    RedBlackNode node6 = new RedBlackNode(40);
    RedBlackNode node7 = new RedBlackNode(80);

    // This inputs covers all the cases for insertion
    System.out.println();
    RedBlackTree.insert(rbt, node1);
    RedBlackTree.insert(rbt, node2);
    RedBlackTree.insert(rbt, node3);
    RedBlackTree.insert(rbt, node4);
    RedBlackTree.insert(rbt, node5);
    RedBlackTree.insert(rbt, node6);
    RedBlackTree.inOrder(rbt.root);
    System.out.println();
    System.out.println();

  }
}
