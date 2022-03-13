class RedBlackTreeTester {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        RedBlackNode node1 = new RedBlackNode(30);
        RedBlackNode node2 = new RedBlackNode(5);
        RedBlackNode node3 = new RedBlackNode(10);
        RedBlackNode node4 = new RedBlackNode(25);
        RedBlackNode node5 = new RedBlackNode(9);
        RedBlackNode node6 = new RedBlackNode(50);
        RedBlackNode node7 = new RedBlackNode(37);
        rbt.insert(node1);
        rbt.insert(node2);
        rbt.insert(node3);
        rbt.insert(node4);
        rbt.delete(node3);
        rbt.insert(node5);
        rbt.insert(node6);
        rbt.insert(node7);
        rbt.delete(node1);
        
        //System.out.println(rbt.root.leftNode);
        System.out.println("root is " + rbt.root.key);
        System.out.println("===================" );
        rbt.inOrder(rbt.root);
        
        
        
        
    }
}
