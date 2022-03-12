
/*
 * Academic Honesty Certification
 * Written sources used:
 * https://www.w3schools.com/java/java_operators.asp
 * (Include textbook(s), complete citations for web or other written sources.
 * Note that you are only allowed to use the web for syntax.
 * Write "none" if no sources used.)
 *
 *
 * Help obtained: (Include names of anyone other than the instructor.)
 *
 *
 * My written or typed signature below confirms that the above list
 * of sources is complete.
 * Signatures:
 */
public class RedBlackTree {
	final static int RED = 0;
	final static int BLACK = 1;
	public RedBlackNode root;
	public RedBlackNode nil = new RedBlackNode(); //leaf

	public RedBlackTree() {
		this.root = nil;
	}

	private static void leftRotate(RedBlackTree T, RedBlackNode x) {
		RedBlackNode y = x.rightNode;
		x.rightNode = y.leftNode;
		y.parentNode = x.parentNode;

		if (y.leftNode != null) {
			y.leftNode.parentNode = x;
		}

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

	private static void rightRotate(RedBlackTree T, RedBlackNode x) {
		RedBlackNode y = x.leftNode;
		x.leftNode = y.rightNode;
		y.rightNode.parentNode = x;
		y.parentNode = x.parentNode;

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

	public void insert(RedBlackNode z) {
		System.out.println("hi");
		RedBlackNode x = this.root;
		RedBlackNode y = this.nil;
			while (x != this.nil) {
				System.out.println("I'm in loop");
				y = x;
				if (z.key < x.key) {
					x = x.leftNode;
				} else {
					x = x.rightNode;
				}
				System.out.println("x: " + x + "y: " + y);
			}
			z.parentNode = y;

			if (y == this.nil) {
				this.root = z;
				System.out.println("root");
			} else if (z.key < y.key) {
				y.leftNode = z;
			} else {
				y.rightNode = z;
			}

			z.leftNode = this.nil;
			z.rightNode = this.nil;
			z.color = RED;
			//insertFix(T, z);
		// as defined in the RedBlackNode class, 0 is red 1 is black

	}

	private static void insertFix(RedBlackTree T, RedBlackNode z) {
		while (z.parentNode != null && z.parentNode.parentNode != null && z.parentNode.parentNode.leftNode != null
				&& z.parentNode.color == RED) {
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
				}
				z.parentNode.color = BLACK;
				z.parentNode.parentNode.color = RED;
				rightRotate(T, z.parentNode.parentNode);
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
				}
				if (z.parentNode != null) {
					z.parentNode.color = BLACK;
				}
				if (z.parentNode != null && z.parentNode.parentNode != null) {
					z.parentNode.parentNode.color = RED;
					leftRotate(T, z.parentNode.parentNode);
				}
			}
		}
		T.root.color = BLACK;
	}

	public static void transplant(RedBlackTree T, RedBlackNode u, RedBlackNode v) {
		if (u.parentNode == null) {
			T.root = v;
		} else if (u == u.parentNode.leftNode) {
			u.parentNode.leftNode = v;
		} else {
			u.parentNode = v;
			v.parentNode = u.parentNode;
		}
	}

	public static void delete(RedBlackTree T, RedBlackNode z) {
		RedBlackNode y = z;
		RedBlackNode x = null;
		int y_origin_color = y.color;
		if (z.leftNode == null && z.rightNode == null) {
			z = null;
		} else if (z.leftNode == null) {
			x = z.rightNode;
			transplant(T, z, z.rightNode);
		} else if (z.rightNode == null) {
			x = z.leftNode;
			transplant(T, z, z.leftNode);
		} else {
			transplant(T, y, y.rightNode);
			y.rightNode = z.rightNode;
			y.rightNode.parentNode = y;
		}
		transplant(T, z, y);
		y.leftNode = z.leftNode;
		y.leftNode.parentNode = y;
		y.color = z.color;
		if (y_origin_color == BLACK) {
			delete_fixup(T, x);
		}

	}

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
			x.color = BLACK;
		}
	}
	public void inOrder(RedBlackNode x) {
		if (x != nil){
			inOrder(x.leftNode);
			System.out.println(x.key);
			inOrder(x.rightNode);
		}
			
	}
	// public void inOrder(RedBlackNode n) {
	// 	if (n == nil)
	// 		return;
	// 	inOrder(n.leftNode);
	// 	String color;
	// 	if (n.color == RED) {
	// 		color = "RED";
	// 	} else {
	// 		color = "BLACK";
	// 	}
	// 	System.out.print(n.key + ":" + color + " ");
	// 	inOrder(n.rightNode);
	// }
}