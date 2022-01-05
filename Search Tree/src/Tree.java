import java.util.*;

public class Tree {
    private TreeNode root; // root of entire tree
    private TreeNode cursor; // cursor of entire tree
    int choice; // choice intaked during session

    /**
     * Default constructor
     * sets all variables to null
     */
    public Tree(){
        root = null;
        cursor = null;
    }

    /**fds f
     * Constructor
     * sets cursor equal to root
     * @param root root of tree
     */
    public Tree (TreeNode root){
        this.root = root;
        cursor = root;
    }

    /**
     * Adds newNode to tree, newNode is child of parent from parentLabel
     * @param label label of newNode
     * @param prompt prompt of newNode
     * @param message message of newNode
     * @param parentLabel label of parentNode
     * @return true if newNode is created, false otherwise
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel){
        cursor = getNodeReference(parentLabel); // gets node that parentLabel is from
        int temp = 0;

        // searches for empty spot in array to input newNode
        while( temp < cursor.children.length){
            if(cursor.children[temp] == null){
                cursor.children[temp] = new TreeNode(label,prompt,message);
                return true;
            }
            temp++;
        }
        return false;
    }

    /**
     * Gets node using parameter
     * @param label label of node
     * @return node which its label is the parameter
     */
    public TreeNode getNodeReference(String label) {
        cursor = root; // resets cursor
        return getNodeReferenceHelper(label,cursor); // starts recursion method
    }

    /**
     * Recursive method to go through all nodes to find node under cursor with label label
     * @param label label of desired node
     * @param cursor node starting point
     * @return node with label label
     */
    public TreeNode getNodeReferenceHelper(String label, TreeNode cursor){
        // if label == cursor.label
        if(label.equalsIgnoreCase(cursor.getLabel())){
            return cursor;
        }
        else {
            if (cursor.getChildSize() != 10) { // prevents nullPointerExceptions
                // searches if children is desired node
                for (int i = 0; i < cursor.children.length; i++) {
                    if (label.equalsIgnoreCase(cursor.children[i].getLabel())) {
                        return cursor.children[i];
                    }
                }
                // recursive statement to go through tree
                for (int j = 0; j < cursor.children.length; j++) {
                    if (getNodeReferenceHelper(label, cursor.children[j]) != null) {
                        return getNodeReferenceHelper(label, cursor.children[j]);
                    }
                }
            }
        }
        return null; // base return statement
    }

    /**
     * displays tree in preorder traversal
     */
    public void preOrder(){
        cursor = root; // sets cursor to default
        preOrderHelper(cursor); // starts recursive process
    }

    /**
     * Recursive method to go through all nodes in a preOrder fashion
     * @param node starting node
     */
    public void preOrderHelper(TreeNode node){

        // displays node information
        System.out.println("Label: " + node.getLabel());
        System.out.println("Prompt: " + node.getPrompt());
        System.out.println("Message: " +node.getMessage()+"\n");

        // prevents nullPointerException
        if(node.getChildSize() == 10){
            return;
        }
        // goes down tree to display other nodes
        else {
            for(int i = 0; i < node.children.length; i++){
                preOrderHelper(node.children[i]);
            }
        }
    }

    /**
     * Begins session
     */
    public void beginSession() {
        Scanner stdin = new Scanner(System.in);
        cursor = root;

        while (cursor.getChildSize() != 10) { // prevents nullPointerExceptions
            display();
            choice = stdin.nextInt();
            System.out.println();
            // allows user to leave session
            if (choice == 0) {
                System.out.println("Session has ended.\n");
                return;
            }
            cursor = cursor.children[choice-1]; // changes cursor to chosen node
        }

        System.out.println(cursor.getMessage());
        System.out.println("Thank you for using this automatic help service!");
    }

    /**
     * Displays node messages and leaf prompts depending on user input, starting from root node
     */
    public void display(){
        System.out.println(cursor.getMessage());

        int temp = 0;

        // Displays all leaf prompts
        while( temp < cursor.children.length){
            System.out.println(temp+1 + " " +cursor.children[temp].getPrompt());
            temp++;
        }
        System.out.println("0 Exit Session.");
        System.out.print("Choice> ");
    }

    /**
     * Sets root of tree
     * sets cursor to default
     * @param root root node of tree
     */
    public void setRoot(TreeNode root){
        this.root = root;
        cursor = root;
    }
}