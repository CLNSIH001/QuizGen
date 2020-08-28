package QuizGen;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

public class Driver<T extends Comparable<? super T>>{
    private ArrayList<T> allInserts;
    private ArrayList<T> travel;  //for traversals
    T type;

    Driver(T init){
        allInserts = new ArrayList<T>();
        type = init;
        travel = new ArrayList<>();
    }

    private void printRandomOrder(ArrayList<T> list){
        travel.clear();
        travel.add(list.get(1));
        for(int i = 0; i < list.size(); i++){
            if(i != 1)
                travel.add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    private void printAllNodes(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.inOrder();
        }
        else if(object instanceof AVLTree){
            AVLTree<T> avl = (AVLTree<T>) object;
            travel = avl.inOrder();
        }
        else if(object instanceof RedBlackTree){
            RedBlackTree<T> rbt = (RedBlackTree<T>) object;
            travel = rbt.inOrder();
        }
    }

    @SuppressWarnings("unchecked")
    private void printPreOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.preOrder();
        }
        else if(object instanceof AVLTree){
            AVLTree<T> avl = (AVLTree<T>) object;
            travel = avl.preOrder();
        }
        else if(object instanceof RedBlackTree){
            RedBlackTree<T> rbt = (RedBlackTree<T>) object;
            travel = rbt.preOrder();
        }
    }

    @SuppressWarnings("unchecked")
    private void printPostOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.postOrder();
        }
        else if(object instanceof AVLTree){
            AVLTree<T> avl = (AVLTree<T>) object;
            travel = avl.postOrder();
        }
        else if(object instanceof RedBlackTree){
            RedBlackTree<T> rbt = (RedBlackTree<T>) object;
            travel = rbt.postOrder();
        }
    }

    @SuppressWarnings("unchecked")
    private void printLevelOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.levelOrder();
        }
        else if(object instanceof AVLTree){
            AVLTree<T> avl = (AVLTree<T>) object;
            travel = avl.levelOrder();
        }
        else if(object instanceof RedBlackTree){
            RedBlackTree<T> rbt = (RedBlackTree<T>) object;
            travel = rbt.levelOrder();
        }
    }

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
        return obj;
    }

    //this should be the insert for everything except graph and hashtables
    //still thinking of a way to make it generic
    @SuppressWarnings("unchecked")
    private void insert(int b, Object obj){  //b is the number of inserts

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

    @SuppressWarnings("unchecked")
    private void delete(T item, Object obj){
        if(obj instanceof BinarySearchTree){
            ((BinarySearchTree) obj).delete(item);
        }
        else if (obj instanceof AVLTree){
            ((AVLTree) obj).delete(item);
        }
        else if (obj instanceof RedBlackTree){
            ((RedBlackTree) obj).delete(item);
        }

        /*
         * Implement delete for other data structures
         */
    }

    private void listChoices(int totalQs, Object t, String fileName, int type){
        if (t instanceof BinarySearchTree)
            MCQ(totalQs, t, fileName, type);
        else if (t instanceof AVLTree)
            MCQ(totalQs, t, fileName, type);
        else if (t instanceof RedBlackTree)
            MCQ(totalQs, t, fileName, type);
    }

    //insertion is generic, deletion is not completely generic
    private void MCQ(int total, Object obj, String fileName, int type){
        try {
            File label = new File("MCQuestions/", fileName + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(fileName + " pool txt file has been created");
            else{
                System.out.println(fileName + " already exists. Please type another name and then press enter.");
                Scanner input = new Scanner(System.in);
                MCQ(total, obj, input.nextLine(), type);
                input.close();
            }
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MCQuestions/" + fileName + ".txt", true));   //windows forward slash
            String[] traverse = {"preOrder", "postOrder", "inOrder"};
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            allInserts.clear();
            Random rn = new Random();
            int numOfInserts = rn.nextInt(7 - 5) + 4;

            insert( numOfInserts, obj);
            for (int i = 0; i < total; i++) {
                int index = (int) (Math.random() * 3);
                if (type == 1) {
                    file.write("Question (2 points)\nIf the following " + allInserts + " is inserted inside a "+obj.toString()+
                            " then in which order will the nodes be visited during " + traverse[index] + " traversal?\n");
                    for (int j = 0; j < option.length; ++j) {
                        if (j == 0) {
                            printPreOrder(obj);
                        }else if (j == 1) {
                            printPostOrder(obj);
                        }else if (j == 2) {
                            printAllNodes(obj);
                        }else {
                            printRandomOrder(allInserts);
                        }
                        if (j == index) {
                            file.print("*");
                        }
                        file.print(option[j] + travel.toString() + "\n");
                    }
                }
                else if(type == 2){
                    file.write("Question (2 points)\nIf we delete " + allInserts.get(index) + " from a "+obj.toString()+", " +allInserts +
                            " then new tree in " + traverse[index] + " traversal is:\n");
                    delete(allInserts.get(index), obj);        //not generic yet
                    allInserts.remove(index);
                    for (int j = 0; j < option.length; ++j) {
                        if (j == 0)
                            printPreOrder(obj);
                        else if (j == 1)
                            printPostOrder(obj);
                        else if (j == 2)
                            printAllNodes(obj);
                        else
                            printRandomOrder(allInserts);
                        if (j == index)
                            file.print("*");
                        file.print(option[j] + travel.toString() + "\n");
                    }
                }
                allInserts.clear();
                insert(numOfInserts, obj);
		        file.flush();
                file.println("E.  None of the answers are correct.\n#randomize\n");
                //deleteTree(obj);
            }
            System.out.println("Done!\n________________________________\n");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Exiting now.");
            File label = new File("MCQuestions/", fileName + ".txt");
            label.delete();
        }
    }

    private char[] charLib = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
            'p','q','r','s','t','u','v','w','x','y','z'};
    private String[] stringLib = {"apple","banana","care","down","edible","fly","garage","hello","ink","jump","kick","lie",
            "make","no","over","principle","quit","rest","sit","tumble","up","veer","wait","xenophobia","you","zit"};

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
                            "4. Binary Heap\n" +
                            "5. Hash Table");
                    int dataStructure = in.nextInt();

                    System.out.println("\nChoose Question format. (Enter a number)\n" +
                            "1. MCQ\n" +
                            "2. True/False\n" +
                            "3. Fill In Numeric");
                    int format = in.nextInt();

                    System.out.println("Choose Question. (Enter a number)\n" +
                            "1. Insertion - Traversal\n" +
                            "2. Deletion - Traversal\n" +
                            "3. Question about the root\n" +
                            "4. Question about the leaf\n" +
                            "5. Question about the tree height");
                    int type = in.nextInt();

                    System.out.println("Enter number of questions (pool size).");
                    int poolSize = in.nextInt();

                    switch (dataStructure){
                        case 1:
                            BST(format, type, poolSize);
                            break;
                        case 2:
                            AVL(format, type, poolSize);
                            break;
                        case 3:
                            RBT(format, type, poolSize);
                            break;
                        case 4:
                            BinaryHeap();
                            break;
                        case 5:
                            HashTable();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("");
                    File f = new File("MCQuestions");
                    int i = 0;
                    for (String file: f.list()){
                        System.out.println(i +1 +". " + file);
                        i++;
                    }
                    System.out.println("_______________________________\n");
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice, Choose again");
                    break;
            }
        }
        while (choice != 3);
        System.out.println("!!!BYE!!!");
    }

    private static void BST(int format, int type, int poolSize){
        Driver<Integer> tree = new Driver<>(0);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        String filename = "BST_";
        switch(format){
            case 1:
                filename += "MCQ_";
                break;
            case 2:
                filename += "TrueFalse";
                break;
            case 3:
                filename += "FillIn";
                break;
        }
        switch (type){
            case 1:
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
                break;
        }
        tree.listChoices(poolSize, bst, filename, type);//choice of data structure with varying questions
    }

    private static void AVL(int format, int type, int poolSize){
        Driver<Integer> tree = new Driver<>(0);
        String filename = "AVL_";
        switch(format){
            case 1:
                filename += "MCQ_";
                break;
            case 2:
                filename += "TrueFalse";
                break;
            case 3:
                filename += "FillIn";
                break;
        }
        switch (type){
            case 1:
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
                break;
        }
        AVLTree<Integer> avl = new AVLTree<>();
        tree.listChoices(poolSize, avl, filename, type);//choice of data structure with varying questions
    }

    private static void RBT(int format, int type, int poolSize){
        Driver<Integer> tree = new Driver<>(0);
        RedBlackTree rbt = new RedBlackTree();
        String filename = "RedBlack_";
        switch(format){
            case 1:
                filename += "MCQ_";
                break;
            case 2:
                filename += "TrueFalse";
                break;
            case 3:
                filename += "FillIn";
                break;
        }
        switch (type){
            case 1:
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
                break;
        }
        tree.listChoices(poolSize, rbt, filename, type);//choice of data structure with varying questions
    }

    private static void BinaryHeap(){
        Driver<Integer> tree = new Driver<>(0);
    }

    private static void HashTable(){
        Driver<Integer> tree = new Driver<>(0);
    }
}
