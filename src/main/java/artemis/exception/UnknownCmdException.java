package artemis.exception;

/**
 * EmptyDescriptionException is a class for handling unknown commands.
 *
 * @author Rosa Kang
 */
public class UnknownCmdException extends Exception {
    /**
     * Constructor for UnknownCmdException.
     */
    public UnknownCmdException() {
        super("OOPS!!! I'm sorry, but I don't know what that means...");
    }
}
