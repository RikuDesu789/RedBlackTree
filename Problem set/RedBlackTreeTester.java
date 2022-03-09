class RedBlackTreeTester {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        RedBlackNode node1 = new RedBlackNode(14);
        RedBlackNode node2 = new RedBlackNode(1);
        RedBlackNode node3 = new RedBlackNode(23);
        RedBlackNode node4 = new RedBlackNode(25);
        RedBlackNode node5 = new RedBlackNode(24);
        //RedBlackNode node4 = new RedBlackNode(234);
        //RedBlackNode node5 = new RedBlackNode(3); 
        RedBlackTree.insert(rbt, node1);
        RedBlackTree.insert(rbt, node2);
        //RedBlackTree.delete(rbt, node1);
        RedBlackTree.insert(rbt, node3);
        RedBlackTree.insert(rbt, node4);
        //RedBlackTree.delete(rbt, node3);
        RedBlackTree.insert(rbt, node5);
        // RedBlackTree.delete(rbt, node5);
        RedBlackTree.inOrder(rbt.root);
    }
}
