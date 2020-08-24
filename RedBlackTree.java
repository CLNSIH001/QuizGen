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

    void insert(RedBlackNode node){
        RedBlackNode temp = root;

        if(root==nil){
            root = node;
            node.color = BLACK;
            node.parent = nil;
        }
        else{
            node.color = RED;
            while(true){
                if(node.key < temp.key){
                    if(temp.left==nil){
                        temp.left = node;
                        node.parent = temp;
                        break;
                    }
                    else{
                        temp = temp.left;
                    }
                }
                else {
                    if(temp.right == nil){
                        temp.right = node;
                        node.parent = temp;
                        break;
                    }
                    else{
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    private void fixTree(RedBlackNode node){
        while(node.parent.color == RED){
            RedBlackNode uncle;
            if(node.parent == node.parent.parent.left){
                uncle = node.parent.parent.right;

                if(uncle != nil && uncle.color == RED){
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if(node == node.parent.right){
                    //Double rotation needed
                    node = node.parent;
                    rotateLeft(node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //Single rotation needed if else if not executed
                rotateRight(node.parent.parent);
            }
            else{
                uncle = node.parent.parent.left;
                if(uncle != nil && uncle.color == RED){
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left){
                    //Double rotation
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                //Single rotation needed if else if not executed
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    //Deletion Code .

    //Deletes whole tree
    public void deleteTree(){
        root = nil;
    }

    //This operation doesn't care about the new RedBlackNode's connections
    //with previous node's left and right. The caller has to take care
    //of that.
    private void transplant(RedBlackNode target, RedBlackNode with){
        if(target.parent == nil){
            root = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
    }

    void delete(RedBlackNode z){
        if((z = findNode(z))==null){
            return;
        }
        RedBlackNode x;
        RedBlackNode y = z; // temporary reference y
        int yOriginalColor = y.color;

        if(z.left == nil){
            x = z.right;
            transplant(z, z.right);
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left);
        }else{
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(yOriginalColor==BLACK){
            deleteFixup(x);
        }

    }
}