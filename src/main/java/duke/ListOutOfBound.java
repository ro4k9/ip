package duke;
/**
 * ListOutOfBOund is a class for handling exception when user selected a task that
 * is out of bound from the current list
 *
 * @author Rosa Kang
 */
class ListOutOfBound extends Exception {
    /**
     * Constructor for EmptyDescriptionException
     *
     * @param num is a invalid task number selected by the user
     */
    public ListOutOfBound(int num) {
        super("OOPS!!! You exceeded the bound!!\nTask " + num + " does not exist.");
    }
}
