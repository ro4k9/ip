/**
 * A class representing and holding functionality of task
 *
 * @author Rosa Kang
 */

public class Task {
    /**
     * Store a description of a task
     */
    protected String description;
    /**
     * Store a boolean indicating completion of the task
     */
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description a description of the deadline task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method that display completion status in string
     *
     * @return Status Icon for a task
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * A method that mark the task as complete
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * A method that mark the task as incomplete
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * toString method of the task
     *
     * @return status icon followed by a description of a task
     */
    @Override
    public String toString() {
        return (isDone? "1 " : "0 ") + description;
    }
    /*public String toString() {
        return getStatusIcon() + description;
    } */
}
