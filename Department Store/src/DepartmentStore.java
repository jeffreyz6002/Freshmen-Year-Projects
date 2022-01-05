import java.util.*;
public class DepartmentStore {
public static void main (String [] arg) throws InvalidRFIDException, InvalidLocationException {

    Scanner stdin = new Scanner(System.in);

    ItemInfo newItem;
    ItemList itemList = new ItemList();

    String input = "testing"; // Input of user
    String name; // Name of item
    String rfid; // RFID Tag Number of item
    String cart; // Number of cart
    String initialLocation; // Starting location
    String newLocation; // New location
    String searchLocation; // Desired location to find
    double price; // Price of item
    double total; // Total price
    boolean itemMoved; // If item is moved

    System.out.println("Welcome!");

    while(!input.equalsIgnoreCase("Q")) {

        // Display options
        System.out.println("\nC - Clean store");
        System.out.println("I - Insert item into the list");
        System.out.println("L - List by location");
        System.out.println("M - Move an item in the store");
        System.out.println("O - Checkout");
        System.out.println("P - Print all items in store");
        System.out.println("R - Print by RFID tag number");
        System.out.println("U - Update inventory system");
        System.out.println("Q - Exit the program");

        System.out.print("\nPlease select an option: ");
        input = stdin.nextLine();

        System.out.println();

        switch(input.toUpperCase()){
            case ("C"): // To clean store
                itemList.cleanStore();
                break;
            case ("I"): // Insert item into LL
                System.out.print("Enter the name: ");
                name = stdin.nextLine();
                System.out.print("Enter the RFID: ");
                rfid = stdin.nextLine();
                System.out.print("Enter the original location: ");
                initialLocation = stdin.nextLine();
                System.out.print("Enter the price: ");
                price = stdin.nextDouble();
                stdin.nextLine();

                if(price < 0){
                    System.out.println("\nPrice entered is invalid.");
                }
                else {
                    itemList.insertInfo(name, rfid, initialLocation, price);
                }
                break;
            case ("L"): // Lists by Location
                System.out.print("Enter the location: ");
                searchLocation = stdin.nextLine();
                itemList.printByLocation(searchLocation);
                break;
            case ("M"): // Move items around
                System.out.print("Enter the RFID: ");
                rfid = stdin.nextLine();
                System.out.print("Enter the original location: ");
                initialLocation = stdin.nextLine();
                System.out.print("Enter the new location: ");
                newLocation = stdin.nextLine();

                itemMoved = itemList.moveItem(rfid, initialLocation, newLocation);
                if(itemMoved){
                    System.out.println("\nThe item has been moved.");
                }
                else {
                    System.out.println("\nThe item has not been moved due to invalid location or nonexistent item.");
                }
                break;
            case ("O"): // Checkouts items
                System.out.print("Enter the cart number: ");
                cart = stdin.nextLine();
                total =itemList.checkOut(cart);
                cart = cart.substring(1,cart.length());
                if(total >= 0) {
                    System.out.printf("\nThe total cost for all merchandise in cart $" + cart + " was %.2f.\n", total);
                }
                else {
                    System.out.println("Cart number is invalid.");
                }
                break;
            case ("P"): // Prints all items in LL
                itemList.printAll();
                break;
            case ("U"): // Update inventory
                itemList.removeAllPurchases();
                break;
            case("R"): // Prints by RFID
                System.out.print("Enter the RFID: ");
                rfid = stdin.nextLine();
                System.out.println();
                itemList.printByRFID(rfid);
                break;
            case("Q"): // Ends program
                break;
            default: // Backup if input is not an option
                System.out.println("Input is not one of the options.");
                break;
        }
    }
    System.out.println("Goodbye!");

}
}