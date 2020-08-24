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
    //////////////////////////////////////////
}