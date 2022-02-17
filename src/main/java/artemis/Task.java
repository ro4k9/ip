package artemis;

/**
 * A class representing and holding functionality of task.
 *
 * @author Rosa Kang
 */

public class Task {
    /**
     * Store a description of a task.
     */
    protected String description;
    /**
     * Store a boolean indicating completion of the task.
     */
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description a description of the deadline task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param description a description of the deadline task
     * @param isDone      completion status
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Display completion status in string.
     *
     * @return Status Icon for a task
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Mark the task as complete.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task as incomplete.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }


    /**
     * toString method of the task.
     *
     * @return status icon followed by a description of a task
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Format Task to be stored into txt file.
     *
     * @return String representation of Task for txt file
     */
    public String format() {
        return (isDone ? "1|" : "0|") + description;
    }
}
