//Name : Junhui Zhang
//ID Number : 112895310
//Recitation Number : R30

public class InvalidLocationException extends  Exception{
    public InvalidLocationException() {} // Default Constructor

    /**
     * Creation of custom excepion so that location is either out, s_____ or c___
     * @param message outpput when exception is hit
     */
    public InvalidLocationException(String message) { super(message); }
}
