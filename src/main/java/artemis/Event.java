package artemis;

/**
 * Event class represents an even task.
 *
 * @author Rosa Kang
 */
public class Event extends Task {
    /**
     * Store date of an event.
     */
    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description the description of the deadline task
     * @param at          the event date
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event.
     *
     * @param description description of the deadline task
     * @param at          event date
     * @param isDone      completion of the task
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * toString method for Event.
     *
     * @return String representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * format Event to be stored into txt file.
     *
     * @return String representation of Event for txt file
     */
    public String format() {
        return "E|" + super.format() + "|" + at;
    }


}
