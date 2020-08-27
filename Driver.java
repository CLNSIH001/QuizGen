package QuizGen;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.PrintWriter;

public class Driver<T extends Comparable<? super T>>{
    private BinarySearchTree<T> bst;
    //private AVL<T> avl;
    //private RBT<T> rbt;
    private ArrayList<T> allInserts;
    private ArrayList<T> travel;  //for traversals
    T type;

    Driver(T init){
        bst = new BinarySearchTree<T>();
        allInserts = new ArrayList<T>();
        type = init;
        travel = new ArrayList<>();
        /*avl = new ArrayList<>();
        rbt = new ArrayList<>();*/
    }

    private void printAllNodes(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.inOrder();
        }
        /*else if(object instanceof AVL){
            AVL<T> avl = (AVL<T>) object;
            travel = avl.inOrder;
        }
        else if(object instanceof RBT){
            RBT<T> rbt = (RBT<T>) object;
            travel = rbt.inOrder;
        }*/
    }
    private void printPreOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.preOrder();
        }
        /*else if(object instanceof AVL){
            AVL<T> avl = (AVL<T>) object;
            travel = avl.preOrder;
        }
        else if(object instanceof RBT){
            RBT<T> rbt = (RBT<T>) object;
            travel = rbt.preOrder;
        }*/
    }
    private void printPostOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.postOrder();
        }
        /*else if(object instanceof AVL){
            AVL<T> avl = (AVL<T>) object;
            travel = avl.postOrder;
        }
        else if(object instanceof RBT){
            RBT<T> rbt = (RBT<T>) object;
            travel = rbt.postOrder;
        }*/
    }
    private void printLevelOrder(Object object){
        if (object instanceof BinarySearchTree) {
            BinarySearchTree<T> binarySearchTree = (BinarySearchTree<T>) object;
            travel = binarySearchTree.levelOrder();
        }
        /*else if(object instanceof AVL){
            AVL<T> avl = (AVL<T>) object;
            travel = avl.levelOrder;
        }
        else if(object instanceof RBT){
            RBT<T> rbt = (RBT<T>) object;
            travel = rbt.levelOrder;
        }*/
    }

    //this should be the insert for everything except graph and hashtables
    //still thinking of a way to make it generic
    private void insert(int b){  //b is the number of inserts
        bst.root = null;
        for (int i=0; i<b; ++i) {
            if (type instanceof String) {
                int index = (int) (Math.random()*26);
                T str = (T) stringLib[index];
                if (!allInserts.contains(str)) {
                    allInserts.add(str);
                    bst.insert(str);
                }
                else i--;
            }
            if (type instanceof Character){
                int index = (int) (Math.random()*26);
                Character c = charLib[index];
                T chr = (T) c;
                if (!allInserts.contains(chr)) {
                    bst.insert(chr);
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
                    bst.insert(num);
                }
                else i--;
            }
        }

    }

    public void listChoices(int totalQs, String t, String fileName){
        if (t.equals("bst")||t.equals("BST")||t.equals("Binary Search Tree")||t.equals("binary search tree"))
            MCQ(totalQs, bst, fileName);
        /*else if (t.equals("avl")||t.equals("AVL"))
            MCQ(totalQs, avl);
        else if (t.equals("rbt")||t.equals("RBT")||t.equals("Red Black Tree")||t.equals("red black trees"))
            MCQ(totalQs, rbt);*/
    }

    //insertion is generic, deletion is not completely generic
    public void MCQ(int total, Object obj, String fileName){
        try {
            File label = new File("MCQuestions", fileName + ".txt");   //folder must already exits
            if (label.createNewFile())
                System.out.println(fileName + " has been created");
            else{
                System.out.println(fileName + " already exists. Please type another name and then press enter.");
                Scanner input = new Scanner(System.in);
                MCQ(total, obj, input.nextLine());
                input.close();
            }
        }
        catch(IOException e){
            System.out.println("An error occurred. Exiting");
            System.exit(0);
        }
        try {
            PrintWriter file = new PrintWriter(new FileWriter("MCQuestions/" + fileName + ".txt", true));
            String[] traverse = {"preOrder", "postOrder", "inOrder", "levelOrder"};
            String[] option = {"A.  ", "B.  ", "C.  ", "D.  "};
            allInserts.clear();
            insert((int) (Math.random() * 9 + 5));
            for (int i = 0; i < total; i++) {
                int index = (int) (Math.random() * 4);
                if (i % 2 == 0) {
                    file.write(i+1 + ". If the following " + allInserts.toString() + " is inserted inside a Binary search tree," +
                            "\n   then in which order will the nodes be visited during " + traverse[index] + " traversal?\n");
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
                        file.print(option[j] + travel.toString() + ".\n");
                    }
                }
                else {
                    file.write(i+1 + ". If we have delete " + travel.get(index) + " from the tree in the previouse question," +
                            "then new tree in " + traverse[index] + " traversal is:\n");
                    bst.delete(travel.get(index));        //not generic yet
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
                        file.print(option[j] + travel.toString() + ".\n");
                    }
                    allInserts.clear();
                    insert((int) (Math.random() * 9 + 5));
                }
		file.flush();
                file.println("E.  None of the above.\n#randomize\n");
            }
            System.out.println("Done!");
            file.close();
        }
        catch (IOException E){
            System.out.println("Error. Exiting now.");
            File label = new File("QuizGen\\History", fileName + ".txt");
            label.delete();
        }
    }

    char[] charLib = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
            'p','q','r','s','t','u','v','w','x','y','z'};
    String[] stringLib = {"apple","banana","care","down","edible","fly","garage","hello","ink","jump","kick","lie",
            "make","no","over","principle","quit","rest","sit","tumble","up","veer","wait","xenophobia","you","zit"};

    public static void main (String [] args) throws IOException, FileNotFoundException{
        //Driver<Type> var = new Driver<Type>(example);
        //really tried to make it so that it wouldn't need
        //parameters. Was no way around that. The parameter is
        //just to sorta "initialise" the type. no other way to
        //differentiate different types.
        /*Driver<Character> bst2 = new Driver<>(' ');
        bst2.insert(8);
        System.out.println("inOrder before delete");
        bst2.printAllNodes();
        System.out.println();
        System.out.println("-----------------------");
        Driver<String> bst4 = new Driver<>("");
        bst4.insert(10);
        System.out.println("inOrder before delete");
        bst4.printAllNodes();
        System.out.println();
        System.out.println("------------------------------");*/
        Driver<Integer> bst = new Driver<>(0);
        bst.listChoices(5, "bst", "MCQs");   //choice of data structure with varying questions
    }
}