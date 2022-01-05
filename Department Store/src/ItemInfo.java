public class ItemInfo {
    private String name; // name of item
    private String rfidTagNumber; // rfid tag number of item
    private String initialLocation;  // starting position of item
    private String currentLocation; // current position of item
    private double price; // price of item

    /**
     * Constructor
     * Creates an item using name, rfid tag number, starting location and price
     * @param name name of item
     * @param rfidTagNumber rfid tag number of item
     * @param initialLocation starting location of item
     * @param price price of item
     */

    public ItemInfo(String name, String rfidTagNumber, String initialLocation, double price){
        this.name = name;
        this.rfidTagNumber = rfidTagNumber;
        this.initialLocation = initialLocation;
        currentLocation = initialLocation;
        this.price = price;
    }

    /**
     * Sets name of item
     * @param name name of item
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets RFID tag number of item
     * @param rfidTagNumber  rfid tag number of item
     */
    public void setRfidTagNumber(String rfidTagNumber){ this.rfidTagNumber = rfidTagNumber; }

    /**
     * Sets starting location of item
     * @param initialLocation starting location of item
     */
    public void setInitialLocation(String initialLocation){ this.initialLocation = initialLocation; }

    /**
     * Sets current location of item
     * @param currentLocation current location of item
     */
    public void setCurrentLocation(String currentLocation){ this.currentLocation = currentLocation; }

    /**
     * Sets price of item
     * @param price price of item
     */
    public void setPrice(double price){ this.price = price; }

    /**
     * Gets the name of item
     * @return bane of item
     */
    public String getName(){ return name; }

    /**
     * Gets RFID Tag Number of item
     * @return rfid tag number of item
     */
    public String getRfidTagNumber(){ return rfidTagNumber; }

    /**
     * Gets starting location of item
     * @return starting location of item
     */
    public String getInitialLocation(){ return initialLocation; }

    /**
     * Gets current location of item
     * @return current location of item
     */
    public String getCurrentLocation(){ return currentLocation; }

    /**
     * Gets price of item
     * @return price of item
     */
    public double getPrice(){ return price; }
}