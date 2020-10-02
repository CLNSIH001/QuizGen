package DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class BinaryHeap<T extends Comparable<? super T>> extends BinaryTree<T>{

    Vector<BinaryTreeNode<T>> heap; // vector to store heap elements
    private ArrayList<T> path = new ArrayList<>();

    /*class BinaryTreeNode<T>{
        T item;

        BinaryTreeNode(T item){
            this.item = item;
        }

        @Override
        public String toString(){
            return item.toString();
        }

    }*/

    /**
     * @param i item position
     * @return parent of item at specified index
     */
    int parent(int i){
        // if i is already a root node
        if (i == 0)
            return 0;

        return (i - 1) / 2;
    }

    /**
     * @param index element position
     * @return left child of item at index
     */
    int leftChild(int index){
        int leftIndex = 2 * index + 1;
        heap.get(index).left = heap.get(leftIndex);
        return leftIndex;
    }

    /**
     * @param index element position
     * @return right child of item at index
     */
    int rightChild(int index){
        int rightIndex = 2 * index + 1;
        heap.get(index).right = heap.get(rightIndex);
        return rightIndex;
    }

    /**
     * swap values at two indexes
     * @param x index
     * @param y index
     */
    void swap(int x, int y){
        // swap with child having greater value
        BinaryTreeNode<T> temp = heap.get(x);
        heap.setElementAt(heap.get(y), x);
        heap.setElementAt(temp, y);
    }

    /**
     * @return size of the heap
     */
    int size() { return heap.size(); }

    /**
     * check if heap is empty or not
     * @return true if empty else false
     */
    Boolean isEmpty() { return heap.isEmpty(); }

    abstract void heapifyDown(int i);

    abstract void heapifyUp(int i);

    public void insert(T item){
        insert(new BinaryTreeNode<>(item,null,null));
    }
    /**
     * insert specified key into the heap
     * @param key to be inserted
     */
    private void insert(BinaryTreeNode<T> key){
        // insert the new element to the end of the vector
        heap.addElement(key);

        // get element index and call heapify-up procedure
        int index = size() - 1;
        heapifyUp(index);
    }

    /**
     * function to remove and return element with highest priority
     * (present at root). It returns null if queue is empty
     * @return removed item
     * @throws Exception if null is found
     */
    public BinaryTreeNode<T> delete() throws Exception {
        // if heap is empty, throw an exception
        if (size() == 0) {
            throw new Exception("Index is out of range (Heap underflow)");
        }

        // element with highest priority
        BinaryTreeNode<T> root = heap.firstElement();	// or heap.get(0);

        // replace the root of the heap with the last element of the vector
        heap.setElementAt(heap.lastElement(), 0);
        heap.remove(size()-1);

        // call heapify-down on root node
        heapifyDown(0);

        // return root element
        return root;
    }

    /**
     * function to return, but does not remove, element with highest priority
     * (present at root). It returns null if queue is empty
     * @return item
     * @throws Exception if null is found
     */
    BinaryTreeNode<T> peek() throws Exception {
        // if heap has no elements, throw an exception
        if (size() == 0) {
            throw new Exception("Index out of range (Heap underflow)");
        }

        // else return the top (first) element
        return heap.firstElement();	// or heap.get(0);
    }

    /**
     * function to remove all elements from priority queue
     * @throws Exception if found null
     */
    public void clear()throws Exception {
        System.out.print("Emptying queue: ");
        while (!heap.isEmpty()) {
            System.out.print(delete() + " ");
        }
        System.out.println();
    }

    Boolean contains(T item){
        return contains(new BinaryTreeNode<>(item,null,null));
    }
    /**
     * @param i: item being searched
     * @return true if queue contains the specified element
     */
    private Boolean contains(BinaryTreeNode<T> i) {
        return heap.contains(i);
    }

    /**
     * Add node to ArrayList
     * @param node: node to be added
     */
    /*private void visit(BinaryTreeNode<T> node) {
        path.add(node.item);
    }*/

    /**
     * @return ArrayList of node keys in preOrder
     */
    /*ArrayList<T> preOrder() {
        path.clear();
        try{
            preOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap pre order\n"+e);
        }
        return path;
    }
    /**
     * Get the preOrder formation of nodes recursively
     * @param node: root node
     */
    /*private void preOrder(BinaryTreeNode<T> node)
    {
        if (node != null)
        {
            visit (node);
            preOrder (heap.get(leftChild(heap.indexOf(node))));
            preOrder (heap.get(rightChild(heap.indexOf(node))));
        }
    }

    /**
     * @return ArrayList of node keys in postOrder
     */
    /*ArrayList<T> postOrder()
    {
        path.clear();
        try{
            postOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap post order\n"+e);
        }
        return path;
    }
    /**
     * Get the postOrder formation of nodes recursively
     * @param node: root node
     */
    /*private void postOrder(BinaryTreeNode<T> node)
    {
        if (node != null)
        {
            postOrder (heap.get(leftChild(heap.indexOf(node))));
            postOrder (heap.get(rightChild(heap.indexOf(node))));
            visit (node);
        }
    }

    /**
     * @return ArrayList of node keys in inOrder
     */
    /*ArrayList<T> inOrder ()
    {
        path.clear();
        try{
            inOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap pre order\n"+e);
        }
        return path;
    }
    /**
     * Get the inOrder formation of nodes recursively
     * @param node: root node
     */
    /*private void inOrder(BinaryTreeNode<T> node)
    {
        if (node != null)
        {
            inOrder (heap.get(leftChild(heap.indexOf(node))));
            visit (node);
            inOrder (heap.get(rightChild(heap.indexOf(node))));
        }
    }

    /**
     * @return ArrayList of node keys in levelOrder
     */
    /*ArrayList<T> levelOrder(){
        path.clear();
        try{
            levelOrder (peek());
        }catch(Exception e){
            System.out.println("Error in Binary Heap pre order\n"+e);
        }
        return path;
    }
    /**
     * Get the levelOrder formation of nodes recursively
     * @param startNode: root node
     */
    /*private ArrayList<T> levelOrder (BinaryTreeNode<T> startNode)
    {
        path.clear();
        Queue<BinaryTreeNode<T>> queue=new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty())
        {
            BinaryTreeNode<T> tempNode=queue.poll();
            path.add(tempNode.item);
            if(heap.get(leftChild(heap.indexOf(tempNode)))!=null)
                queue.add(heap.get(leftChild(heap.indexOf(tempNode))));
            if(heap.get(rightChild(heap.indexOf(tempNode)))!=null)
                queue.add(heap.get(rightChild(heap.indexOf(tempNode))));
        }
        return path;
    }*/

}
