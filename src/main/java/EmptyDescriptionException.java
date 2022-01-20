public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException()  {
        super("OOPS!!! You forgot to type in your command.");
    }
    public EmptyDescriptionException(String field) {
        super("OOPS!!! The description of a " + field + " cannot be empty.");
    }
}