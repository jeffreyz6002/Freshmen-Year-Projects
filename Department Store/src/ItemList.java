public class ItemList {
    ItemInfoNode head; // Head of LL
    ItemInfoNode tail; // Tail of LL
    ItemInfoNode cursor; // Cursor of LL
    ItemInfo newItem; // New Item being inserted

    /**
     * Constructor creating list
     */
    public ItemList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * Inserts ItemInfo into LL
     * @param name name of Item
     * @param rfidTag rfidTag of Item
     * @param initPosition starting position of item
     * @param price price of item
     * @throws InvalidRFIDException exception when RFID is not all hexadecimal and is not 9 digits long
     * @throws InvalidLocationException exception when position/location when is not "out" or in formats of s_____ or c___
     */
    public void insertInfo(String name, String rfidTag,String initPosition, double price) throws InvalidRFIDException, InvalidLocationException{ // Order of Complexity: O(n)
        rfidCheck(rfidTag);
        if(locationCheck(initPosition)){
        newItem = new ItemInfo(name, rfidTag, initPosition, price); // Creates Object
        ItemInfoNode newItemNode = new ItemInfoNode(newItem);

        if (head == null) { // Insert if LL is empty
            cursor = newItemNode;
            head = newItemNode;
            tail = newItemNode;
        }
        else { // Multiple Node LL
            cursor = head;
            if (cursor == head && newItemNode.getInfo().getRfidTagNumber().compareTo(cursor.getInfo().getRfidTagNumber()) <= 0) {// Insert Item as head
                head = newItemNode;
                newItemNode.setNext(cursor);
                cursor.setPrev(newItemNode);
            } else {
                cursor = head;
                while (cursor != null) {
                    if (newItemNode.getInfo().getRfidTagNumber().compareTo(cursor.getInfo().getRfidTagNumber()) <= 0) { // Insert items to left of cursor
                        cursor.getPrev().setNext(newItemNode);
                        newItemNode.setPrev(cursor.getPrev());
                        cursor.setPrev(newItemNode);
                        newItemNode.setNext(cursor);
                        break;
                    } else if (cursor.getNext() == null &&
                            newItemNode.getInfo().getRfidTagNumber().compareTo(cursor.getInfo().getRfidTagNumber()) >= 0) { // Insert Item as tail
                        tail = newItemNode;
                        cursor.setNext(newItemNode);
                        newItemNode.setPrev(cursor);
                        break;
                    }
                    cursor = cursor.getNext();
                }
            }
        }
        }
        }

    /**
     * Removes purchases when the current location is "out"
     * 5 situations of removal
     */
    public void removeAllPurchases(){ // Order of Complexity: O(n)
        boolean removed = false;
        cursor = head;
        if (cursor == head && cursor == tail && cursor.getNext() == null && cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")) { // Removal when LL size == 1
            heading();
            printSpecific(cursor);
            cursor = null;
            head = null;
            tail = null;
        }
        else {
            heading();
            while (cursor != null) {
                if (cursor != head && cursor != tail && cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")) { // Remove middle nodes
                    printSpecific(cursor);
                    cursor.getNext().setPrev(cursor.getPrev());
                    cursor = cursor.getNext();
                    cursor.getPrev().setNext(cursor);
                    removed = true;
                }
                else if(cursor == tail && cursor != head && cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")) {// Remove tail
                    printSpecific(cursor);
                    cursor.getPrev().setNext(cursor.getNext());
                    tail = cursor.getPrev();
                    cursor = tail;
                    removed = true;
                    break;
                }
                else if(cursor == head && cursor != tail && cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")){ // Remove head
                    printSpecific(cursor);
                    head = cursor.getNext();
                    cursor.setNext(null);
                    head.setPrev(null);
                    removed = true;
                    cursor = head;
                }
                else {
                    cursor = cursor.getNext();
                }
                }
            if (!removed) { // Empty LL
                System.out.println("\nThere is nothing to remove.");
            }
        }
     }

    /**
     * Moves item from one position to another
     * @param rfidTag rfid Tag of item
     * @param source current position/location of item
     * @param dest desired location/position of item
     * @return true if item is moved false if else
     * @throws InvalidRFIDException exception when RFID is not all hexadecimal and is not 9 digits long
     * @throws InvalidLocationException exception when position/location when is not "out" or in formats of s_____ or c___
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws InvalidRFIDException, InvalidLocationException { // Order of Complexity: O(n)
        rfidCheck(rfidTag);
        if(locationCheck(dest)) {
            boolean itemMoved = false;
            cursor = head;
            while (cursor != null) {
                if (!itemMoved && cursor.getInfo().getRfidTagNumber().equals(rfidTag) && // Checks item to match criteria
                        cursor.getInfo().getCurrentLocation().equals(source)) {
                    itemMoved = true;
                    cursor.getInfo().setCurrentLocation(dest);
                    return itemMoved;
                }
                cursor = cursor.getNext();
            }
            return itemMoved;
        }
        return false;

    }

    /**
     *  Prints all items in LL using their information of name, rfid Tag Number, initial location, current location, price
     */
    public void printAll(){ // Order of Complexity: O(n)
        heading();
        cursor = head;
        while(cursor != null){ //Change inner code of while loop to complete
            System.out.printf("%-15s%-15s%-25s%-20s%-15.2f\n",
                    cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber(),
                    cursor.getInfo().getInitialLocation(), cursor.getInfo().getCurrentLocation(),
                    cursor.getInfo().getPrice());
            cursor = cursor.getNext();
        }
    }

    /**
     * Prints all items in LL when their current location is same as inputted location
     * @param location inputted location
     * @throws InvalidLocationException exception when position/location when is not "out" or in formats of s_____ or c___
     */
    public void printByLocation(String location) throws InvalidLocationException{ // Order of Complexity: O(n) here
        if(locationCheck(location)) {
            cursor = head;
            heading();
            while (cursor != null) {
                if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(location)) {
                    System.out.printf("%-15s%-15s%-25s%-20s%-15.2f\n",
                            cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber(),
                            cursor.getInfo().getInitialLocation(), cursor.getInfo().getCurrentLocation(),
                            cursor.getInfo().getPrice());
                }
                cursor = cursor.getNext();
            }
        }
        else{
            System.out.println("Location entered is invalid.");
        }
    }

    /**
     * Print all items in store if their current location is "out"
     * Moves all of those items back to original location
     */
    public void cleanStore(){ // Order of Complexity: O(n)
        cursor = head;
        if(head == null){
            System.out.println("There are no items in the store.");
            return;
        }
        else{
            System.out.println("The following item(s) have been moved back to their original locations:\n");
            heading();
            while(cursor != null) {
                if (!cursor.getInfo().getCurrentLocation().equalsIgnoreCase("out")
                        && !cursor.getInfo().getInitialLocation().equals(cursor.getInfo().getCurrentLocation())) {
                    System.out.printf("%-15s%-15s%-25s%-20s%-15.2f\n",
                            cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber(),
                            cursor.getInfo().getInitialLocation(), cursor.getInfo().getCurrentLocation(),
                            cursor.getInfo().getPrice());
                    cursor.getInfo().setCurrentLocation(cursor.getInfo().getInitialLocation());
            }
                cursor = cursor.getNext();
        }
        }
    }

    /**
     * Intakes cart number and moves all items from that cart to "out"
     * Calculates price of all items in said cart
     * @param cartNumber cart number that is ready for checkout
     * @return price of all ittems combined within cart
     * @throws InvalidLocationException exception when position/location when is not "out" or in formats of s_____ or c___
     */
    public double checkOut(String cartNumber) throws InvalidLocationException {// Order of Complexity: O(n)
        if (locationCheck(cartNumber)) {
            double total = 0;
            cursor = head;
            heading();
            while (cursor != null) {
                if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(cartNumber)) {
                    printSpecific(cursor);
                    cursor.getInfo().setCurrentLocation("out");
                    total += cursor.getInfo().getPrice();
                }
                cursor = cursor.getNext();
            }
            return total;
        }
        else{
            return -1;
    }
    }

    /**
     * Displays simple heading and categories of information of each item
     */
    public void heading(){ // Order of Complexity: O(1)
        System.out.printf("%-15s%-15s%-25s%-20s%-15s\n",
                "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.printf("%-15s%-15s%-25s%-20s%-15s\n"
        ,"---------","----","-----------------","----------------","-----");
    }

    /**
     * Prints all info on specific Item
     * @param cursor item that needs information printed
     */
    public void printSpecific(ItemInfoNode cursor){ // Order of Complexity: O(1)
        System.out.printf("%-15s%-15s%-25s%-20s%-15.2f\n",
                cursor.getInfo().getName(), cursor.getInfo().getRfidTagNumber(),
                cursor.getInfo().getInitialLocation(), cursor.getInfo().getCurrentLocation(),
                cursor.getInfo().getPrice());
        cursor.getInfo().setCurrentLocation(cursor.getInfo().getInitialLocation());

    }

    /**
     * Prints all items with given rfid tag number
     * @param rfid rfid tag number that needs all of that type outputted
     * @throws InvalidRFIDException exception when RFID is not all hexadecimal and is not 9 digits long
     */
    public void printByRFID(String rfid) throws InvalidRFIDException{ // Order of Complexity: O(n)
        rfidCheck(rfid);
        cursor = head;
        heading();
        while(cursor != null){
            if(cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfid)) {
                printSpecific(cursor);
            }
            cursor = cursor.getNext();
        }
    }

    /**
     * Checks for exceptions within rfid tag number
     * @param rfidTag rfid Tag number that needs checking
     * @throws InvalidRFIDException exception when RFID is not all hexadecimal and is not 9 digits long
     */
    public void rfidCheck (String rfidTag) throws InvalidRFIDException{
        int F = (int) 'F';
        try {
            boolean rfidValid = true;
            if(rfidTag.length() != 9) { // Length of tag number
                throw new InvalidRFIDException("RFID Tag Number is not 9 units long.");
            }
            else {
                char unit;
                for(int i = 0; i < rfidTag.length(); i++){ // makes sure char in tag number are hexadecimal
                    unit = rfidTag.charAt(i);
                    if(!Character.isDigit(unit)){
                        if(Character.toUpperCase(unit) > F){
                            throw new InvalidRFIDException("RFID Tag Number contains letter beyond F.");
                        }
                    }
                }
            }

        }
        catch(InvalidRFIDException e){
            System.out.println("\nThe RFID Tag Number entered is invalid.");
            return;
        }
    }

    /**
     * Checks location of exceptions if it fits criteria
     * @param location location input that needs to be checked
     * @return true of false whether criteria is met
     * @throws InvalidLocationException exception when position/location when is not "out" or in formats of s_____ or c___
     */
    public boolean locationCheck (String location) throws InvalidLocationException{
            boolean result = true;
        try{
            if(location.length() == 4 && (location.charAt(0) == 'c' || location.charAt(0) == 'C')){ // Checks if location is from cart and is 4 chars long
                char unit;
                for(int i = 1; i < location.length(); i++){
                    unit = location.charAt(i);
                    if(!Character.isDigit(unit)){
                        throw new InvalidLocationException("Location as cart is in invalid format.");
                    }
                }
            }
            else if(location.length() == 6 && (location.charAt(0) == 's' || location.charAt(0) == 'S')){ // Checks if location is from shelf and is 6 chars long
                char unit;
                for(int i = 1; i < location.length(); i++){
                    unit = location.charAt(i);
                    if(!Character.isDigit(unit)){
                        throw new InvalidLocationException("Location as cart is in invalid format.");
                    }
                }
            }
            else if(!location.equalsIgnoreCase("out")){ // Checks if location is from "out"
                throw new InvalidLocationException("Location as cart is in invalid format.");
            }
        }
        catch(InvalidLocationException e){
            System.out.println("\nThe location entered is invalid.");
            return false;
        }
        return true;
    }

}