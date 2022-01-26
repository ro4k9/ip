package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * toString method for Deadline.
     *
     * @return type of the task (deadline) marked by D and the description of the task followed by the date
     */
    @Override
    public String toString() {
        if(isValidDate(by)) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(by, format);
            by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

     public String format() {
        return "D|" + super.format() + "|" + by;
    }
}