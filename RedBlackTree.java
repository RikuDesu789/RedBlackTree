/*
 * Academic Honesty Certification
 * Written sources used: none
 * (Include textbook(s), complete citations for web or other written sources.
 * Note that you are only allowed to use the web for syntax.
 * Write "none" if no sources used.)
 *
 *
 * Help obtained: Haruko Okada, Sergio Martelo
 *
 *
 * My written or typed signature below confirms that the above list
 * of sources is complete.
 * Signatures: Muqi Guo, Riku Smriga
 */

/**
 * a class that represent a Red Black Tree
 * @author rikusmriga
 *
 */
public class RedBlackTree {
	final static int RED = 0;
	final static int BLACK = 1;
	public RedBlackNode root;
//	private static RedBlackNode Tnil = new RedBlackNode(0);
	
/**
 * a constructor for the class that takes the key of the root node as input
 * @param key
 */
	public RedBlackTree(int key){
		RedBlackNode node = new RedBlackNode(key);
		this.root = node;
		root.color = BLACK;
	}

	/**
	 * a method that performs the left rotation 
	 * @param T the RedBlack Tree which the rotation needs to be performed 
	 * @param x the base node in which the rotation will be performed
	 */
	private static void leftRotate(RedBlackTree T, RedBlackNode x) {
//		System.out.println("left Rotate");
		RedBlackNode y = x.rightNode;
		x.rightNode = y.leftNode;


		if (y.leftNode != null) {
			y.leftNode.parentNode = x;
		}
		
		y.parentNode = x.parentNode;

		if (y.parentNode == null) {
			T.root = y;
		} else if (x == x.parentNode.leftNode) {
			x.parentNode.leftNode = y;
		} else {
			x.parentNode.rightNode = y;
		}
		y.leftNode = x;
		x.parentNode = y;
	}

	/**
	 * a method that performs the right rotation 
	 * @param T the RedBlack Tree which the rotation needs to be performed 
	 * @param x the base node in which the rotation will be performed
	 */
	private static void rightRotate(RedBlackTree T, RedBlackNode x) {
//		System.out.println("right Rotate");
		RedBlackNode y = x.leftNode;
		x.leftNode = y.rightNode;

		if (x.parentNode == null) {
			T.root = y;
		}

		else if (x.parentNode.leftNode == x) {
			x.parentNode.leftNode = y;
		} else {
			x.parentNode.rightNode = y;
		}
		y.rightNode = x;
		x.parentNode = y;
	}

	/**
	 * a method that inserts the node z into the the tree T
	 * @param T the Tree that the node z will be inserted
	 * @param z the node which will be inserted in the Tree 
	 */
	public static void insert(RedBlackTree T, RedBlackNode z) {
		
		
		RedBlackNode y = null;
		RedBlackNode x = T.root;
		
		while (x != null){
			y = x;
			if (z.key < x.key) {
				x = x.leftNode;
			} else {
				x = x.rightNode;
			}
		}
		z.parentNode = y;

		if (y == null) {
			T.root = z;
		} else if (z.key < y.key) {
			y.leftNode = z;
		} else {
			y.rightNode = z;
		}
 
		insertFix(T,z);	
	}

	private static void insertFix(RedBlackTree T, RedBlackNode z) {
		
		while (T.root != z && z.parentNode.color == RED){
			if (z.parentNode == z.parentNode.parentNode.leftNode) {
				RedBlackNode y = z.parentNode.parentNode.rightNode;
				//check for case 1
				if (y != null && y.color == RED){
					System.out.println("case11");
					z.parentNode.color = BLACK;
					y.color = BLACK;
					z.parentNode.parentNode.color = RED;
					z = z.parentNode.parentNode;
				}
				else {
					//check for case 2
					System.out.println("case21");
					if (z == z.parentNode.rightNode) {
						z = z.parentNode;
						leftRotate(T, z);
					}
					//check for case 3
					System.out.println("case31");
					z.parentNode.color = BLACK;
					z.parentNode.parentNode.color = RED;
					
					rightRotate(T, z.parentNode.parentNode);
				}
			}
			else {
				RedBlackNode y = z.parentNode.parentNode.leftNode;
				if (y!= null && y.color == RED) {
					//check for case 1
					System.out.println("case12");
					z.parentNode.color = BLACK;
					y.color = BLACK;
					z.parentNode.parentNode.color = RED;
					z = z.parentNode.parentNode;
				} else {
					//check for case 2
					if (z == z.parentNode.leftNode) {
						System.out.println("case22");
						z = z.parentNode;
						rightRotate(T, z);
					}
					//check for case 3
					System.out.println("case32");
					z.parentNode.color = BLACK;
					z.parentNode.parentNode.color = RED;
					System.out.println(z.parentNode.parentNode.key);
					leftRotate(T, z.parentNode.parentNode);
				}
			}
			T.root.color = BLACK;
		}
	}

	public void transplant(RedBlackNode u, RedBlackNode v, RedBlackTree T) {
		if (u.parentNode == null) {
			T.root = v;
		} else if (u == u.parentNode.leftNode) {
			u.parentNode.leftNode = v;
		} else {
			u.parentNode = v;
			v.parentNode = u.parentNode;
		}
	}

	public static void inOrder(RedBlackNode n) {
		if (n == null)
			return;
		inOrder(n.leftNode);
		String color;
		if (n.color == RED) {
			color = "RED";
		} else {
			color = "BLACK";
		}
		System.out.print(n.key + ":" + color + " ");
		inOrder(n.rightNode);
	}	
}
