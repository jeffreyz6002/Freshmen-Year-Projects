import java.util.*;
import java.io.*;

public class TreeDriver {
    public static void main (String [] args) throws FileNotFoundException {
        Scanner stdin = new Scanner(System.in);
        Tree tree = new Tree();

        char choice = 'f'; // Choice input that user creates
        boolean readTXT = false; // ensures file is read before preorder/session begins
        String label; // label of option
        String prompt; // prompt of option
        String message; // message of option
        String info; // info of size for said label
        String fileName; // name of inputted file

        String parentNode; // holder for name of node that has yet to have size set
        int children; // number of children for parentNode
        int temp; // temp holder

        TreeNode newTreeNode; // newNode
        TreeNode cursorNode; // primary node director

        while (choice != 'Q'){

        // Displays options and intakes user response
        System.out.println("\nL - Load a Tree.");
        System.out.println("H - Begin a Help Session.");
        System.out.println("T - Traverse the Tree in preorder.");
        System.out.println("Q - Quit");

        System.out.print("Choice> ");
        choice = stdin.next().charAt(0);
        choice = Character.toUpperCase(choice);
        System.out.println();

        switch (choice) {
            case ('L'):
                readTXT = true; // ensures file is read
                System.out.print("Enter the file name> "); // takes in file
                stdin.nextLine();
                fileName = stdin.nextLine();

                try {
                    // file reader
                    File file = new File(fileName);
                    Scanner stdinFile = new Scanner(file);

                    // intakes root of tree
                    label = stdinFile.nextLine().trim();
                    prompt = stdinFile.nextLine().trim();
                    message = stdinFile.nextLine().trim();

                    // creates and sets root of tree
                    newTreeNode = new TreeNode(label, prompt, message);
                    tree.setRoot(newTreeNode);

                    // intakes rest of nodes
                    while (stdinFile.hasNextLine()) {
                        // intakes info line
                        info = stdinFile.nextLine().trim();
                        temp = info.indexOf(' ');

                        // splits info line into node and numChildren
                        parentNode = info.substring(0, temp);
                        children = Integer.parseInt(info.substring(temp + 1).trim());
                        cursorNode = tree.getNodeReference(parentNode);
                        cursorNode.setChildrenSize(children);

                        // intakes numChildren amount of nodes
                        // creates such nodes and inserts into tree
                        for (int i = 0; i < children; i++) {
                            label = stdinFile.nextLine().trim();
                            prompt = stdinFile.nextLine().trim();
                            message = stdinFile.nextLine().trim();

                            tree.addNode(label, prompt, message, parentNode);
                        }
                    }
                    System.out.println("\nTree created successfully!");
                    break;
                } catch (FileNotFoundException e) { // Throws fileNotFoundException incase fileName is not valid file
                    System.out.println("Stated file is not found.");
                    break;
                }
            case ('H'):
                if(readTXT) { // ensures file is read
                    tree.beginSession(); // begins session
                }
                else{ // prevents no files read
                    System.out.println("Help Session cannot begin because no file instructions were entered.\n ");
                }
                break;
            case ('T'):
                if(readTXT) { // ensures file is read
                    tree.preOrder(); // begins preOrder
                }
                else{ // prevents no files read
                    System.out.println("Preorder cannot proceed because no file instructions were entered.\n");
                }
                break;
            case('Q'):
                break;
            default:
                System.out.println("Input is not one of the options."); // prevents non valid options occuring
        }
    }
        System.out.println("Thank you for using our automated help services!"); // ending
    }
}
