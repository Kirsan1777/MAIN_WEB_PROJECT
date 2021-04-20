package by.nikita.web.exception;
/**
 * The {@code TransactionException} class represents TransactionException.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class TransactionException extends Exception {
    /**
     * create object of class
     */
    public TransactionException() {
    }

    /**
     * create object of class
     *
     * @param message
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * create object of class
     *
     * @param message
     * @param cause
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * create object of class
     *
     * @param cause
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }
}








