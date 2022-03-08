/*
 * Academic Honesty Certification
 * Written sources used:
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
   private RedBlackNode nil;

   public RedBlackTree() {
      this.root = nil;
   }

   private static void leftRotate(RedBlackTree T, RedBlackNode x) {
      RedBlackNode y = x.rightNode;
      x.rightNode = y.leftNode;
      y.parentNode = x.parentNode;

      if (y.leftNode != T.nil) {
         y.leftNode.parentNode = x;
      }

      if (y.parentNode == T.nil) {
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

      if (x.parentNode == T.nil) {
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

   public static void insert(RedBlackTree T, RedBlackNode z) {
      RedBlackNode x = T.root;
      RedBlackNode y = T.nil;

      while (x != T.nil) {
         y = x;
         if (z.key < x.key) {
            x = x.leftNode;
         } else {
            x = x.rightNode;
         }
         z.parentNode = y;

         if (y == T.nil) {
            T.root = z;
         } else if (z.key < y.key) {
            y.leftNode = z;
         } else {
            y.rightNode = z;
         }

         z.leftNode = T.nil;
         z.right = T.nil;
         z.color = RED; // do we need this line????
         insertFix(T, z);
         // as defined in the RedBlackNode class, 0 is red 1 is black
      }
   }

   private static void insertFix(RedBlackTree T, RedBlackNode z) {
      while (z.parentNode.color == RED) {
         if (z.parentNode == z.parentNode.parentNode.leftNode) {
            TreeNode y = z.parentNode.parentNode.rightNode;
            if (y.color == RED) {
               z.parentNode.color = BLACK;
               y.color = BLACK
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
            TreeNode y = z.parentNode.parentNode.leftNode;
            if (y.color == RED) {
               z.parentNode.color = BLACK;
               y.color = BLACK;
               z.parentNode.parentNode.color = RED;
               z = z.parentNode.parentNode;
            } else if (z == z.parentNode.leftNode) {
               z = z.parentNode;
               rightRotate(T, z);
            }
            z.parentNode.color = BLACK;
            z.parentNode.parentNode.color = RED;
            leftRotate(T, z.parentNode.parentNode);
         }
      }
      T.root.color = BLACK;
   }

   public void transplant(RedBlackNode u, RedBlackNode v, RedBlackTree T) {
      if (u.parentNode == T.nil) {
         T.root = v;
      } else if (u == u.parentNode.leftNode) {
         u.parentNode.leftNode = v;
      } else {
         u.parentNode = v;
         v.parentNode = u.parentNode;
      }
   }

   // public void delete(RedBlackNode z, RedBlackTree T) {
   // RedBlackTree y = z;
   // integer y_origin_color = y.color;
   // if (z.leftNode == T.nil) {
   // x = z.rightNode;
   // transplant(T, z, z.rightNode);
   // }else if{

   // }

   // }
   // public void delete_fixup(RedBlackNode z, RedBlackTree T){

   // }
   public static void inOrder(RedBlackNode n) {
      if (n == null)
         return;
      inorder(n.leftNode);
      System.out.print(n.key);
      inorder(n.lrightNode);
   }
}
