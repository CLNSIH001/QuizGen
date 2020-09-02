package QuizGen;

import java.util.Vector;

/**
 * Avhusaho Ramalala
 * class for implementing min Priority Queue
 */
class MinHeap<T extends Comparable<? super T>> extends BinaryHeap<T>{

    /**
     * Constructor: use default initial capacity of vector
     */
    MinHeap(){
        heap = new Vector<>();
    }

    /**
     * constructor: set custom initial capacity for vector
     * @param capacity: initial capacity of queue
     */
    MinHeap(int capacity) {
        heap = new Vector<>(capacity);
    }

    /**
     * Recursive Heapify-down procedure. Here the node at index i
     * and its two direct children violates the heap property
     * @param i element
     */
    void heapifyDown(int i){
        // get left and right child of node at index i
        int left = leftChild(i);
        int right = rightChild(i);

        int smallest = i;

        // compare heap.get(i) with its left and right child
        // and find smallest value
        if (left < size() && heap.get(left).item.compareTo(heap.get(i).item) < 0) {
            smallest = left;
        }
        if (right < size() && heap.get(right).item.compareTo(heap.get(smallest).item) < 0) {
            smallest = right;
        }
        if (smallest != i)
        {
            // swap with child having lesser value
            swap(i, smallest);

            // call heapify-down on the child
            heapifyDown(smallest);
        }
    }

    /**
     * Recursive Heapify-up procedure
     * @param i item
     */
    void heapifyUp(int i){
        // check if node at index i and its parent violates
        // the heap property
        if (i > 0 && heap.get(parent(i)).item.compareTo(heap.get(i).item) > 0)
        {
            // swap the two if heap property is violated
            swap(i, parent(i));

            // call Heapify-up on the parent
            heapifyUp(parent(i));
        }
    }

    /**
     * function to remove and return element with highest priority
     * (present at root). It returns null if queue is empty
     * @return removed item
     * @throws Exception if null is found
     */
    BinaryHeapNode<T> delete() throws Exception {
        // if heap is empty, throw an exception
        if (size() == 0) {
            throw new Exception("Index is out of range (Heap underflow)");
        }

        // element with highest priority
        BinaryHeapNode<T> root = heap.firstElement();	// or heap.get(0);

        // replace the root of the heap with the last element of the vector
        heap.setElementAt(heap.lastElement(), 0);
        heap.remove(size()-1);

        // call heapify-down on root node
        heapifyDown(0);

        // return root element
        return root;
    }

    /**
     * insert specified key into the heap
     * @param key to be inserted
     */
    private void insert(BinaryHeapNode<T> key){
        // insert the new element to the end of the vector
        heap.addElement(key);

        // get element index and call heapify-up procedure
        int index = size() - 1;
        heapifyUp(index);
    }
}
