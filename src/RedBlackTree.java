package QuizGen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * http://www.codebytes.in/2014/10/red-black-tree-java-implementation.html
 */

/**
 * Generic Red-Black Tree
 * Avhusaho Ramalala
 */
public class RedBlackTree<T extends Comparable<? super T>>{
    private final int RED = 0;
    private final int BLACK = 1;
    RedBlackNode<T> root;
    private ArrayList<T> path = new ArrayList<>();

    /**
     * Red Black Node class
     */
    class RedBlackNode<T>{
        T key;
        int color = 1;
        RedBlackNode<T> left;
        RedBlackNode<T> right;
        RedBlackNode<T> parent;

        RedBlackNode(T key){
            this.key = key;
            left = right = parent = null;
        }
        
        /*
        Getters for the left and right nodes
         */

        RedBlackNode<T> getLeft() {
            return left;
        }

        RedBlackNode<T> getRight() {
            return right;
        }

    }

     /**
     * Add node to ArrayList
     * @param node: node to be added
     */
    private void visit(RedBlackNode<T> node)
    {
      /*if (node != root)
         System.out.print (", "+node.data);
      else System.out.print (node.data);*/
        path.add(node.key);
    }

    /**
     * @return ArrayList of node keys in preOrder
     */
    ArrayList<T> preOrder(){
        path.clear();
        preOrder (root);
        return path;
    }
    /**
     * Get the preOrder formation of nodes recursively
     * @param node: root node
     */
    private void preOrder(RedBlackNode<T> node){
        if (node != null){
            visit (node);
            preOrder (node.getLeft ());
            preOrder (node.getRight ());
        }
    }

    /**
     * @return ArrayList of node keys in postOrder
     */
    ArrayList<T> postOrder(){
        path.clear();
        postOrder (root);
        return path;
    }
    /**
     * Get the postOrder formation of nodes recursively
     * @param node: root node
     */
    private void postOrder(RedBlackNode<T> node){
        if (node != null){
            postOrder (node.getLeft ());
            postOrder (node.getRight ());
            visit (node);
        }
    }

    /**
     * @return ArrayList of node keys in inOrder
     */
    ArrayList<T> inOrder (){
        path.clear();
        inOrder (root);
        return path;
    }
    /**
     * Get the inOrder formation of nodes recursively
     * @param node: root node
     */
    private void inOrder(RedBlackNode<T> node){
        if (node != null){
            inOrder (node.getLeft ());
            visit (node);
            inOrder (node.getRight ());
        }
    }

    /**
     * @return ArrayList of node keys in levelOrder
     */
    ArrayList<T> levelOrder(){
        path.clear();
        levelOrder (root);
        return path;
    }
    /**
     * Get the levelOrder formation of nodes recursively
     * @param startNode: root node
     */
    private ArrayList<T> levelOrder (RedBlackNode<T> startNode)
    {
        path.clear();
        Queue<RedBlackNode<T>> queue=new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty())
        {
            RedBlackNode<T> tempNode=queue.poll();
            path.add(tempNode.key);
            if(tempNode.left!=null)
                queue.add(tempNode.left);
            if(tempNode.right!=null)
                queue.add(tempNode.right);
        }
        return path;
    }

    /**
     * @param item: search element
     * @return RedBlack Node with matching key
     */
    RedBlackNode<T> findNode(T item){
        return findNode(new RedBlackNode<>(item), root);
    }
    /**
     * Search for a node recursively using the key
     * @param findNode: RedBlackNode to be searched
     * @param node: root node
     * @return matching RedBlackNode
     */
    private RedBlackNode<T> findNode(RedBlackNode<T> findNode, RedBlackNode<T> node) {
        if (root == null) {
            return null;
        }

        if (findNode.key.compareTo(node.key) < 0) {
            if (node.left != null) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key.compareTo(node.key) > 0) {
            if (node.right != null) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }

    /**
     * @param item to be inserted
     */
    void insert(T item){
        insert(new RedBlackNode<>(item));
    }
     /**
     * insert RedBlackNode into the right position
     * @param node to be inserted
     */
    private void insert(RedBlackNode<T> node){
        RedBlackNode<T> temp = root;

        if(root == null){
            root = node;
            node.color = BLACK;
            node.parent = null;
        }
        else{
            node.color = RED;
            while(true){
                if(node.key.compareTo(temp.key) < 0){
                    if(temp.left==null){
                        temp.left = node;
                        node.parent = temp;
                        break;
                    }
                    else{
                        temp = temp.left;
                    }
                }
                else {
                    if(temp.right == null){
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

    /**
     * Rebalance the tree
     * @param node: recently inserted node
     */
    private void fixTree(RedBlackNode<T> node){
        while(node.parent.color == RED){
            RedBlackNode<T> uncle;
            if(node.parent == node.parent.parent.left){
                uncle = node.parent.parent.right;

                if(uncle != null && uncle.color == RED){
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    //node = node.parent.parent;
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
                if(uncle != null && uncle.color == RED){
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    //if (node.parent != null)
                    //    node = node.parent.parent;
                    //System.out.println(node.parent == null);/**********Testing*****/
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

    /**
     * Rotate tree to the left
     * @param node: grand parent of recently inserted node
     */
    private void rotateLeft(RedBlackNode<T> node){
        if(node.parent != null){
            if(node == node.parent.left){
                node.parent.left = node.right;
            }
            else{
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if(node.right.left != null){
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        }
        else{
            //Rotate root
            RedBlackNode<T> right = root.right;
            root.right = right.left;
            if(right.left != null)
                right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = null;
            root = right;
        }
    }

    /**
     * Rotate tree to the right
     * @param node: grand parent of recently inserted node
     */
    private void rotateRight(RedBlackNode<T> node){
        if(node.parent != null){
            if (node == node.parent.left) {
                node.parent.left = node.left;
            }
            else{
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if(node.left.right != null){
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        }
        else{
            //Rotate root
            RedBlackNode<T> left = root.left;
            root.left = root.left.right;
            if(left.right != null)
                left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = null;
            root =  left;
        }
    }

    //Deletion Code .

    //Deletes whole tree
    void deleteTree(){
        root = null;
    }

    /**
     * Switch the two nodes
     * This operation doesn't care about the new RedBlackNode's connections
     * with previous node's left and right. The caller has to take care
     * of that.
     * @param target RedBlackNode
     * @param with RedBlackNode
     */
    private void transplant(RedBlackNode<T> target, RedBlackNode<T> with) {
        try {
            if (target.parent == null) {
                root = with;
            } else if (target == target.parent.left) {
                target.parent.left = with;
            } else
                target.parent.right = with;
            with.parent = target.parent;
        }catch (NullPointerException n){
            return;
        }
    }

    /**
     * @param item to be deleted
     */
    void delete(T item){
        delete(new RedBlackNode<>(item));
    }

    /**
     * first find node then remove it.
     * after removing the node, check for any violations and fix the tree
     * @param z RedBlackNode to be deleted
     */
    private void delete(RedBlackNode<T> z){
        if((z = findNode(z, root))==null){
            return;
        }
        RedBlackNode<T> x;
        RedBlackNode<T> y = z; // temporary reference y
        int yOriginalColor = y.color;

        if(z.left == null){
            x = z.right;
            transplant(z, z.right);
        }else if(z.right == null){
            x = z.left;
            transplant(z, z.left);
        }else{
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            System.out.println(y.key);/***********Testing*************/
            if(y.parent == z )
                if(x != null)
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
        if(yOriginalColor == BLACK){
            deleteFixup(x);
        }

    }

    /**
     * Check for property violations after a node has been removed and fix the tree
     * @param x: node that took the place of the deleted node
     */
    private void deleteFixup(RedBlackNode<T> x){
        while(x!=root && x.color == BLACK){
            if(x == x.parent.left){
                RedBlackNode<T> w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                RedBlackNode<T> w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    /**
     * Get the root of the left sub tree
     * @param subTreeRoot: left child node
     * @return sub tree root is left child if left child is not null
     */
    private RedBlackNode<T> treeMinimum(RedBlackNode<T> subTreeRoot){
        while(subTreeRoot.left != null){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    /**
     * Print the tree by displaying the key, color and parent of each node
     * @param node: root node
     */
    void printTree(RedBlackNode<T> node){
        if (node == null){
            return;
        }
        printTree(node.left);
        if(node.parent == null)
            System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" ROOT\n");
        else
            System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
        printTree(node.right);
    }
    
    /**
     * Constructor
     */
    RedBlackTree(){root = null;}

    /**
     * @return Name of tree
     */
    @Override
    public String toString() {
        return "Red Black Tree";
    }
}
