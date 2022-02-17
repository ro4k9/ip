package artemis.exception;

/**
 * ListOutOfBOund is a class for handling exception when user selected a task that
 * is out of bound from the current list.
 *
 * @author Rosa Kang
 */
public class ListOutOfBound extends Exception {
    /**
     * Constructor for EmptyDescriptionException.
     *
     * @param idx invalid task number selected by the user
     */
    public ListOutOfBound(int idx) {
        super("OOPS!!! You exceeded the bound!!\nTask " + idx + " does not exist.");
    }
}
