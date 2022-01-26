package duke;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class represents an event task.
 *
 * @author Rosa Kang
 */
public class Event extends Task {
    /**
     * Store date of an event
     */
    protected String at;

    /**
     * Constructor for Event
     *
     * @param description a description of the deadline task
     * @param at          a event date
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * toString method for Event.
     *
     * @return type of the task (event) marked by E and the description of the task followed by the date
     */
    @Override
     public String toString()  {
        if(isValidDate(at)) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(at, format);
            at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public String format() {
        return "E|" + super.format() +"|" +at;
    }


}