//Name : Junhui Zhang
//ID Number : 112895310
//Recitation Number : R30

public class InvalidRFIDException extends Exception{
    public InvalidRFIDException() {} //  Default Constructor

    /**
     * Creation of custom exception so that RFID is 9 long and each char must contain A-F or 0-9
     * @param message output when exception is hit
     */
   public InvalidRFIDException(String message){
        super(message);
   }

}