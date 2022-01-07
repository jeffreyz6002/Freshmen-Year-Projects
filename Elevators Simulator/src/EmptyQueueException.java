public class EmptyQueueException extends RuntimeException{
    public EmptyQueueException(){} // default constructor

    /**
     * Creation of custom exception
     * @param m outputs this string message when exception is hit
     */

    public EmptyQueueException(String m){ super(m); }
}
