package QuizGen;

import java.util.Scanner;

public class Testing {

    public static void main(String[] args) {

        new Testing();

    }

    private Testing(){
        Scanner scan = new Scanner(System.in);
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        System.out.println("1. Insert\n2. Delete\n3. Find\n4. Delete tree\n5. Print Tree\nq. Quit");
        String input = scan.nextLine();

        while(true) {
            switch (input){
                case "1":
                    System.out.println("insert space seperated numbers terminated by 'q'");
                    input = scan.next();
                    while(!(input.toUpperCase().equals("Q"))){
                        //System.out.println(a.key);/******************Testing*****************/
                        rbt.insert(Integer.parseInt(input));
                        input = scan.next();
                    }
                    rbt.printTree(rbt.root);
                    break;
                case "2":
                    System.out.println("Enter number to be deleted:");
                    input = scan.next();
                    rbt.delete(Integer.parseInt(input));
                    break;
                case "3":
                    System.out.println("Enter number to be deleted:");
                    input = scan.next();
                    System.out.println(rbt.findNode(Integer.parseInt(input)));
                    break;
                case "4":
                    rbt.deleteTree();
                    break;
                case "5":
                    rbt.printTree(rbt.root);
                    break;
                case "q":
                case "Q":
                    System.exit(1);
                default:
                    //System.out.println(input);/******************Testing*/
                    System.out.println("Invalid choice, try again.");
            }
            System.out.println("1. Insert\n2. Delete\n3. Find\n4. Delete tree\n5. Print Tree\nq. Quit");
            input = scan.next();
        }
    }

}
