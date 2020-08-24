import java.io.Serializable;
import java.util.Scanner;

/**
 * Class for Red-Black Tree
 */
public class RedBlackTree{
    private final int RED = 0;
    private final int BLACK = 1;
    private final RedBlackNode nil = new RedBlackNode(Integer.MIN_VALUE);
    public RedBlackNode root = nil;

    /**
     * Red Black Node class
     */
    public class RedBlackNode implements Serializable {
        int key = -1;
        int color = 1;
        RedBlackNode left = nil;
        RedBlackNode right = nil;
        RedBlackNode parent = nil;

        RedBlackNode(int key){
            this.key = key;
        }

    }

    public RedBlackTree(int item) {

    }

    public RedBlackTree(){}

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        RedBlackTree rbt = new RedBlackTree(input);
    }
    
    RedBlackNode findNode(RedBlackNode findNode){
        if (root == nil) {
            return null;
        }

        if(findNode.key == root.key){
            return root;
        }
        else if(findNode.key < root.key){
            if(root.left != nil){
                return findNode(findNode);
            }
        }
        else {
            if(root.right != nil){
                return findNode(findNode);
            }
        }
        return null;
    }

    private void rotateLeft(RedBlackNode node){
        if(node.parent != nil){
            if(node == node.parent.left){
                node.parent.left = node.right;
            }
            else{
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if(node.right.left != nil){
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        }
        else{
            //Rotate root
            RedBlackNode right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    private void rotateRight(RedBlackNode node){
        if(node.parent != nil){
            if (node == node.parent.left) {
                node.parent.left = node.left;
            }
            else{
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if(node.left.right != nil){
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        }
        else{
            //Rotate root
            RedBlackNode left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root =  left;
        }
    }
}