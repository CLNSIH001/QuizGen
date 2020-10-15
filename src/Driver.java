import DataStructures.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors

/**
 * Author: All team members
 * Driver creates UI and also accepts input from user before creating the pool text file.
 * @param <T> class is generic to allow for insertion of numbers, strings or characters into data structures
 */

public class Driver<T extends Comparable<? super T>>{

    private char[] charLib = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
            'p','q','r','s','t','u','v','w','x','y','z'};
    private String[] stringLib = {"apple","banana","care","down","edible","fly","garage","hello","ink","jump","kick","lie",
            "make","no","over","principle","quit","rest","sit","tumble","up","veer","wait","xenophobia","you","zit"};
    private ArrayList<T> allInserts;
    private ArrayList<T> travel;  //for traversals
    private T type;
    private BinarySearchTree<T> bst;
    private AVLTree<T> avl;
    private RedBlackTree<T> rbt;
    private BinaryHeap<T> bheap;
    private BPTrees bpt;
    private Graph digraph;
    private static int heapType = 0;
    private static final String[] resolutionSchemes = {"Linear Probing", "Quadratic Probing", "Chaining resolution scheme"};

    /**
     * Constructor
     * @param init: type of items to be inserted (i.e. Integers, Characters or String)
     */
    @SuppressWarnings("unchecked")
    private Driver(T init){
        allInserts = new ArrayList<>();
        type = init;
        travel = new ArrayList<>();
        //Data Structures
        bst = new BinarySearchTree<>();
        avl = new AVLTree<>();
        rbt = new RedBlackTree();
        digraph = new Graph();
    }

    private void poolType(int format, int type, int poolSize, String filename){
        if (filename.substring(0, filename.indexOf("/")).equals("BinarySearchTree")){
            if (format == 1)
                treeMCQ(poolSize, bst, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, bst, filename, type);
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("AVLTree")){
            if (format == 1)
                treeMCQ(poolSize, avl, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, avl, filename, type);
            /*if (format == 3)
                fillIn(poolSize, avl, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("RedBlackTree")){
            if (format == 1)
                treeMCQ(poolSize, rbt, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, rbt, filename, type);
            /*if (format == 3)
                fillIn(poolSize, rbt, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("BinaryHeap")){
            switch(heapType){
                case 0:
                    bheap = new MinHeap<>();
                    break;
                case 1:
                    bheap = new MaxHeap<>();
                    break;
                default:
                    System.out.println("Invalid heap type: " + heapType);

            }
            if (format == 1)
                treeMCQ(poolSize, bheap, filename, type);
            if (format == 2)
                treeTrueFalse(poolSize, bheap, filename, type);
            //if (format == 3);
        }
    }

    /**
     * Author: Group 2
     * Get the inOrder reading of the nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printAllNodes(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.inOrder();

        else if(object instanceof AVLTree)
            travel = avl.inOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.inOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.inOrder();
        }
    }

    /**
     * Author: Group 2
     * Get preOrder formation of the nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printPreOrder(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.preOrder();

        else if(object instanceof AVLTree)
            travel = avl.preOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.preOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.preOrder();
        }
    }

    /**
     * Author: Group 2
     * get post order formation of nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printPostOrder(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.postOrder();

        else if(object instanceof AVLTree)
            travel = avl.postOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.postOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.postOrder();
        }
    }

    /**
     * Author: Group 2
     * Get level ordering of nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printLevelOrder(Object object){
        if (object instanceof BinarySearchTree)
            travel = bst.levelOrder();

        else if(object instanceof AVLTree)
            travel = avl.levelOrder();

        else if(object instanceof RedBlackTree)
            travel = rbt.levelOrder();

        else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap)
                bheap = (MinHeap<T>) object;
            else if(object instanceof MaxHeap)
                bheap = (MaxHeap<T>) object;
            travel = bheap.levelOrder();
        }
    }

    @SuppressWarnings("unchecked")
    private int height(Object obj){
        if (obj instanceof BinarySearchTree) {
            bst = (BinarySearchTree<T>) obj;
            return bst.getHeight();
        }
        else if(obj instanceof AVLTree){
            avl = (AVLTree<T>) obj;
            return avl.getHeight();
        }
        else if(obj instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) obj;
            return rbt.getHeight();
        }
        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            else if(obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            return bheap.getHeight();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    private int size(Object obj){
        if (obj instanceof BinarySearchTree) {
            bst = (BinarySearchTree<T>) obj;
            return bst.getSize();
        }
        else if(obj instanceof AVLTree){
            avl = (AVLTree<T>) obj;
            return avl.getSize();
        }
        else if(obj instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) obj;
            return rbt.getSize();
        }
        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            else if(obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            return bheap.getSize();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<T> treeLeaves(Object obj){
        if (obj instanceof BinarySearchTree)
            return bst.getLeaves();

        else if(obj instanceof AVLTree)
            return avl.getLeaves();

        else if(obj instanceof RedBlackTree)
            return rbt.getLeaves();

        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            else if(obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            return bheap.getLeaves();
        }
        return null;
    }

    /**
     * Author: Group 2
     * insert all items in the array list into the passed in data structure.
     * @param list: Array List of items of any type.
     * @param obj: Passed in data structure.
     * @return data structure with items inserted.
     */
    @SuppressWarnings("unchecked")
    private Object insert(ArrayList<T> list, Object obj){
        if(obj instanceof BinarySearchTree){
            for(T i: list){
                bst.insert(i);
            }
        }
        else if (obj instanceof AVLTree){
            for(T i: list){
                avl.insert(i);
            }
        }
        else if (obj instanceof RedBlackTree){
            for(T i: list){
                rbt.insert(i);
            }
        }
		else if ( obj instanceof BinaryHeap){
            if(obj instanceof MinHeap){
                try{
                    ((MinHeap) obj).clear();
                    bheap = (MinHeap) obj;
                }catch(Exception e){
                    System.out.println(""+e);
                }
                for(T i: list){
                    bheap.insert(i);
                }
            }
            else if(obj instanceof MaxHeap){
                try{
                    ((MaxHeap) obj).clear();
                    bheap = (MaxHeap) obj;
                }catch(Exception e){
                    System.out.println(""+e);
                }
                for(T i: list){
                    bheap.insert(i);
                }
            }
        }
        return obj;
    }

    /**
     * Author: Group 2
     * This should be the insert for every data structure except graph and hashtables
     * @param b: number of items to be inserted
     * @param obj: data structure to which items will be implemented
     */
    @SuppressWarnings("unchecked")
    private void insert(int b, Object obj){  //b is the number of inserts
	/*
        Items to be inserted could be Strings, Characters or Numbers therefore the type of
        random items must be checked before being generated
       */
        /*
        Storing the generated inputs into an ArrayList makes easier to ensure that no duplicates are created
        by checking if the allInserts ArrayList contains the number.
         */
        for (int i=0; i<b; ++i) {
            if (type instanceof String) {
                int index = (int) (Math.random()*26);
                T str = (T) stringLib[index];
                if (!allInserts.contains(str)) {
                    allInserts.add(str);
                }
                else i--;
            }
            if (type instanceof Character){
                int index = (int) (Math.random()*26);
                Character c = charLib[index];
                T chr = (T) c;
                if (!allInserts.contains(chr)) {
                    allInserts.add(chr);
                }
                else i--;
            }
            else if (type instanceof Number) {
                Number h = Math.random()*100;
                if (type instanceof Integer)  //special case
                    h = (int) (Math.random()*100);
                else if (type instanceof Float) h = (float) (Math.random()*100);
                T num = (T) h;
                if (!allInserts.contains(num)) {
                    allInserts.add(num);
                }
                else i--;
            }
        }
        insert(allInserts, obj);
    }

    /**
     * Author: Group 2
     * @param item to be deleted.
     * @param obj: data structure from which item is supposed to ebe deleted
     */
    @SuppressWarnings("unchecked")
    private void delete(T item, Object obj){
		/*
        Each Object is checked to see what data structure it is into to called the appropriate method.
         */
        if(obj instanceof BinarySearchTree){
            bst.delete(item);
        }
        else if (obj instanceof AVLTree){
            avl.delete(item);
        }
        else if (obj instanceof RedBlackTree){
            rbt.delete(item);
        }
		else if (obj instanceof BinaryHeap){
            try{
                if(obj instanceof MinHeap){
                    bheap = (MinHeap) obj;
                    bheap.delete();
                }
                else if(obj instanceof MaxHeap){
                    bheap = (MaxHeap) obj;
                    bheap.delete();
                }
            }catch(Exception e){
                System.out.println("Error while deleting binary heap\n"+e);
            }
        }

        /*
         * Implement delete for other data structures
         */
    }

     /**
     * Author: Group 2
     * Generate Multiple Choice Questions about insertion or deletion for the specified data structure
     * @param total: number of questions in a pool to be created (i.e. pool size)
     * @param obj: data structure
     * @param fileName: output text file name
     * @param type: insertion, deletion
     */
    private void treeMCQ(int total, Object obj, String fileName, int type){
        fileName = createNewMCQFile(fileName);
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MultipleChoice/" + fileName + ".txt", true));   //windows forward slash
            String[] traverse = {"preOrder", "postOrder", "inOrder", "levelOrder"};
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            emptyTree(obj);
            insert((int)(Math.random()*3+6), obj);
            for (int i = 0; i < total; i++) {
                int[] heightsize = {height(obj)-1, size(obj)-1, height(obj), size(obj), size(obj)+1, height(obj)+1};
                file.println("Question " + (i+1) + " (2 points)");
                int index = (int) (Math.random() * 4);

                //insertion
                if (type == 1) {
					/*
                    Generate insert MultipleChoice of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted inside a "+obj.toString()+
                            " then in which order will the nodes be visited during " + traverse[index] + " traversal?\n");
                    for (int j = 0; j < option.length; ++j) {
                        if (j == 0) {
                            printPreOrder(obj);
                        }else if (j == 1) {
                            printPostOrder(obj);
                        }else if (j == 2) {
                            printAllNodes(obj);
                        }else {
                            printLevelOrder(obj);
                        }
                        if (j == index) {
                            file.print("*");
                        }
                        file.print(option[j] + travel.toString() + "\n");
                    }
                }

                //deletion
                else if(type == 2){
					/*
                    Generate delete MultipleChoice of the specified pool size for the given data structure
                     */
                    int randomElement = (int)(Math.random()*allInserts.size());
                    file.write("If we delete " + allInserts.get(randomElement) + " from a "+obj.toString()+" containing " +allInserts +
                            ", then the new tree in " + traverse[index] + " traversal will be:\n");
                    delete(allInserts.get(randomElement), obj);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == 0)
                            printPreOrder(obj);
                        else if (j == 1)
                            printPostOrder(obj);
                        else if (j == 2)
                            printAllNodes(obj);
                        else
                            printLevelOrder(obj);
                        if (j == index)
                            file.print("*");
                        file.print(option[j] + travel.toString() + "\n");
                    }
                }

                //root
                else if (type == 3){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the root is:\n");
                    printLevelOrder(obj); T answer = travel.get(0); allInserts.remove(answer);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + answer + "\n");
                        else{
                            int rand = (int) (Math.random() * allInserts.size());
                            T wrong = allInserts.remove(rand);
                            file.print(option[j] + wrong + "\n");
                        }
                    }
                }

                //leaf
                else if (type == 4){
                    file.write("The following " + allInserts + " is inserted into a "+obj.toString()+
                            ". Which of the following is a leaf  is:\n");
                    ArrayList<T> leafNodes = treeLeaves(obj);
                    for (T leaf : leafNodes)
                        allInserts.remove(leaf);
                    T randAns = leafNodes.remove((int)(Math.random()*leafNodes.size()));
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + randAns + "\n");
                        else{
                            int rand = (int) (Math.random() * allInserts.size());
                            T wrong = allInserts.remove(rand);
                            file.print(option[j] + wrong + "\n");
                        }
                    }
                }

                //height
                else if (type == 5){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is:\n");
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + heightsize[2] + "\n");
                        else{
                            int rand = (int) (Math.random() * allInserts.size());
                            while (rand == 2)
                                rand = (int) (Math.random() * allInserts.size());
                            file.print(option[j] + heightsize[rand] + "\n");
                        }
                    }
                }

                //size
                else if (type == 6){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the size of the tree is:\n");
                    for (int j = 0; j < option.length; ++j) {
                        if (j == index)
                            file.print("*"+option[j] + heightsize[3] + "\n");
                        else{
                            int rand = (int) (Math.random() * allInserts.size());
                            while (rand == 2)
                                rand = (int) (Math.random() * allInserts.size());
                            file.print(option[j] + heightsize[rand] + "\n");
                        }
                    }
                }
                emptyTree(obj);
                insert((int)(Math.random()*3+6), obj);
                file.println("E.  None of the answers are correct.\n#randomize\n");
                file.flush();
            }
            System.out.println("Done!\n-------------------------------------\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Try Again.");
            File label = new File("MultipleChoice/", fileName + ".txt");
            label.delete();
        }
    }

    /**
     *
     * @param obj Tree
     */
    @SuppressWarnings("unchecked")
    private void emptyTree(Object obj){
        if (obj instanceof BinarySearchTree)
            bst.clearTree();
        else if(obj instanceof AVLTree)
            avl.clearTree();
        else if(obj instanceof RedBlackTree)
            rbt.clearTree();
        else if(obj instanceof BinaryHeap){
            if (obj instanceof MaxHeap)
                bheap = (MaxHeap<T>) obj;
            else if (obj instanceof MinHeap)
                bheap = (MinHeap<T>) obj;
            bheap.clearTree();
        }
        allInserts.clear();
    }

    private void treeTrueFalse(int total, Object obj, String fileName, int type){
        fileName = createNewToFFile(fileName);
        try {
            PrintWriter file = new PrintWriter(new FileWriter("TrueFalse/" + fileName + ".txt", true));   //windows forward slash
            emptyTree(obj);
            String[] traversal = {"pre-order", "post-order"};
            insert((int)(Math.random()*3+6), obj);
            for (int i = 0; i < total; i++) {
                file.println("Question " + (i+1) + " (2 points)");
                int TorF = (int) (Math.random() * 2);
                int[] sh = {height(obj)-1, size(obj)-1, height(obj), size(obj), size(obj)+1, height(obj)+1};
                int randi = (int)(Math.random()*6);
                if (TorF == 0)
                    printAllNodes(obj);
                else printLevelOrder(obj);
                if (type == 1) {
                    int traversalArrPos = (int)(Math.random()*2);
                    if (TorF == 0) printPreOrder(obj);
                    else printPostOrder(obj);
                    file.write("If the following " + allInserts + " is inserted inside a "+obj.toString()+
                            " then the " + traversal[traversalArrPos] + " traversal will be "+travel+"\n");
                    if (TorF == traversalArrPos){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{file.print("True\n");
                        file.println("*False\n");}
                }
                else if (type == 2) {
                    int traversalArrPos = (int)(Math.random()*2);
                    int randomElement = (int)(Math.random()*allInserts.size());
                    delete(allInserts.get(randomElement), obj);
                    if (TorF == 0) printPreOrder(obj);
                    else printPostOrder(obj);
                    file.write("If we delete " + allInserts.get(randomElement) + " from a "+obj.toString()+" containing " +allInserts +
                        ", then the new tree in " + traversal[traversalArrPos] + " traversal is: "+ travel +"\n");
                    if (TorF == traversalArrPos){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{file.print("True\n");
                        file.println("*False\n");}
                }
                else if (type == 3) {
					/*
                    Generate insert TrueFalse of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the root is " + travel.get(0) + ".\n");
                    if (TorF==0){
                        file.print("True\n");
                        file.println("*False\n");
                    }
                    else {
                        file.print("*True\n");
                        file.println("False\n");
                    }
                }
                else if(type == 4){
					/*
                    Generate delete MultipleChoice of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then " + travel.get(0) + " is a leaf.\n");
                    if (TorF==1){
                        file.print("True\n");
                        file.println("*False\n");
                    }
                    else {
                        file.print("*True\n");
                        file.println("False\n");
                    }
                }
                else if (type==5){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is " + sh[randi] + ".\n");
                    if (sh[randi] == height(obj)){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{
                        file.print("True\n");
                        file.println("*False\n");
                    }
                }
                else if (type==6){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is " + sh[randi] + ".\n");
                    if (sh[randi] == size(obj)){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{
                        file.print("True\n");
                        file.println("*False\n");
                    }
                }
                emptyTree(obj);
                insert((int)(Math.random()*3+6), obj);
                file.flush();
            }
            System.out.println("Done!\n-------------------------------------\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Try Again.");
            File label = new File("TrueFalse/", fileName + ".txt");
            label.delete();
        }
    }

    private void graphPool(int format, int type, String name, int path_or_cost){
        ArrayList<String> otherNodes;
        if (type == 1){
            digraph.emptyGraphMap();
            digraph.insert("v0","v1", Graph.infinity);
            digraph.insert("v0","v3", Graph.infinity);
            digraph.insert("v1","v3", Graph.infinity);
            digraph.insert("v1","v4", Graph.infinity);
            digraph.insert("v2","v0", Graph.infinity);
            digraph.insert("v2","v5", Graph.infinity);
            digraph.insert("v3", "v2", Graph.infinity);
            digraph.insert("v3", "v4", Graph.infinity);
            digraph.insert("v3","v5", Graph.infinity);
            digraph.insert("v3","v6", Graph.infinity);
            digraph.insert("v4","v6", Graph.infinity);
            digraph.insert("v6","v5", Graph.infinity);
            otherNodes = new ArrayList<>(Arrays.asList("v0", "v1", "v3", "v4", "v5"));
            digraph.type = "unweighted";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 2){
            digraph.emptyGraphMap();
            digraph.insert("Algeria", "Belgium", 7.0);
            digraph.insert("Algeria", "Chile", 3.0);
            digraph.insert("Algeria", "Denmark", 4.0);
            digraph.insert("Belgium", "Greece", 3.0);
            digraph.insert("Chile", "France", 4.0);
            digraph.insert("Denmark", "France", 2.0);
            digraph.insert("Denmark", "Ethiopia", 7.0);
            digraph.insert("France", "Greece", 5.0);
            digraph.insert("Ethiopia", "Greece", 2.0);
            otherNodes = new ArrayList<>(Arrays.asList("Belgium", "Chile", "Greece", "Denmark", "Ethiopia"));
            digraph.type = "weighted";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 3){
            digraph.emptyGraphMap();
            digraph.insert("Jan", "Feb", -5.00);
            digraph.insert("Jan", "Apr", 7.00);
            digraph.insert("Jan", "May", 2.00);
            digraph.insert("Feb", "Mar", 5.00);
            digraph.insert("Mar", "Jan", 1.00);
            digraph.insert("Mar", "Apr", -4.00);
            digraph.insert("Apr", "May", 3.00);
            digraph.insert("May", "Mar", 1.00);
            digraph.insert("May", "Feb", 6.00);
            otherNodes = new ArrayList<>(Arrays.asList("Feb", "Mar", "May"));
            digraph.type = "negative";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 4){
            digraph.emptyGraphMap();
            digraph.insert("A", "B", 4);
            digraph.insert("A", "C", 8);
            digraph.insert("B", "C", 9);
            digraph.insert("B", "D", 10);
            digraph.insert("C", "D", 7);
            otherNodes = new ArrayList<>(Arrays.asList("B", "C"));
            digraph.type = "acyclic";
            if (format == 1)
                graphMCQ(path_or_cost, name, digraph, otherNodes);
            //else if (format  == 2)
            //else if (format == 3)
        }
        else if(type == 5){
            //run the first four types using a temp file name.
            //copy them into the file for this type question.
        }
        else{
            System.out.println("Enter a valid Question Format from the Menu.");
        }
    }

    private String createNewMCQFile(String textFile){
        try {
            File label = new File("MultipleChoice/", textFile + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(textFile + " has been created");
            else{
                System.out.println(textFile + " already exists.");
                String dataStruc = textFile.substring(0, textFile.indexOf("/"));
                String dir = "MultipleChoice/"+ dataStruc;
                textFile = dataStruc + "/" + changeFileName(dir, textFile.substring(textFile.indexOf("/")+1));
                label = new File("MultipleChoice/", textFile + ".txt");
                label.createNewFile();
                System.out.println(textFile.substring(textFile.indexOf("/")+1) + " has been created instead.");
            }
            return textFile;
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        return textFile;
    }

    private String createNewToFFile(String textFile){
        try {
            File label = new File("TrueFalse/", textFile + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(textFile + " has been created");
            else{
                System.out.println(textFile + " already exists.");
                String dataStruc = textFile.substring(0, textFile.indexOf("/"));
                String dir = "TrueFalse/"+dataStruc;
                textFile = dataStruc + "/" +changeFileName(dir, textFile);
                label = new File("TrueFalse/", textFile + ".txt");
                label.createNewFile();
                System.out.println(textFile + " has been created instead.");
            }
            return textFile;
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        return textFile;
    }

    private void graphMCQ(int poc, String fname, Graph dg, ArrayList<String> others) {
        fname = createNewMCQFile(fname);
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MultipleChoice/"+fname+".txt", true));   //windows forward slash
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            int index = Math.min(others.size(), (int) (Math.random()*4));
            if (poc == 1){
                if (dg.type.equals("unweighted")) dg.bfs(dg.startPoint());
                else if (dg.type.equals("weighted")) dg.dijkstra(dg.startPoint());
                else if (dg.type.equals("negative")) dg.bellmanFord(dg.startPoint());
                else if (dg.type.equals("acyclic")) dg.topological(dg.startPoint());
                file.write("What is the cost of the path, from "+dg.startPoint()+" to "+dg.endPoint()+", in the displayed graph?\n");
                int minIterations = Math.min(others.size()+1, option.length);
                for (int i=0; i<minIterations; i++){
                    int randInt = (int)(Math.random()*others.size());
                    //System.out.println("RANDINT = "+randInt+" & ITERATOR IS "+i+" + ARRAY SIZE: "+others.size());
                    if (i == index)
                        file.println("*"+option[i]+dg.getNodeValue(dg.endPoint()));
                    else
                        file.println(option[i]+dg.getNodeValue(others.remove(randInt)));
                }
            }
            else if (poc == 2){
                file.write("What is the shortest path (using the most suitable algorithm) from "+dg.startPoint()+" to "+dg.endPoint()+" in the graph show?\n");
                for (int i=0; i<others.size()+1; i++){
                    //Wont work. need new strategy
                    //consider negative edges or graphs with cycles
                    /*if (i==0)
                        dg.bfs(dg.startPoint());
                    else if (i==1)
                        dg.dijkstra(dg.startPoint());
                    else if (i==2)
                        dg.bellmanFord(dg.startPoint());
                    else if (i==3)
                        dg.topological(dg.startPoint());
                    if (i == index)
                        file.print("*");
                    file.println(option[i]+ dg.printPath(dg.endPoint()));*/
                }
            }
            file.println("#randomize\n");
            file.flush();
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Try Again.");
            File label = new File("MultipleChoice/", fname + ".txt");
            label.delete();
        }
    }

    private void createHashTable(int format){
        Scanner scan = new Scanner(System.in);
        String filename = "HashTable/";
        int resolutionScheme;
        int mcqType;
        if(format == 1){
            System.out.println("1. Insertion\n2. Collisions");
            mcqType = scan.nextInt();

            if(mcqType==1)
                filename += "Insertion";
            else
                filename+= "Collisions";

            System.out.println("choose resolution scheme\n1. Linear Probing\n2. Quadratic Probing\n3. Chaining");
            resolutionScheme = scan.nextInt();
            filename += "_"+resolutionSchemes[resolutionScheme-1];

            System.out.println("Enter number of questions (pool size).");
            int poolSize = scan.nextInt();

            filename += "_" + poolSize + "Qs";

            //filename = createNewMCQFile(filename);
            System.out.println(filename);

            new HashTableDriver(poolSize, format, resolutionScheme, mcqType, filename);
        }
        else if(format == 2){
            System.out.println("Enter number of questions (pool size).");
            int poolSize = scan.nextInt();
            resolutionScheme = 2;
            mcqType = 2;

            filename += "Loadfactor_" + poolSize + "Qs";
            //filename = createNewToFFile(filename);
            System.out.println(filename);

            new HashTableDriver(poolSize, format, resolutionScheme, mcqType, filename);
        }
        else{
            System.out.println("choose resolution scheme\n1. Linear Probing\n2. Quadratic Probing\n3. Chaining");
            resolutionScheme = scan.nextInt();
            filename += "_"+resolutionSchemes[resolutionScheme-1];

            System.out.println("Enter number of questions (pool size).");
            int poolSize = scan.nextInt();

            filename += "_" + poolSize + "Qs";

            //filename = createNewMCQFile(filename);
            System.out.println(filename);

            mcqType = 3;

            new HashTableDriver(poolSize, format, resolutionScheme, mcqType, filename);
        }
        //scan.close();
    }

  /*
     *a method to do all the functionality of allowing users to
     input data and also allows the mehtod to be called by a file to automate the process of
     creating questions.
     */

    public static void printchoice(int dataStructure,int format, int type, int poolSize){
        int graphType= 0;
        if (dataStructure != 5) {
            switch (dataStructure) {
                case 1:
                    System.out.println("1. BinarySearchTree");
                    break;
                case 2:
                    System.out.println("1. AVLTree");
                    break;
                case 3:
                    System.out.println("1. RedBlackTree");
                    break;
                case 4:
                    System.out.println("1. Binary Min Heap / Max Heap");
                    break;
            }

            switch(format){
                case 1:
                    System.out.println("2. MCQ");
                    break;

                case 2:
                    System.out.println("2. True/False");
                    break;

                case 3:
                    System.out.println("2. Fill In Numeric");
                    break;
            }

            switch (type){
                case 1:
                    System.out.println("3. Insertion");
                    break;
                case 2:
                    System.out.println("3. Deletion");
                    break;
                case 3:
                    System.out.println("3. root");
                    break;
                case 4:
                    System.out.println("3. leaf");
                    break;
                case 5:
                    System.out.println("3. height");
                    break;
            }
            System.out.println("4. PoolSize = "+ poolSize );
        }

        else {
            System.out.println("1. Graphy and type is. ");
            switch (graphType){
                case 1:
                    System.out.println("UnweightedDigraph");

                    break;
                case 2:
                    System.out.println("WeightedDigraph");
                    break;
                case 3:
                    System.out.println("NegativeDigraph");
                    break;
                case 4:
                    System.out.println("AcyclicDigraph");

                    break;
                case 5:
                    System.out.println("EachDigraph");
                    break;
            }

        }
    }



    public static void theone(int a,int b , int c, int d, int e){
        Driver<Integer> intTree = new Driver<Integer>(0);
        Driver<String> strTree = new Driver<String>("");
        Driver<Float> fltTree = new Driver<Float>((float) 0.0);
        Driver<Character> chrTree = new Driver<Character>(' ');
        Scanner in = new Scanner(System.in);

        int dataStructure = a;
        int format = b;

        if (dataStructure < 5) {
            int type = c;

            int poolSize = d;

            String filename = "";

            switch (dataStructure) {
                case 1:
                    filename = "BinarySearchTree/";
                    break;
                case 2:
                    filename = "AVLTree/";
                    break;
                case 3:
                    filename = "RedBlackTree/";
                    break;
                case 4:
                    filename = "BinaryHeap/";
                    System.out.println("Enter a number:\n1. Binary Min Heap\n2. Binary Max Heap");
                    int minOrMax = in.nextInt();
                    //BinaryHeap(format, type, poolSize, minOrMax, filename);
                    if(minOrMax == 1){
                        heapType = 0;
                        filename += "MinHeap/";
                    }else{
                        heapType = 1;
                        filename += "MaxHeap/";
                    }
                    break;
            }

            switch (type){
                case 1:
                    filename += "Insertion_";
                    break;
                case 2:
                    filename += "Deletion_";
                    break;
                case 3:
                    filename += "root_";
                    break;
                case 4:
                    filename += "leaf_";
                    break;
                case 5:
                    filename += "height_";
                    break;
            }

            int dataStored = e;
            switch (dataStored) {
                case 1:
                    filename += "Numbers_" + poolSize + "Qs";
                    intTree.poolType(format, type, poolSize, filename);
                    break;
                case 2:
                    filename += "Words_" + poolSize + "Qs";
                    strTree.poolType(format, type, poolSize, filename);
                    break;
                case 3:
                    filename += "Decimals_" + poolSize + "Qs";

                    fltTree.poolType(format, type, poolSize, filename);
                    break;
                case 4:
                    filename += "Letters_" + poolSize + "Qs";
                    chrTree.poolType(format, type, poolSize, filename);
                    break;
            }
        }
        else {
            int graphType = c;
            int costOrPath = d;
            String file = "Graph/";
            switch (graphType){
                case 1:
                    file += "UnweightedDigraph";
                    intTree.graphPool(format, graphType, file, costOrPath);
                    break;
                case 2:
                    file += "WeightedDigraph";
                    intTree.graphPool(format, graphType, file, costOrPath);
                    break;
                case 3:
                    file += "NegativeDigraph";
                    intTree.graphPool(format, graphType, file, costOrPath);
                    break;
                case 4:
                    file += "AcyclicDigraph";
                    intTree.graphPool(format, graphType, file, costOrPath);
                    break;
                case 5:
                    file += "EachDigraph";
                    intTree.graphPool(format, graphType, file, costOrPath);
                    break;
            }
                        /*switch (format){
                            case 1:
                                graphMCQ(graphType, file);
                            case 2:
                                graphTrueFalse(graphType, file);
                            case 3:
                                graphFillIn(graphType, file);
                        }*/
        }

    }

    /**
     Group 2
     Terminal UI
     **/
    public static void main (String [] args){
        Driver<Integer> intTree = new Driver<Integer>(0);
        Scanner in = new Scanner(System.in);
        int choice;


        do{
            System.out.println("Enter a number to select.\n" +
                    "1. New Question Pool\n" +
                    "2. Choose from textFile\n" +
                    "3. Exit");
            choice = in.nextInt();
            int dataStored=0;
            int type=0;
            int poolSize =0;

            switch (choice){
                case 1:

                    System.out.println("\nChoose a Data Structure (Enter a number):\n" +
                            "1. Binary Search Tree\n" +
                            "2. AVL Tree\n" +
                            "3. Red Black Tree\n" +
                            "4. Binary Heap\n"+
                            "5. Graph\n"+
                            "6. Hash Table");
                    int dataStructure = in.nextInt();
                    System.out.println("\nChoose Question format. (Enter a number)\n" +
                            "1. MCQ\n" +
                            "2. True/False\n" +
                            "3. Fill In Numeric");
                    int format = in.nextInt();
                    if (dataStructure < 5) {
                        System.out.println("Choose Question. (Enter a number)\n" +
                                "1. Insertion - Traversal\n" +
                                "2. Deletion - Traversal\n" +
                                "3. Question about the root\n" +
                                "4. Question about the leaf\n" +
                                "5. Question about the tree height\n" +
                                "6. Question about the tree size");
                        type = in.nextInt();

                        System.out.println("Enter number of questions (pool size).");
                        poolSize = in.nextInt();
                        System.out.println("Select the type of data you wish to store:\n" +
                                "1. Integer\n" +
                                "2. String\n" +
                                "3. Float\n" +
                                "4. Char\n");
                        dataStored = in.nextInt();
                    }
                    else if(dataStructure ==5){
                        System.out.println("Which directed graph type (which algorithm) would you like?\n" +
                                "1. Unweighted (Breadth First Search)\n" +
                                "2. Weighted (Dijkstra)\n" +
                                "3. Negative Weights (Bellman-Ford)\n" +
                                "4. Acyclic (Topological Sort)\n" +
                                "5. All of the above\n");

                        int graphType = in.nextInt();

                        System.out.println("Do you want questions on...\n" +
                                "1. Path Cost\n" +
                                "2. Path Sequence\n");

                        int costOrPath = in.nextInt();
                        theone(dataStructure,format,graphType,costOrPath,0);

                    }
                    else if(dataStructure == 6){
                        intTree.createHashTable(format);
                    }
                    else
                        System.out.println("you have entered an invalid option");

                    theone(dataStructure,format,type,poolSize,dataStored);
                    break;

                case 2:
                    // will create all files with 5 digits that are enter seperated
                    // default class using make file is the quizgen folder
                    // if you are running app nativley it will be in src
                    System.out.println();
                    System.out.println("Enter filename");
                    in.nextLine();
                    System.out.println(System.getProperty("user.dir")); // to print the default directory so you know where to place the file
                    String file_name = in.nextLine();
                    System.out.println();
                    System.out.println(file_name);
                    try {
                        File input = new File(file_name);
                        //do something with data...
                        Scanner myReader = new Scanner(input);

                        int fdataStructure = myReader.nextInt();
                        myReader.nextLine(); //used to go to a new line othewise the scanner object will stay on the same line when you try get the next value

                        int fformat = myReader.nextInt();
                        myReader.nextLine(); //used to go to a new line othewise the scanner object will stay on the same line when you try get the next value

                        int ftype = myReader.nextInt();
                        myReader.nextLine(); //used to go to a new line othewise the scanner object will stay on the same line when you try get the next value

                        int fpoolSize = myReader.nextInt();
                        myReader.nextLine(); //used to go to a new line othewise the scanner object will stay on the same line when you try get the next value

                        int fdataStored = myReader.nextInt();
                        myReader.close(); //used to go to a new line othewise the scanner object will stay on the same line when you try get the next value

                        System.out.println("are thease the choices you have made? ");
                        printchoice(fdataStructure,fformat,ftype,fpoolSize); // to display the chosen choices from the file that has been selected

                        System.out.println("enter yes to continue or no to change the values entered");
                        String confermation = in.nextLine();
                        System.out.println(confermation);

                        if (confermation.equals("yes")){
                            theone(fdataStructure,fformat,ftype,fpoolSize,fdataStored);// sends the file data to a folder to then be created need to make a nother method that just prints out the methodd
                        }
                        else if(confermation.equals("no")){
                            break;
                        }
                        else{
                            System.out.println("you have enterd an invalid argument try yes or no only");

                        }
                    }

                    catch(FileNotFoundException e)
                    {
                        System.out.println("An error occurred.");
                        // in case of I/O error
                        e.printStackTrace();
                    }

                    break;

                case 3:
                    /*
                    Exit the program
                     */
                    break;


                default:
                    System.out.println("Invalid choice, Choose again");
                    break;
            }
        }
        while (choice != 4);
        System.out.println("\n!!!BYE!!!\n");
    }



    /**
     * Creates a new file name if a duplicate is detected
     * @param filename: duplicate filename
     * @return new filename
     */
    private String changeFileName (String dir, String filename){
        File folder = new File(dir);
        File[] files = folder.listFiles();
        String old; int num=0;
        for (File file: files){
            old = file.getName();
            if(old.contains(filename) && old.contains("(")){
                int temp = Integer.parseInt(old.substring(old.indexOf("(")+1, old.indexOf(")")));
                if (num<temp)
                    num = temp;
            }
        }
        filename += " (" + (++num) + ")";
        return filename;
    }
}
