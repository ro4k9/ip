public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String field) {
        super("OOPS!!! The description of a " + field + " cannot be empty.");
    }
}