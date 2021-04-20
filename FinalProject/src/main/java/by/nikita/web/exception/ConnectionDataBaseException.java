package by.nikita.web.exception;
/**
 * The {@code ConnectionDataBaseException} class represents ConnectionDataBaseException.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class ConnectionDataBaseException extends Exception{

    /**
     * create object of class
     */
    public ConnectionDataBaseException() {
    }

    /**
     * create object of class
     *
     * @param message
     */
    public ConnectionDataBaseException(String message) {
        super(message);
    }

    /**
     * create object of class
     *
     * @param message
     * @param cause
     */
    public ConnectionDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * create object of class
     *
     * @param cause
     */
    public ConnectionDataBaseException(Throwable cause) {
        super(cause);
    }
}
