/**
 * Deadline class represents the task with a deadline
 *
 * @author Rosa Kang
 */
public class Deadline extends Task {
    /**
     * Store deadline of a task
     */
    protected String by;

    /**
     * Constructor for Deadline
     *
     * @param description a description of the deadline task
     * @param by          a deadline date
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * toString method for Deadline.
     *
     * @return type of the task (deadline) marked by D and the description of the task followed by the date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

     public String format() {
        return "D " + super.format() + by;
    }
}
