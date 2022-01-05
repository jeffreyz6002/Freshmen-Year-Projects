public class ItemInfoNode {
    private ItemInfo data; // data of node
    ItemInfoNode next; // data of node after node
    ItemInfoNode prev; // data of node before node

    /**
     * Default Constructor
     * creates node with null
     */
    public ItemInfoNode(){
        data = null;
        prev = null;
        next = null;
    }

    /**
     * Constructor
     * Creates node of ItemInfo, sets next and prev to null
     * @param initialData info of node
     */
    public ItemInfoNode(ItemInfo initialData){
        data = initialData;
        next = null;
        prev = null;
    }

    /**
     * Sets info
     * @param data info of node
     */
    public void setInfo(ItemInfo data){
        this.data = data;
    }

    /**
     * Gets info
     * @return info of node
     */
    public ItemInfo getInfo(){
        return data;
    }

    /**
     * Sets next node of node
     * @param newLink next node of node
     */
    public void setNext(ItemInfoNode newLink){ next = newLink; }

    /**
     * Sets previous node of node
     * @param newLink prev node of node
     */
    public void setPrev(ItemInfoNode newLink){ prev = newLink; }

    /**
     * Gets next node of node
     * @return next node of node
     */
   public ItemInfoNode getNext(){
        return next;
   }

    /**
     * Gets previous node of node
     * @return prev node of node
     */
   public ItemInfoNode getPrev(){
        return prev;
   }

}