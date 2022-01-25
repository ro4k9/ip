import java.util.List;

/**
 * A class for displaying
 *
 * @author Rosa Kang
 */
public class Display {
    private static final String line = "------------------------------------------";
    private static final String greeting = "-> Hi I'm Duke~\n-> What can I do for you?";
    private static final String listMsg = "-> Here are the tasks in your list:";
    private static final String markMsg = "Nice~ I've marked this task as *done*:";
    private static final String unmarkMsg = "Okie~ I've marked this task as *not done* yet:";
    private static final String farewellMsg = "~*~*Cya soon*~*~!";
    private static final String addTaskMsg = "Gotcha~ I've added this task:";
    private static final String deleteTaskMsg = "Noted~ I've removed this task:";
    //protected List<Task> tasks;

    /**
     * Constructor for Display
     *
     */
    Display() {
        //this.tasks = tasks;
    }

    /**
     * A method to display greeting message
     */
    public void greeting() {
        System.out.println(" n____n");
        System.out.println("( o v o ) n");
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    /**
     * A method to display the list
     */
    public void lists(List<String> lst) {
        System.out.println("  n____n");
        System.out.println(" ( o v o )");
        System.out.println("--u-----u---------------------------------");
        System.out.println(listMsg);

        for (int i = 0; i < lst.size(); i++) {
            System.out.println("    " + (i+1) + "." + taskLine(lst.get(i)));
            // System.out.println("    " + i + "." + tasks.get(i - 1));
        }

        System.out.println(line);
    }

    /**
     * A method to display farewell
     */
    public void farewell() {
        System.out.println("   n____n");
        System.out.println("n ( o v o )");
        System.out.println(line);
        System.out.println(farewellMsg);
        System.out.println(line);
    }

    /**
     * A method to display a task that has been marked
     */
    public void mark(String taskName) {
        System.out.println("   n____n");
        System.out.println("  ( > v < )n");
        System.out.println("---u--------------------------------------");
        System.out.println(markMsg);
        System.out.println("    " + taskName);
        System.out.println(line);
    }

    /**
     * A method to display a task that has been unmarked
     */
    public void unmark(String taskName) {
        System.out.println("   n____n");
        System.out.println("  ( o _ o )");
        System.out.println(line);
        System.out.println(unmarkMsg);
        System.out.println("    " + taskName);
        System.out.println(line);
    }

    /**
     * A method to display tasks that has been added
     */
    public void tasks(String taskName, int total) {
        System.out.println("   n____n");
        System.out.println("  ( ^ v ^ )");
        System.out.println("---u-----u--------------------------------");
        System.out.println(addTaskMsg);
        System.out.println("    " + taskName);
        System.out.println("Now you have " + total + " task in the list.");
        System.out.println(line);
    }

    /**
     * A method to display deleted task
     */
    public void delete(Task t, int total) {
        System.out.println("   n____n");
        System.out.println("  ( o n o )");
        System.out.println(line);
        System.out.println(deleteTaskMsg);
        System.out.println("    " + t);
        System.out.println("Now you have " + total + " task in the list.");
        System.out.println(line);
    }

    public String taskLine(String data) {
        String[] input = data.split("\\|",4);
        String temp;
        temp ="";

        if(input[0].equals("T")) {
          temp += "[T]";
        } else  if(input[0].equals("E")) {
            temp+= "[E]";
        } else {
            temp+= "[D]";
        }

        if(input[1].equals("1")) {
            temp +="[X] ";
        } else {
            temp+="[ ] ";
        }

        temp += input[2];


        if(input[0].equals("D")) {
            temp += " (by:" + input[3] + ")";
        } else if(input[0].equals("E")) {
            temp+= " (at:" + input[3] + ")";
        }

        return temp;
    }
}