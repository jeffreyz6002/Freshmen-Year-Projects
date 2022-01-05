//Name : Junhui Zhang
//ID Number : 112895310
//Recitation Number : R30

public class FullPlaylistException extends RuntimeException {
    public FullPlaylistException(){} // Default constructor

    /**
     * Creation of custom exception
     * @param message , output when exception is hit
     */
    public FullPlaylistException(String message){
        super(message);
    }
}