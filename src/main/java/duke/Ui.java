package duke;

import duke.storage.TaskList;

import java.util.List;

/**
 * A class for displaying Duke functionality.
 *
 * @author Rosa Kang
 */
public class Ui {
    private static final String LIST_MSG = "Here are the tasks in your list:";
    private static final String MARK_MSG = "Nice~ I've marked this task as *done*:";
    private static final String UNMARK_MSG = "Okie~ I've marked this task as *not done* yet:";
    private static final String FAREWELL_MSG = "~*~*Cya soon*~*~!";
    private static final String ADD_MSG = "Gotcha~ I've added this task:";
    private static final String DELETE_MSG = "Noted~ I've removed this task:";
    private static final String MATCH_MSG = "Here are the matching tasks in your list:\n";
    private static final String REMINDER_MSG = "Here are the reminder \n (due within 24hr) in your list:\n";
    protected TaskList tasks;

    /**
     * Constructor for Ui.
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * A method to display the list.
     *
     * @return list of task
     */
    public String lists() {
        String list = LIST_MSG + "\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            list += ("    " + (i + 1) + "." + tasks.getTask(i) + "\n");
        }

        return list;
    }

    /**
     * A method to display farewell.
     *
     * @return farewell message
     */
    public String farewell() {
        return FAREWELL_MSG;
    }

    /**
     * A method to display a task that has been marked.
     *
     * @return task that has been marked
     */
    public String mark(String taskName) {
        return MARK_MSG + "\n" + "    " + taskName;
    }

    /**
     * A method to display a task that has been unmarked.
     *
     * @return task that has been unmarked
     */
    public String unmark(String taskName) {
        return UNMARK_MSG + "\n" + "    " + taskName;
    }

    /**
     * A method to display tasks that has been added.
     *
     * @return task that has been added
     */
    public String tasks(String taskName, int total) {
        return ADD_MSG + "\n" + "    " + taskName + "\n" + "Now you have " + total + " task in the list.";
    }

    /**
     * A method to display deleted task.
     *
     * @return task that has been deleted
     */
    public String delete(Task t, int total) {
        return DELETE_MSG + "\n" + "    " + t + "\n" + "Now you have " + total + " task in the list.";
    }

    /**
    * A method to display the list of tasks matching the term given by user.
     *
     * @return tasks that matches the term given by user
    */
    public String matchList(List<Task> lst) {

        String list = MATCH_MSG + "\n";
        for (int i = 0; i < lst.size(); i++) {
            list += "    " + (i + 1) + "." + lst.get(i) + "\n";
        }

        return list;
    }

    /**
     * A method to display the list of tasks to remind
     *
     * @return tasks that matches the term given by user
     */
    public String reminderList(List<Deadline> lst) {
        String list = REMINDER_MSG + "\n";
        for (int i = 0; i < lst.size(); i++) {
            list += "    " + (i + 1) + "." + lst.get(i) + "\n";
        }
        return list;
    }
}
