package artemis.exception;

public class InvalidDateException extends Exception {
    /**
     * Constructor for EmptyDescriptionException.
     */
    public InvalidDateException() {
        super("OOPS! I couldn't understand the date ;-;\n Please write date in yyyy-mm-dd format!");
    }
}
