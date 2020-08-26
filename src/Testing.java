/**
* This is a throw away class only meant to illustrate how the functions in redblack tree should be implemented
*/

import java.util.Scanner;

public class Testing {

    public static void main(String[] args) {

        /*String a = null;
        if(a==null)
            System.out.println("null");
        System.exit(1);*/

        Testing t = new Testing();

    }

    Testing(){
        Scanner scan = new Scanner(System.in);
        RedBlackTree rbt = new RedBlackTree();
        RedBlackTree.RedBlackNode a;

        System.out.println("1. Insert\n2. Delete\n3. Find\n4. Delete tree\n");
        String input = scan.nextLine();

        while(true) {
            switch (input){
                case "1":
                    System.out.println("insert space seperated numbers terminated by 'q'");
                    input = scan.next();
                    while(!(input.toUpperCase().equals("Q"))){
                         a = rbt.new RedBlackNode(Integer.parseInt(input));
                        System.out.println(a.key);/******************Testing*****************/
                        rbt.insert(a);
                        input = scan.next();
                    }
                    rbt.printTree(rbt.root);
                    break;
                case "2":
                    System.out.println("Enter number to be deleted:");
                    input = scan.next();
                    a = rbt.new RedBlackNode(Integer.parseInt(input));
                    rbt.delete(a);
                    break;
                case "3":
                    System.out.println("Enter number to be deleted:");
                    input = scan.next();
                    a = rbt.new RedBlackNode(Integer.parseInt(input));
                    rbt.findNode(a);
                    break;
                case "4":
                    System.out.println("Enter number to be deleted:");
                    input = scan.next();
                    a = rbt.new RedBlackNode(Integer.parseInt(input));
                    rbt.deleteTree();
                    break;
                case "5":
                    rbt.printTree(rbt.root);
                default:
                    System.out.println(input);/******************Testing*/
                    System.out.println("Invalid choice, try again.");
            }
            System.out.println("1. Insert\n2. Delete\n3. Find\n4. Delete tree\n");
            input = scan.next();
        }
    }

}
