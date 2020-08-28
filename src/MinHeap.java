package QuizGen;

import java.util.Arrays;
import java.util.Vector;

// class for implementing Priority Queue
class MinHeap{
    // vector to store heap elements
    private Vector<Integer> heap;

    // constructor: use default initial capacity of vector
    MinHeap(){
        heap = new Vector<>();
    }

    // constructor: set custom initial capacity for vector
    MinHeap(int capacity) {
        heap = new Vector<>(capacity);
    }

    // return parent of heap.get(index)
    private int parent(int index){
        // if index is already a root node
        if (index == 0)
            return 0;

        return (index - 1) / 2;
    }

    // return left child of heap.get(index)
    private int leftChild(int index){
        return (2 * index + 1);
    }

    // return right child of heap.get(index)
    private int rightChild(int index){
        return (2 * index + 2);
    }

    // swap values at two indexes
    private void swap(int x, int y){
        // swap with child having greater value
        Integer temp = heap.get(x);
        heap.setElementAt(heap.get(y), x);
        heap.setElementAt(temp, y);
    }

    // Recursive Heapify-down procedure. Here the node at index i
    // and its two direct children violates the heap property
    private void heapifyDown(int i){
        // get left and right child of node at index i
        int left = leftChild(i);
        int right = rightChild(i);

        int smallest = i;

        // compare heap.get(i) with its left and right child
        // and find smallest value
        if (left < size() && heap.get(left) < heap.get(i)) {
            smallest = left;
        }
        if (right < size() && heap.get(right) < heap.get(smallest)) {
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

    // Recursive Heapify-up procedure
    private void heapifyUp(int i){
        // check if node at index i and its parent violates
        // the heap property
        if (i > 0 && heap.get(parent(i)) > heap.get(i))
        {
            // swap the two if heap property is violated
            swap(i, parent(i));

            // call Heapify-up on the parent
            heapifyUp(parent(i));
        }
    }

    // return size of the heap
    int size() { return heap.size(); }

    // check if heap is empty or not
    Boolean isEmpty() { return heap.isEmpty(); }

    // insert specified key into the heap
    void add(Integer key){
        // insert the new element to the end of the vector
        heap.addElement(key);

        // get element index and call heapify-up procedure
        int index = size() - 1;
        heapifyUp(index);
    }

    // function to remove and return element with highest priority
    // (present at root). It returns null if queue is empty
    Integer poll() throws Exception {
        // if heap is empty, throw an exception
        if (size() == 0) {
            throw new Exception("Index is out of range (Heap underflow)");
        }

        // element with highest priority
        int root = heap.firstElement();	// or heap.get(0);

        // replace the root of the heap with the last element of the vector
        heap.setElementAt(heap.lastElement(), 0);
        heap.remove(size()-1);

        // call heapify-down on root node
        heapifyDown(0);

        // return root element
        return root;
    }

    // function to return, but does not remove, element with highest priority
    // (present at root). It returns null if queue is empty
    Integer peek() throws Exception {
        // if heap has no elements, throw an exception
        if (size() == 0) {
            throw new Exception("Index out of range (Heap underflow)");
        }

        // else return the top (first) element
        return heap.firstElement();	// or heap.get(0);
    }

    // function to remove all elements from priority queue
    void clear() throws Exception {
        System.out.print("Emptying queue: ");
        while (!heap.isEmpty()) {
            System.out.print(poll() + " ");
        }
        System.out.println();
    }

    // returns true if queue contains the specified element
    Boolean contains(Integer i){
        return heap.contains(i);
    }

    // returns an array containing all elements in the queue
    Integer[] toArray() {
        return heap.toArray(new Integer[size()]);
    }
}

/////////////////////////////////////////////////
class MinHeapDriver
{
    // Program for Max Heap Implementation in Java
    public static void main (String[] args) throws Exception
    {
        // create a Priority Queue of initial capacity 10
        // Priority of an element is decided by element's value
        MinHeap pq = new MinHeap(10);

        // insert three integers
        pq.add(3);
        pq.add(2);
        pq.add(15);

        // print Priority Queue size
        System.out.println("Priority Queue Size is " + pq.size());

        // search 2 in Priority Queue
        Integer searchKey = 2;

        if (pq.contains(searchKey)) {
            System.out.println("Priority Queue contains " + searchKey + "\n");
        }

        // empty queue
        pq.clear();

        if (pq.isEmpty()) {
            System.out.println("Queue is Empty");
        }

        System.out.println("\nCalling remove operation on an empty heap");
        System.out.println("Element with highest priority is " + pq.poll() + '\n');

        System.out.println("Calling peek operation on an empty heap");
        System.out.println("Element with highest priority is " + pq.peek() + '\n');

        // again insert three integers
        pq.add(5);
        pq.add(4);
        pq.add(45);

        // construct array containing all elements present in the queue
        Integer[] I = pq.toArray();
        System.out.println("Printing array: " + Arrays.toString(I));

        System.out.println("\nElement with highest priority is " + pq.poll());
        System.out.println("Element with highest priority is " + pq.peek());
    }
}
