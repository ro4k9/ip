import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke class provides the functionality for Duke chatbot.
 *
 * @author Rosa Kang
 */
public class Duke {
    /**
     * Represents the list of tasks added by the user.
     */
    protected List<Task> tasks;
    /**
     * For the display of the text on the console.
     */
    protected Display display;

    public Duke() {
        tasks = new ArrayList<>();
        display = new Display(tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Execute the command corresponding to the user input
     */
    public void run() {
        display.greeting();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input;
            input = sc.nextLine();
            try {
                if (input.isEmpty()) {
                    throw new EmptyDescriptionException();
                }
                String[] cmd = input.split(" ", 2);
                if (cmd[0].equals("bye")) {
                    display.farewell();
                    exit = true;
                } else if (cmd[0].equals("list")) {
                    display.lists();
                } else if (cmd[0].equals("mark")) {
                    markTask(cmd);
                } else if (cmd[0].equals("unmark")) {
                    unmarkTask(cmd);
                } else if (cmd[0].equals("todo")) {
                    addTodo(cmd);
                } else if (cmd[0].equals("event")) {
                    addEvent(cmd);
                } else if (cmd[0].equals("deadline")) {
                    addDeadline(cmd);
                } else if (cmd[0].equals("delete")) {
                    deleteTask(cmd);
                } else {
                    throw new UnknownCmdException();
                }
            } catch (UnknownCmdException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * A method to mark the task complete
     *
     * @param cmd An array of String containing the user input split by whitespace
     */
    public void markTask(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            int num = Integer.parseInt(cmd[1]);
            if (num > tasks.size()) {
                throw new ListOutOfBound(num);
            }
            tasks.get(num - 1).markAsDone();
            display.mark(tasks.get(num - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        }

    }

    /**
     * A method to unmark the task that had been marked complete
     *
     * @param cmd An array of String containing the user input split by whitespace
     */
    public void unmarkTask(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            int num = Integer.parseInt(cmd[1]);
            if (num > tasks.size()) {
                throw new ListOutOfBound(num);
            }
            tasks.get(num - 1).markAsNotDone();
            display.unmark(tasks.get(num - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        }
    }

    /**
     * A method to add  a new todo into tasks list
     *
     * @param cmd An array of String containing the user input split by whitespace
     */
    public void addTodo(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            Task t;
            t = new Todo(cmd[1]);
            tasks.add(t);
            display.tasks(t, tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method to add  a new event into tasks list
     *
     * @param cmd An array of String containing the user input split by whitespace
     */
    public void addEvent(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            Task t;
            String[] temp = cmd[1].split("/at", 2);

            if (temp.length < 2) {
                t = new Event(temp[0], " nil");
            } else t = new Event(temp[0], temp[1]);

            tasks.add(t);
            display.tasks(t, tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method to add  a new deadline into tasks list
     *
     * @param cmd An array of String containing the user input split by whitespace
     */
    public void addDeadline(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            Task t;
            String[] temp = cmd[1].split("/by", 2);

            if (temp.length < 2) {
                t = new Deadline(temp[0], " nil");
            } else {
                t = new Deadline(temp[0], temp[1]);
            }
            tasks.add(t);
            display.tasks(t, tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method to delete an existing task in tasks list
     *
     * @param cmd An array of String containing the user input split by whitespace
     */
    public void deleteTask(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            int num = Integer.parseInt(cmd[1]);

            if (num > tasks.size()) {
                throw new ListOutOfBound(num);
            }
            display.delete(tasks.get(num - 1), tasks.size() - 1);
            tasks.remove(num - 1);

        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        }
    }
}
