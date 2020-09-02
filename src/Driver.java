package QuizGen;
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
<<<<<<< HEAD
    T type;
    BinarySearchTree<T> bst;
    AVLTree<T> avl;
    RedBlackTree<T> rbt;
=======
    private T type;
    private BinarySearchTree<T> bst;
    private AVLTree<T> avl;
    private RedBlackTree<T> rbt;
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
    //BinaryHeap<T> heap;

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
<<<<<<< HEAD
        rbt = new RedBlackTree();
    }

    public void poolType(int format, int type, int poolSize, String filename){
=======
        rbt = new RedBlackTree<>();
    }

    private void poolType(int format, int type, int poolSize, String filename){
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
        String ds = filename;
        if (ds.substring(0, ds.indexOf("_")).equals("BST")){
            if (format == 1)
                MCQ(poolSize, bst, filename, type);
            if (format == 2)
                trueFalse(poolSize, bst, filename, type);
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        else if (ds.substring(0, ds.indexOf("_")).equals("AVL")){
            if (format == 1)
<<<<<<< HEAD
                MCQ(poolSize, avl, filename, type);
            if (format == 2)
                trueFalse(poolSize, avl, filename, type);
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        else if (ds.substring(0, ds.indexOf("_")).equals("RedBlack")){
            if (format == 1)
                MCQ(poolSize, rbt, filename, type);
            if (format == 2)
                trueFalse(poolSize, rbt, filename, type);
=======
                MCQ(poolSize, bst, filename, type);
            if (format == 2)
                trueFalse(poolSize, bst, filename, type);
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        else if (ds.substring(0, ds.indexOf("_")).equals("RBT")){
            if (format == 1)
                MCQ(poolSize, bst, filename, type);
            if (format == 2)
                trueFalse(poolSize, bst, filename, type);
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
            /*if (format == 3)
                fillIn(poolSize, bst, filename, type);*/
        }
        //else if (ds.substring(0, ds.indexOf("_")).equals("BH")){}
    }

    /**
     * Author: Takaedza
     * Get the inOrder reading of the nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printAllNodes(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> bst = (BinarySearchTree<T>) object;
            travel = bst.inOrder();
        }
        else if(object instanceof AVLTree){
            avl = (AVLTree<T>) object;
            travel = avl.inOrder();
        }
        else if(object instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) object;
            travel = rbt.inOrder();
        }
		else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap){
                MinHeap<T> minHeap = (MinHeap<T>) object;
                travel = minHeap.inOrder();
            }
            else if(object instanceof MaxHeap){
                MaxHeap<T> maxHeap = (MaxHeap<T>) object;
                travel = maxHeap.inOrder();
            }
        }
    }

    /**
     * Author: Takaedza
     * Get preOrder formation of the nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printPreOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> bst = (BinarySearchTree<T>) object;
            travel = bst.preOrder();
        }
        else if(object instanceof AVLTree){
            avl = (AVLTree<T>) object;
            travel = avl.preOrder();
        }
        else if(object instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) object;
            travel = rbt.preOrder();
        }
		else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap){
                MinHeap<T> minHeap = (MinHeap<T>) object;
                travel = minHeap.preOrder();
            }
            else if(object instanceof MaxHeap){
                MaxHeap<T> maxHeap = (MaxHeap<T>) object;
                travel = maxHeap.preOrder();
            }
        }
    }

    /**
     * Author: Takaedza
     * get post order formation of nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printPostOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> bst = (BinarySearchTree<T>) object;
            travel = bst.postOrder();
        }
        else if(object instanceof AVLTree){
            avl = (AVLTree<T>) object;
            travel = avl.postOrder();
        }
        else if(object instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) object;
            travel = rbt.postOrder();
        }
		else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap){
                MinHeap<T> minHeap = (MinHeap<T>) object;
                travel = minHeap.postOrder();
            }
            else if(object instanceof MaxHeap){
                MaxHeap<T> maxHeap = (MaxHeap<T>) object;
                travel = maxHeap.postOrder();
            }
        }
    }

    /**
     * Author: Takaedza
     * Get level ordering of nodes
     * @param object: Data structure
     */
    @SuppressWarnings("unchecked")
    private void printLevelOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> bst = (BinarySearchTree<T>) object;
            travel = bst.levelOrder();
        }
        else if(object instanceof AVLTree){
            avl = (AVLTree<T>) object;
            travel = avl.levelOrder();
        }
        else if(object instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) object;
            travel = rbt.levelOrder();
        }
		else if(object instanceof BinaryHeap){
            if(object instanceof MinHeap){
                MinHeap<T> minHeap = (MinHeap<T>) object;
                travel = minHeap.levelOrder();
            }
            else if(object instanceof MaxHeap){
                MaxHeap<T> maxHeap = (MaxHeap<T>) object;
                travel = maxHeap.levelOrder();
            }
        }
    }

<<<<<<< HEAD
=======
    @SuppressWarnings("unchecked")
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
    private int height(Object obj){
        if (obj instanceof BinarySearchTree) {
            bst = (BinarySearchTree<T>) obj;
            return bst.getHeight();
        }
        else if(obj instanceof AVLTree){
            avl = (AVLTree<T>) obj;
            return avl.getHeight();
        }
        /*else if(obj instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) obj;
            return rbt.getHeight();
        }
        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap){
                MinHeap<T> minHeap = (MinHeap<T>) obj;
                return minHeap.getHeight();
            }
            else if(obj instanceof MaxHeap){
                MaxHeap<T> maxHeap = (MaxHeap<T>) obj;
                return maxHeap.getHeight();
            }
        }*/
        return 0;
    }

<<<<<<< HEAD
=======
    @SuppressWarnings("unchecked")
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
    private int size(Object obj){
        if (obj instanceof BinarySearchTree) {
            bst = (BinarySearchTree<T>) obj;
            return bst.getSize();
        }
        else if(obj instanceof AVLTree){
            avl = (AVLTree<T>) obj;
            return avl.getSize();
        }
        /*else if(obj instanceof RedBlackTree){
            rbt = (RedBlackTree<T>) obj;
            return rbt.getSize();
        }
        else if(obj instanceof BinaryHeap){
            if(obj instanceof MinHeap){
                MinHeap<T> minHeap = (MinHeap<T>) obj;
                return minHeap.getSize();
            }
            else if(obj instanceof MaxHeap){
                MaxHeap<T> maxHeap = (MaxHeap<T>) obj;
                return maxHeap.getSize();
            }
        }*/ 
        return 0;
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
            ((BinarySearchTree) obj).root=null;
            for(T i: list){
                ((BinarySearchTree) obj).insert(i);
            }
        }
        else if (obj instanceof AVLTree){
            ((AVLTree) obj).root=null;
            for(T i: list){
                ((AVLTree) obj).insert(i);
            }
        }
        else if (obj instanceof RedBlackTree){
            ((RedBlackTree) obj).root = null;
            for(T i: list){
                ((RedBlackTree) obj).insert(i);
            }
        }
		else if ( obj instanceof BinaryHeap){
            if(obj instanceof MinHeap){
                try{
                    ((MinHeap) obj).clear();
                }catch(Exception e){
                    System.out.println(e);
                }
                for(T i: list){
                    ((MinHeap) obj).insert(i);
                }
            }
            else if(obj instanceof MaxHeap){
                try{
                    ((MaxHeap) obj).clear();
                }catch(Exception e){
                    System.out.println(e);
                }
                for(T i: list){
                    ((MaxHeap) obj).insert(i);
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
            ((BinarySearchTree) obj).delete(item);
        }
        else if (obj instanceof AVLTree){
            ((AVLTree) obj).delete(item);
        }
        else if (obj instanceof RedBlackTree){
            ((RedBlackTree) obj).delete(item);
        }
		else if (obj instanceof BinaryHeap){
            try{
                if(obj instanceof MinHeap){
                    ((MinHeap) obj).delete();
                }
                else if(obj instanceof MaxHeap){
                    ((MaxHeap) obj).delete();
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
    private void MCQ(int total, Object obj, String fileName, int type){
        try {
            File label = new File("MCQuestions/", fileName + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(fileName + " has been created");
            else{
                System.out.println(fileName + " already exists.");
                fileName = changeFileName("MCQuestions", fileName);
                label = new File("MCQuestions/", fileName + ".txt");
                label.createNewFile();
                System.out.println(fileName + " has been created instead.");
            }
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MCQuestions/" + fileName + ".txt", true));   //windows forward slash
            String[] traverse = {"preOrder", "postOrder", "inOrder", "levelOrder"};
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            allInserts.clear();
            insert((int)(Math.random()*3+6), obj);
            for (int i = 0; i < total; i++) {
                file.println("Question " + (i+1) + " (2 points)");
                int index = (int) (Math.random() * 4);
                if (type == 1) {
					/*
                    Generate insert MCQuestions of the specified pool size for the given data structure
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
                else if(type == 2){
					/*
                    Generate delete MCQuestions of the specified pool size for the given data structure
                     */
                    file.write("If we delete " + allInserts.get(index) + " from a "+obj.toString()+", " +allInserts +
                            " then new tree in " + traverse[index] + " traversal is:\n");
                    delete(allInserts.get(index), obj);        //not generic yet
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
                allInserts.clear();// inserts a * before the correct answer
                insert((int)(Math.random()*3+6), obj);
                file.println("E.  None of the answers are correct.\n#randomize\n");
                file.flush();
            }
            System.out.println("Done!\n-------------------------------------\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Exiting now.");
            File label = new File("MCQuestions/", fileName + ".txt");
            label.delete();
        }
    }

    private void trueFalse(int total, Object obj, String fileName, int type){
        try {
            File label = new File("TrueFalse/", fileName + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(fileName + " has been created");
            else{
                System.out.println(fileName + " already exists.");
<<<<<<< HEAD
                fileName = changeFileName("MCQuestions", fileName);
=======
                fileName = changeFileName("TrueFalse", fileName);
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
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
            allInserts.clear();
            insert((int)(Math.random()*3+6), obj);
            for (int i = 0; i < total; i++) {
                file.println("Question " + (i+1) + " (2 points)");
                int torf = (int) (Math.random() * 2);
<<<<<<< HEAD
                int[] sh = {3, 4, 5, 6, 7, 8};
                int randi = (int)(Math.random()*6);
=======
                int[] sh = {5, 6, 7, 8, 9};
                int randi = (int)(Math.random()*5);
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                if (torf == 0)
                    printAllNodes(obj);
                else printLevelOrder(obj);
                if (type == 1) {
					/*
                    Generate insert TrueFalse of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the root is " + travel.get(0) + ".\n");
                    if (torf==1){
                        file.print("True\n");
                        file.println("*False\n");
                    }
                    else {
                        file.print("*True\n");
                        file.println("False\n");
                    }
                }
                else if(type == 2){
					/*
                    Generate delete MCQuestions of the specified pool size for the given data structure
                     */
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then " + travel.get(0) + " is a leaf.\n");
                    if (torf==0){
                        file.print("True\n");
                        file.println("*False\n");
                    }
                    else {
                        file.print("*True\n");
                        file.println("False\n");
                    }
                }
                else if (type==3){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is " + sh[randi] + ".\n");
                    if (randi == height(obj)){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{
                        file.print("True\n");
                        file.println("*False\n");
                    }
                }
                else if (type==4){
                    file.write("If the following " + allInserts + " is inserted into a "+obj.toString()+
                            " then the height of the tree is " + sh[randi] + ".\n");
                    if (randi == size(obj)){
                        file.print("*True\n");
                        file.println("False\n");
                    }
                    else{
                        file.print("True\n");
                        file.println("*False\n");
                    }
                }
                allInserts.clear();// inserts a * before the correct answer
                insert((int)(Math.random()*3+6), obj);
                file.flush();
            }
            System.out.println("Done!\n-------------------------------------\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Exiting now.");
            File label = new File("TrueFalse/", fileName + ".txt");
<<<<<<< HEAD
            label.delete();
=======
            System.out.println(label.delete());
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
        }
    }

    /**
    Takaedza
    Terminal UI
     **/
    public static void main (String [] args){
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
                            "4. Binary Heap\n");
                    int dataStructure = in.nextInt();

                    System.out.println("\nChoose Question format. (Enter a number)\n" +
                            "1. MCQ\n" +
                            "2. True/False\n" +
                            "3. Fill In Numeric");
                    int format = in.nextInt();

                    if (format == 1)
                        System.out.println("Choose Question. (Enter a number)\n" +
                                        "1. Insertion - Traversal\n" +
                                        "2. Deletion - Traversal\n");
                    else if (format == 2)
                        System.out.println("Choose Question. (Enter a number)\n" +
                                "1. Question about the root\n" +
                                "2. Question about the leaf\n" +
                                "3. Question about the tree height\n" +
                                "4. Question about the tree size");
                    else {
                        System.out.println("Choose Question. (Enter a number)\n" +
                                "1. Insertion - Traversal\n" +
                                "2. Deletion - Traversal\n" +
                                "3. Question about the root\n" +
                                "4. Question about the leaf\n" +
                                "5. Question about the tree height\n" +
                                "6. Question about the tree size");
                    }
                    int type = in.nextInt();

                    System.out.println("Select the type of data you wish to store:\n" +
                            "1. Integer\n" +
                            "2. String\n" +
                            "3. Float\n" +
                            "4. Char\n");
                    int dataStored = in. nextInt();

                    System.out.println("Enter number of questions (pool size).");
                    int poolSize = in.nextInt();

                    String filename = "";
                    switch (dataStructure) {
                        case 1:
                            filename = "BST_";
                            break;
                        case 2:
                            filename = "AVL_";
                            break;
                        case 3:
                            filename = "RedBlack_";
                            break;
                        case 4:
                            System.out.println("Enter a number:\n1. Binary Min Heap\n2. Binary Max Heap");
                            int minOrMax = in.nextInt();
                            BinaryHeap(format, type, poolSize, minOrMax, filename);
                            break;
                    }

                        switch(format){
                            case 1:
                                filename += "MCQ_";
                                break;
                            case 2:
                                filename += "TrueFalse_";
                                break;
                            case 3:
                                filename += "FillIn_";
                                break;
                        }

                        if (format==1){
                            switch (type) {
                                case 1:
<<<<<<< HEAD
                                    filename += "Insertion_";
                                    break;
                                case 2:
                                    filename += "Deletion_";
=======
                                    filename += "Insertion";
                                    break;
                                case 2:
                                    filename += "Deletion";
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                                    break;
                            }
                        }
                        else if (format==2){
                            switch (type){
                                case 1:
<<<<<<< HEAD
                                    filename += "root_";
                                    break;
                                case 2:
                                    filename += "leaf_";
                                    break;
                                case 3:
                                    filename += "height_";
                                    break;
                                case 4:
                                    filename += "size_";
=======
                                    filename += "root";
                                    break;
                                case 2:
                                    filename += "leaf";
                                    break;
                                case 3:
                                    filename += "height";
                                    break;
                                case 4:
                                    filename += "size";
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                            }
                        }
                        else{
                            switch (type){
                                case 1:
<<<<<<< HEAD
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
=======
                                    filename += "Insertion";
                                    break;
                                case 2:
                                    filename += "Deletion";
                                    break;
                                case 3:
                                    filename += "root";
                                    break;
                                case 4:
                                    filename += "leaf";
                                    break;
                                case 5:
                                    filename += "height";
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                                    break;
                            }
                        }

                        switch (dataStored){
                            case 1:
<<<<<<< HEAD
                                filename += "Numbers_PoolSize_"+poolSize;
=======
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                                Driver<Integer> intTree = new Driver<Integer>(0);
                                intTree.poolType(format, type, poolSize, filename);
                                break;
                            case 2:
<<<<<<< HEAD
                                filename += "Words_PoolSize_"+poolSize;
=======
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                                Driver<String> strTree = new Driver<String>("");
                                strTree.poolType(format, type, poolSize, filename);
                                break;
                            case 3:
<<<<<<< HEAD
                                filename += "Decimals_PoolSize_"+poolSize;
=======
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                                Driver<Float> fltTree = new Driver<Float>((float)0.0);
                                fltTree.poolType(format, type, poolSize, filename);
                                break;
                            case 4:
<<<<<<< HEAD
                                filename += "Letters_PoolSize_"+poolSize;
=======
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
                                Driver<Character> chrTree = new Driver<Character>(' ');
                                chrTree.poolType(format, type, poolSize, filename);
                                break;
                        }
                    break;
                case 2:
                    /*
                    List previously generated files
                     */
                    System.out.print("\n");
                    File folder = new File("MCQuestions");
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

<<<<<<< HEAD
=======
    /**
     * Author: Avhusaho
     * @param format: MCQuestions, true/false or fill in numeric questions
     * @param type: insertion, deletion, root question or question about the root
     * @param poolSize: number of questions in a pool to be created (i.e. pool size)
     */
    private static void BST(int format, int type, int poolSize, String filename){
        Driver<Integer> tree = new Driver<>(0);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        if(format == 1)
            tree.MCQ(poolSize, bst, filename, type);
    }

    /**
     * Avhusaho
     * @param format: MCQuestions, true/false or fill in numeric questions
     * @param type: insertion, deletion, root question or question about the root
     * @param poolSize: number of questions in a pool to be created (i.e. pool size)
     */
    private static void AVL(int format, int type, int poolSize, String filename){
        Driver<Integer> tree = new Driver<>(0);
        AVLTree<Integer> avl = new AVLTree<>();
        if(format == 1)
            tree.MCQ(poolSize, avl, filename, type);
    }

    /**
     * Avhusaho
     * @param format: MCQuestions, true/false or fill in numeric questions
     * @param type: insertion, deletion, root question or question about the root
     * @param poolSize: number of questions in a pool to be created (i.e. pool size)
     */
    private static void RBT(int format, int type, int poolSize, String filename){
        Driver<Integer> tree = new Driver<>(0);
        RedBlackTree rbt = new RedBlackTree();
        if(format == 1)
            tree.MCQ(poolSize, rbt, filename, type);
    }

>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
    private static void BinaryHeap(int format, int type, int poolSize, int minOrMax, String filename){
        Driver<Integer> tree = new Driver<>(0);

        BinaryHeap<Integer> heap;

        if(minOrMax == 1){
            heap = new MinHeap<>();
            filename = "MinHeap_"+filename;
        }else{
            heap = new MaxHeap<>();
            filename = "MaxHeap_"+filename;
        }
        if(format == 1)
            tree.MCQ(poolSize, heap, filename, type);
    }

<<<<<<< HEAD
    private static void HashTable(){
        //Driver<Integer> tree = new Driver<>(0);
    }

=======
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
    /**
     * Creates a new file name if a duplicate is detected
     * @param filename: duplicate filename
     * @return new filename
     */
    private String changeFileName (String dir, String filename){
        File folder = new File(dir);
        File[] files = folder.listFiles();
        String old; int num=0;
<<<<<<< HEAD
        for (File file: files){
            old = file.getName();
            if(old.contains(filename) && old.contains("(")){
                int temp = Integer.parseInt(old.substring(old.indexOf("(")+1, old.indexOf(")")));
                if (num<temp)
                    num = temp;
            }
=======
        if(files != null){
            for (File file: files){
                old = file.getName();
                if(old.contains(filename) && old.contains("(")){
                    int temp = Integer.parseInt(old.substring(old.indexOf("(")+1, old.indexOf(")")));
                    if (num<temp)
                        num = temp;
                }
            }   
>>>>>>> 2d6ef926a64eb28f378d0161f68df40e28e396c3
        }
        filename += " (" + (++num) + ")";
        return filename;
    }
}
