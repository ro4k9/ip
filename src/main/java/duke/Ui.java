package duke;

import java.util.List;

/**
 * A class for displaying Duke functionality.
 *
 * @author Rosa Kang
 */
public class Ui {
    private static final String LINE = "------------------------------------------";
    private static final String GREETING_MSG = "-> Hi I'm Duke~\n-> What can I do for you?";
    private static final String LIST_MSG = "-> Here are the tasks in your list:";
    private static final String MARK_MSG = "-> Nice~ I've marked this task as *done*:";
    private static final String UNMARK_MSG = "-> Okie~ I've marked this task as *not done* yet:";
    private static final String FAREWELL_MSG = "-> ~*~*Cya soon*~*~!";
    private static final String ADD_MSG = "-> Gotcha~ I've added this task:";
    private static final String DELETE_MSG = "-> Noted~ I've removed this task:";
    private static final String MATCH_MSG = "-> Here are the matching tasks in your list:\n";
    protected TaskList tasks;

    /**
     * Constructor for Ui.
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }


    /**
     * A method to display greeting message.
     */
    public void greeting() {
        System.out.println(" n____n");
        System.out.println("( o v o ) n");
        System.out.println(LINE);
        System.out.println(GREETING_MSG);
        System.out.println(LINE);
    }

    /**
     * A method to display the list.
     */
    public void lists() {
        System.out.println("  n____n");
        System.out.println(" ( o v o )");
        System.out.println("--u-----u---------------------------------");
        System.out.println(LIST_MSG);

        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.getTask(i));
        }

        System.out.println(LINE);
    }

    /**
     * A method to display farewell.
     */
    public void farewell() {
        System.out.println("   n____n");
        System.out.println("n ( o v o )");
        System.out.println(LINE);
        System.out.println(FAREWELL_MSG);
        System.out.println(LINE);
    }

    /**
     * A method to display a task that has been marked.
     */
    public void mark(String taskName) {
        System.out.println("   n____n");
        System.out.println("  ( > v < )n");
        System.out.println("---u--------------------------------------");
        System.out.println(MARK_MSG);
        System.out.println("    " + taskName);
        System.out.println(LINE);
    }

    /**
     * A method to display a task that has been unmarked.
     */
    public void unmark(String taskName) {
        System.out.println("   n____n");
        System.out.println("  ( o _ o )");
        System.out.println(LINE);
        System.out.println(UNMARK_MSG);
        System.out.println("    " + taskName);
        System.out.println(LINE);
    }

    /**
     * A method to display tasks that has been added.
     */
    public void tasks(String taskName, int total) {
        System.out.println("   n____n");
        System.out.println("  ( ^ v ^ )");
        System.out.println("---u-----u--------------------------------");
        System.out.println(ADD_MSG);
        System.out.println("    " + taskName);
        System.out.println("Now you have " + total + " task in the list.");
        System.out.println(LINE);
    }

    /**
     * A method to display deleted task.
     */
    public void delete(Task t, int total) {
        System.out.println("   n____n");
        System.out.println("  ( o n o )");
        System.out.println(LINE);
        System.out.println(DELETE_MSG);
        System.out.println("    " + t);
        System.out.println("Now you have " + total + " task in the list.");
        System.out.println(LINE);
    }

    public void matchLists(List<Task> lst) {
        System.out.println("  n____n");
        System.out.println(" ( o v o )");
        System.out.println("--u-----u---------------------------------");
        System.out.println(MATCH_MSG);

        for (int i = 0; i < lst.size(); i++) {
            System.out.println("    " + (i + 1) + "." + lst.get(i));
        }

        System.out.println(LINE);
    }
}