import DataStructures.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.PrintWriter;

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
    T type;
    BinarySearchTree<T> bst;
    AVLTree<T> avl;
    RedBlackTree<T> rbt;
    BinaryHeap<T> bheap;
    Graph digraph;

    /**
     * Constructor
     * @param init: type of items to be inserted (i.e. Integers, Characters or String)
     */
    Driver(T init){
        allInserts = new ArrayList<T>();
        type = init;
        travel = new ArrayList<>();
        //Data Structures
        bst = new BinarySearchTree<>();
        avl = new AVLTree<>();
        rbt = new RedBlackTree();
        digraph = new Graph();
    }

    public void poolType(int format, int type, int poolSize, String filename){
        if (filename.substring(0, filename.indexOf("/")).equals("BinarySearchTree")){
            if (format == 1)
                treeMCQ(poolSize, bst, filename, type);
            if (format == 2)
                trueFalse(poolSize, bst, filename, type);
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("AVL")){
            if (format == 1)
                treeMCQ(poolSize, avl, filename, type);
            if (format == 2)
                trueFalse(poolSize, avl, filename, type);
            /*if (format == 3)
                fillIn(poolSize, avl, filename, type);*/
        }
        else if (filename.substring(0, filename.indexOf("/")).equals("RedBlack")){
            if (format == 1)
                treeMCQ(poolSize, rbt, filename, type);
            if (format == 2)
                trueFalse(poolSize, rbt, filename, type);
            /*if (format == 3)
                fillIn(poolSize, rbt, filename, type);*/
        }
        //else if (filename.substring(0, filename.indexOf("/")).equals("BinaryHeap")){}
    }

    /**
     * Author: Takaedza
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
     * Author: Takaedza
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
     * Author: Takaedza
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
     * Author: Takaedza
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
     * Author: Avhusaho
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
                    System.out.println(e);
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
                    System.out.println(e);
                }
                for(T i: list){
                    bheap.insert(i);
                }
            }
        }
        return obj;
    }

    /**
     * Author: Sihle
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
     * Author: Avhusaho
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
     * Author: Sihle
     * Generate Multiple Choice Questions about insertion or deletion for the specified data structure
     * @param total: number of questions in a pool to be created (i.e. pool size)
     * @param obj: data structure
     * @param fileName: output text file name
     * @param type: insertion, deletion
     */
    private void treeMCQ(int total, Object obj, String fileName, int type){
        try {
            File label = new File("MultipleChoice/", fileName + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(fileName + " has been created");
            else{
                System.out.println(fileName + " already exists.");
                fileName = changeFileName("MultipleChoice", fileName);
                label = new File("MultipleChoice/", fileName + ".txt");
                label.createNewFile();
                System.out.println(fileName + " has been created instead.");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
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
                    int randomElement = (int)Math.random()*allInserts.size();
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
            System.out.println("Error. Exiting now.");
            File label = new File("MultipleChoice/", fileName + ".txt");
            label.delete();
        }
    }

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

    private void trueFalse(int total, Object obj, String fileName, int type){
        try {
            File label = new File("TrueFalse/", fileName + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(fileName + " has been created");
            else{
                System.out.println(fileName + " already exists.");
                fileName = changeFileName("MultipleChoice", fileName);
                label = new File("TrueFalse/", fileName + ".txt");
                label.createNewFile();
                System.out.println(fileName + " has been created instead.");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
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
                    int traversalArrPos = (int)Math.random()*2;
                    if (TorF == 0) printPreOrder(obj);
                    else printPostOrder(obj);
                    int randomElement = (int)Math.random()*allInserts.size();
                    file.write("If the following " + allInserts + " is inserted inside a "+obj.toString()+
                            " then the " + traversal[traversalArrPos] + " traversal will be "+travel+"\n");
                    if (TorF == traversalArrPos){
                        file.print("*True");
                        file.println("False");
                    }
                    else{file.print("True");
                        file.println("*False");}
                }
                else if (type == 2) {
                    int traversalArrPos = (int)Math.random()*2;
                    int randomElement = (int)Math.random()*allInserts.size();
                    delete(allInserts.get(randomElement), obj);
                    if (TorF == 0) printPreOrder(obj);
                    else printPostOrder(obj);
                    file.write("If we delete " + allInserts.get(randomElement) + " from a "+obj.toString()+" containing " +allInserts +
                        ", then the new tree in " + traversal[traversalArrPos] + " traversal is: "+ travel +"\n");
                    if (TorF == traversalArrPos){
                        file.print("*True");
                        file.println("False");
                    }
                    else{file.print("True");
                        file.println("*False");}
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
            System.out.println("Error. Exiting now.");
            File label = new File("TrueFalse/", fileName + ".txt");
            label.delete();
        }
    }

    /**
    Takaedza
    Terminal UI
     **/
    public static void main (String [] args){
        Driver<Integer> intTree = new Driver<Integer>(0);
        Driver<String> strTree = new Driver<String>("");
        Driver<Float> fltTree = new Driver<Float>((float) 0.0);
        Driver<Character> chrTree = new Driver<Character>(' ');
        Scanner in = new Scanner(System.in);
        int choice;

        do{
            System.out.println("Enter a number to select.\n" +
                    "1. New Question Pool\n" +
                    "2. Pool from History\n" +
                    "3. Exit");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.println("\nChoose a Data Structure (Enter a number):\n" +
                            "1. Binary Search Tree\n" +
                            "2. AVL Tree\n" +
                            "3. Red Black Tree\n" +
                            "4. Binary Heap\n"+
                            "5. Graph");
                    int dataStructure = in.nextInt();

                    System.out.println("\nChoose Question format. (Enter a number)\n" +
                            "1. MCQ\n" +
                            "2. True/False\n" +
                            "3. Fill In Numeric");
                    int format = in.nextInt();

                    if (dataStructure != 5) {
                        System.out.println("Choose Question. (Enter a number)\n" +
                                "1. Insertion - Traversal\n" +
                                "2. Deletion - Traversal\n" +
                                "3. Question about the root\n" +
                                "4. Question about the leaf\n" +
                                "5. Question about the tree height\n" +
                                "6. Question about the tree size");
                        int type = in.nextInt();

                        System.out.println("Enter number of questions (pool size).");
                        int poolSize = in.nextInt();

                        String filename = "";
                        /*switch(format){
                            case 1:
                                filename = "MultipleChoice/";
                                break;
                            case 2:
                                filename = "TrueFalse/";
                                break;
                            case 3:
                                filename = "FillIn/";
                                break;
                        }*/

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
                                System.out.println("Enter a number:\n1. Binary Min Heap\n2. Binary Max Heap");
                                int minOrMax = in.nextInt();
                                BinaryHeap(format, type, poolSize, minOrMax, filename);
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


                        System.out.println("Select the type of data you wish to store:\n" +
                                "1. Integer\n" +
                                "2. String\n" +
                                "3. Float\n" +
                                "4. Char\n");
                        int dataStored = in.nextInt();
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
                    /*else {
                        System.out.println("Which directed graph type (which algorithm) would you like?\n" +
                                "1. Unweighted (Breadth First Search)\n" +
                                "2. Weighted (Dijkstra)\n" +
                                "3. Negative Weights (Bellman-Ford)\n" +
                                "4. Acyclic (Topological Sort)" +
                                "5. All of the above.\n");
                        int graphType = in.nextInt();
                        switch (graphType){
                            String file = "";
                            case 1:
                                file = "UnweightedDigraph";
                                graphQuestions(format, graphType, file);
                                break;
                            case 2:
                                file = "WeightedDigraph";
                                graphQuestions(format, graphType, file);
                                break;
                            case 3:
                                file = "NegativeDigraph";
                                graphQuestions(format, graphType, file);
                                break;
                            case 4:
                                file = "AcyclicDigraph";
                                graphQuestions(format, graphType, file);
                                break;
                            case 5:
                                file = "EachDigraph";
                                graphQuestions(format, graphType, file);
                                break;
                        }
                    }*/
                    break;
                case 2:
                    /*
                    List previously generated files
                     */
                    System.out.print("\n");
                    File folder = new File("MultipleChoice");
                    File[] files = folder.listFiles();
                    int i = 0;
                    if(files != null){
                        for (File file: files){
                            System.out.println(i +1 +". " + file);
                            i++;
                        }
                    }
                    else{
                        System.out.println("--No files in directory--");
                    }
                    System.out.println("_______________________________\n");
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
        while (choice != 3);
        System.out.println("\n!!!BYE!!!\n");
    }

    private static void BinaryHeap(int format, int type, int poolSize, int minOrMax, String filename){
        Driver<Integer> tree = new Driver<>(0);

        BinaryHeap<Integer> heap;

        if(minOrMax == 1){
            heap = new MinHeap<>();
            filename = "BinaryHeapTree/MinHeap/"+filename;
        }else{
            heap = new MaxHeap<>();
            filename = "BinaryHeapTree/MaxHeap/"+filename;
        }
        if(format == 1)
            tree.treeMCQ(poolSize, heap, filename, type);
    }

    private static void HashTable(){
        //Driver<Integer> tree = new Driver<>(0);
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
