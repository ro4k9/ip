package duke;

/**
 * Deadline class represents the task with a deadline.
 *
 * @author Rosa Kang
 */
public class Deadline extends Task {
    /**
     * Store deadline of the task.
     */
    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description a description of the deadline task
     * @param by          a deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description description of the deadline task
     * @param by          deadline date
     * @param isDone      completion of the task
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * format Deadline to be stored into txt file.
     *
     * @return String representation of Deadline for txt file
     */
    public String format() {
        return "D|" + super.format() + "|" + by;
    }

    /**
     * toString method for Deadline.
     *
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
