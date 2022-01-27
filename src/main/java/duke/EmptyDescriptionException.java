package duke;

/**
 * EmptyDescriptionException is a class for handling exception where there is
 * no user input or the field required by the command is empty.
 *
 * @author Rosa Kang
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Constructor for EmptyDescriptionException.
     */
    public EmptyDescriptionException() {
        super("OOPS!!! You forgot to type in your command.");
    }

    /**
     * Constructor for EmptyDescriptionException.
     *
     * @param field that is required by the command
     */
    public EmptyDescriptionException(String field) {
        super("OOPS!!! The description of " + field + " cannot be empty.");
    }
}