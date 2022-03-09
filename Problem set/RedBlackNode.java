public class RedBlackNode {

    public int key;
    public int color;
    // red is 0, black is 1
    public RedBlackNode parentNode;
    public RedBlackNode leftNode;
    public RedBlackNode rightNode;

    public RedBlackNode(int key) {
        this(key, 1, null, null, null);
    }

    /*
     * public RedBlackNode(RedBlackNode p) {
     * this.parentNode = p;
     * this.color = 1;
     * this.leftNode = null;
     * this.rightNode = null;
     * }
     */

    public RedBlackNode(int key, int color, RedBlackNode parentNode, RedBlackNode leftNode, RedBlackNode rightNode) {
        this.key = key;
        this.color = color;
        this.parentNode = parentNode;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
