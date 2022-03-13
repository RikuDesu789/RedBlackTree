/*
 * Academic Honesty Certification
 * Written sources used:
 * https://www.w3schools.com/java/java_operators.asp for syntax.
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
public class RedBlackTree {
	final static int RED = 0;
	final static int BLACK = 1;
	public RedBlackNode root = new RedBlackNode();
	public RedBlackNode nil = new RedBlackNode(); //leaf

	public RedBlackTree() {
		this.root = nil;
	}

	
	/** 
	 * @param T
	 * @param x
	 */
	private static void leftRotate(RedBlackTree T, RedBlackNode x) {
		RedBlackNode y = x.rightNode;
		x.rightNode = y.leftNode;
		

		if (y.leftNode != T.nil) {
			y.leftNode.parentNode = x;
		}
		y.parentNode = x.parentNode;
		if (x.parentNode == T.nil) {
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
	 * @param T
	 * @param x
	 */
	private static void rightRotate(RedBlackTree T, RedBlackNode x) {
		RedBlackNode y = x.leftNode;
		x.leftNode = y.rightNode;

		if (y.rightNode != T.nil) {
			y.rightNode.parentNode = x;
		}
		y.parentNode = x.parentNode;
		if (x.parentNode == T.nil) {
			T.root = y;
		} else if (x == x.parentNode.rightNode) {
			x.parentNode.rightNode = y;
		} else {
			x.parentNode.leftNode = y;
		}
		y.rightNode = x;
		x.parentNode = y;
	}

	
	/** 
	 * @param z
	 */
	public void insert(RedBlackNode z) {
		RedBlackNode x = this.root;
		//System.out.println("before insert root is " + x.key);
		RedBlackNode y = this.nil;
			while (x != this.nil) {
				y = x;
				if (z.key < x.key) {
					x = x.leftNode;
				} else {
					x = x.rightNode;
				}
			}
			z.parentNode = y;
			//System.out.println("z is " + z.key + " parent is: " + z.parentNode.key);

			if (y == this.nil) {
				this.root = z;
				//System.out.println("Tree is empty, so root is " + z.key);
			} else if (z.key < y.key) {
				y.leftNode = z;
				//System.out.println("z is small: " + z.key + " < " + y.key);
			} else {
				y.rightNode = z;
				//System.out.println("z is big: " + z.key + " > " + y.key);
			}

			z.leftNode = this.nil;
			z.rightNode = this.nil;
			z.color = RED;
			insertFix(this, z);
		// as defined in the RedBlackNode class, 0 is red 1 is black

	}
	
	/** 
	 * @param T
	 * @param z
	 */
	private static void insertFix(RedBlackTree T, RedBlackNode z) {
		while (z.parentNode.color == RED) {
			if (z.parentNode == z.parentNode.parentNode.leftNode) {
				RedBlackNode y = z.parentNode.parentNode.rightNode;
				if (y.color == RED) {
					z.parentNode.color = BLACK;
					y.color = BLACK;
					z.parentNode.parentNode.color = RED;
					z = z.parentNode.parentNode;
				} else if (z == z.parentNode.rightNode) {
					z = z.parentNode;
					leftRotate(T, z);
					z.parentNode.color = BLACK;
					z.parentNode.parentNode.color = RED;
					rightRotate(T, z.parentNode.parentNode);
				}
				
			} else {
				RedBlackNode y = z.parentNode.parentNode.leftNode;
				if (y.color == RED) {
					z.parentNode.color = BLACK;
					y.color = BLACK;
					z.parentNode.parentNode.color = RED;
					z = z.parentNode.parentNode;
				} else if (z == z.parentNode.leftNode) {
					z = z.parentNode;
					rightRotate(T, z);
					if (z.parentNode != T.nil) {
						z.parentNode.color = BLACK;
					}
					if (z.parentNode != T.nil && z.parentNode.parentNode != null) {
						z.parentNode.parentNode.color = RED;
						leftRotate(T, z.parentNode.parentNode);
					}
				}
				
			}
		}
		T.root.color = BLACK;
	}
	/**
	 * 
	 */
	public static RedBlackNode tree_minimum(RedBlackTree T, RedBlackNode x){
		while(x.leftNode != T.nil){
			x = x.leftNode;
		}
		return x;
	}
	
	/** 
	 * @param T
	 * @param u
	 * @param v
	 */
	public static void transplant(RedBlackTree T, RedBlackNode u, RedBlackNode v) {
		if (u.parentNode == T.nil) {
			T.root = v;
		} else if (u == u.parentNode.leftNode) {
			u.parentNode.leftNode = v;
		} else {
			u.parentNode = v;
			v.parentNode = u.parentNode;
		}
	}

	
	/** 
	 * @param z
	 */
	public void delete(RedBlackNode z) {
		RedBlackNode y = z;
		RedBlackNode x = this.nil;
		int y_origin_color = y.color;
		if (z.leftNode == this.nil) {
			x = z.rightNode;
		} else if (z.leftNode == this.nil) {
			x = z.rightNode;
			transplant(this, z, z.rightNode);
		} else if (z.rightNode == this.nil) {
			x = z.leftNode;
			transplant(this, z, z.leftNode);
		} else {
			y = tree_minimum(this, z.rightNode);
			y_origin_color = y.color;
			x = y.rightNode;
			if(y.parentNode == z){
				x.parentNode = y;
			}
			else{
				transplant(this, y, y.rightNode);
				y.rightNode = z.rightNode;
				y.rightNode.parentNode = y;
			}
			transplant(this, z, y);
			y.leftNode = z.leftNode;
			y.leftNode.parentNode = y;
			y.color = z.color;
			
		}
		
		if (y_origin_color == BLACK) {
			delete_fixup(this, x);
		}

	}

	
	/** 
	 * @param T
	 * @param x
	 */
	public static void delete_fixup(RedBlackTree T, RedBlackNode x) {
		RedBlackNode w = null;
		while (x != T.root && x.color == BLACK) {
			if (x == x.parentNode.leftNode) {
				w = x.parentNode.rightNode;
				if (w.color == RED) {
					w.color = BLACK;
					x.parentNode.color = RED;
					leftRotate(T, x.parentNode);
					w = x.parentNode.rightNode;
				}
				if (w.leftNode.color == BLACK && w.rightNode.color == BLACK) {
					w.color = RED;
					x = x.parentNode;
				} else if (w.rightNode.color == BLACK) {
					w.leftNode.color = BLACK;
					w.color = RED;
					rightRotate(T, w);
					w = x.parentNode.rightNode;
				}
				w.color = x.parentNode.color;
				x.parentNode.color = BLACK;
				w.rightNode.color = BLACK;
				leftRotate(T, x.parentNode);
				x = T.root;

			} else {
				w = x.parentNode.leftNode;
				if (w.color == RED) {
					w.color = BLACK;
					x.parentNode.color = RED;
					rightRotate(T, x.parentNode);
					w = x.parentNode.rightNode;
				}
				if (w.rightNode.color == BLACK && w.leftNode.color == BLACK) {
					w.color = RED;
					x = x.parentNode;
				} else if (w.leftNode.color == BLACK) {
					w.rightNode.color = BLACK;
					w.color = RED;
					leftRotate(T, w);
					w = x.parentNode.leftNode;
				}
				w.color = x.parentNode.color;
				x.parentNode.color = BLACK;
				w.leftNode.color = BLACK;
				rightRotate(T, x.parentNode);
				x = T.root;
			}
			
		}
		x.color = BLACK;
	}
	
	/** 
	 * @param x
	 */
	public void inOrder(RedBlackNode x) {
		if (x != nil){
			inOrder(x.leftNode);
			if(x.color == 0){
				System.out.println("node " + x.key + " color: RED");
			}
			else{
				System.out.println("node " + x.key + " color: BLACK");
			}
			inOrder(x.rightNode);
		}

			
	}
	
}
