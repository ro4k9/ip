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


    /**
     * toString method for Event.
     *
     * @return type of the task (event) marked by E and the description of the task followed by the date
     */
    @Override
     public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public String format() {
        return "E " + super.format() + at;
    }
}