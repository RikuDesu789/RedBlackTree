class RedBlackTreeTester {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        RedBlackNode node1 = new RedBlackNode(12);
        RedBlackNode node2 = new RedBlackNode(5);
        RedBlackNode node3 = new RedBlackNode(23);
        rbt.insert(node1);
        rbt.insert(node2);
        rbt.insert(node3);
        //System.out.println(rbt.root.leftNode);
        rbt.inOrder(rbt.root);
        // RedBlackNode node4 = new RedBlackNode(25);
        // RedBlackNode node5 = new RedBlackNode(24);
        //RedBlackNode node4 = new RedBlackNode(234);
        //RedBlackNode node5 = new RedBlackNode(3); 
        // RedBlackTree.insert(rbt, node1);
        // RedBlackTree.insert(rbt, node2);
        // RedBlackTree.delete(rbt, node1);
        // RedBlackTree.insert(rbt, node3);
        // RedBlackTree.insert(rbt, node4);
        // //RedBlackTree.delete(rbt, node3);
        // RedBlackTree.insert(rbt, node5);
        // // RedBlackTree.delete(rbt, node5);
        // RedBlackTree.inOrder(rbt.root);
    }
}
