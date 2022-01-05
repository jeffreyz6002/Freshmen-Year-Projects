public class TreeNode {
    private String label; // label of treeNode
    private String message; // message of treeNode
    private String prompt; // prompt of treeNode
    TreeNode [] children; // children/leafs of treeNode

    /**
     * Default constructor
     * sets all variables to null
     */
    public TreeNode(){
        label = null;
        message = null;
        prompt = null;
    }

    /**
     * Constructor
     * sets parameter to respective variables
     * @param label label of treeNode
     * @param prompt prompt of treeNode
     * @param message message of treeNode
     */
    public TreeNode(String label, String prompt, String message){
        this.label = label;
        this.prompt = prompt;
        this.message = message;
    }

    /**
     * Gets label of treeNode
     * @return label of treeNode
     */
    public String getLabel(){
        return label;
    }

    /**
     * Gets message of treeNode
     * @return message of treeNode
     */
    public String getMessage(){
        return message;
    }

    /**
     * Gets prompt of treeNode
     * @return prompt of treeNode
     */
    public String getPrompt(){
        return prompt;
    }

    /**
     * Sets label of treeNode
     * @param label label of treeNode
     */
    public void setLabel(String label){
        this.label = label;
    }

    /**
     * Sets message of treeNode
     * @param message message of treeNode
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Sets prompt of treeNode
     * @param prompt prompt of treeNode
     */
    public void setPrompt(String prompt){
        this.prompt = prompt;
    }

    /**
     * Sets length of array Children/leafs
     * @param size length of array Children/leafs
     */
    public void setChildrenSize(int size){
        children = new TreeNode[size];
    }

    /**
     * Gets length of array
     * @return length of array, if array == null returns 10
     */
    public int getChildSize(){
        if(children == null) {
            return 10;
        }
            else{
                return children.length;
            }
        }
}
