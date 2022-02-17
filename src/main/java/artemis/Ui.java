package artemis;

import java.util.List;

import artemis.storage.TaskList;

/**
 * A class for displaying Artemis functionality.
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
    private static final String MATCH_MSG = "Here are the *matching tasks* in your list:\n";
    private static final String REMINDER_MSG = "Here are the reminder \n (due within 24hr) in your list:\n";
    protected TaskList tasks;

    /**
     * Constructor for Ui.
     *
     * @param  tasks the list of tasks
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Display the task list.
     *
     * @return list of task in String
     */
    public String lists() {
        String list = LIST_MSG + "\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            list = list.concat("    " + (i + 1) + ". " + tasks.getTask(i) + "\n");
        }
        return list;
    }

    /**
     * Display farewell message.
     *
     * @return farewell message in String
     */
    public String farewell() {
        return FAREWELL_MSG;
    }

    /**
     * Display a task that has been marked.
     *
     * @param taskName the name of the task
     * @return a task that has been marked in String
     */
    public String mark(String taskName) {
        return MARK_MSG + "\n" + "    " + taskName;
    }

    /**
     * Display a task that has been unmarked.
     *
     * @param taskName the name of the task
     * @return a task that has been unmarked in String
     */
    public String unmark(String taskName) {
        return UNMARK_MSG + "\n" + "    " + taskName;
    }

    /**
     * Display tasks that has been added.
     *
     * @param taskName the name of the task
     * @param total the total number of the task
     * @return task that has been added in String
     */
    public String tasks(String taskName, int total) {
        return ADD_MSG + "\n" + "    " + taskName + "\n" + "Now you have " + total + " task in the list.";
    }

    /**
     * Display deleted task.
     *
     * @param task the task to delete
     * @param total the total number of the task
     * @return task that has been deleted
     */
    public String delete(Task task, int total) {
        return DELETE_MSG + "\n" + "    " + task + "\n" + "Now you have " + total + " task in the list.";
    }

    /**
    * Display the list of tasks matching the term given by user.
     *
     * @param lst list containing task that matches the term given by the user
     * @return tasks that matches the term given by user
    */
    public String matchList(List<Task> lst) {

        String list = MATCH_MSG + "\n";
        for (int i = 0; i < lst.size(); i++) {
            list = list.concat("    " + (i + 1) + "." + lst.get(i) + "\n");
        }

        return list;
    }

    /**
     * Display the list of tasks to remind
     *
     * @param lst list containing task to remind,\
     * @return tasks that matches the term given by user
     */
    public String reminderList(List<Deadline> lst) {
        String list = REMINDER_MSG + "\n";
        for (int i = 0; i < lst.size(); i++) {
            list = list.concat("    " + (i + 1) + "." + lst.get(i) + "\n");
        }
        return list;
    }
}
