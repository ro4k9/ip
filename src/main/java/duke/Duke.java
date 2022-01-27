package duke;

import java.util.Scanner;
import java.io.IOException;

/**
 * Duke class provides the functionality for Duke chatbot.
 *
 * @author Rosa Kang
 */
public class Duke {
    /**
     * For the display of the text on the console.
     */
    protected Ui ui;
    protected Storage s;
    protected Parser p;
    protected TaskList tasks;

    /**
     * Constructor for Duke
     */
    public Duke() {
        tasks = new TaskList();
        ui = new Ui(tasks);
        s = new Storage("data/duke.txt", tasks);
        p = new Parser();

    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Execute the command corresponding to the user input
     */
    public void run() throws IOException {
        s.loadTextFile();
        s.load();
        ui.greeting();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input;
            input = sc.nextLine();

            try {
                if (input.isEmpty()) {
                    throw new EmptyDescriptionException();
                }
                String[] cmd = p.parseCommand(input); //input.split(" ", 2);
                if (cmd[0].equals("bye")) {
                    ui.farewell();
                    exit = true;
                } else if (cmd[0].equals("list")) {
                    showList();
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
     * A method to display a list of task.
     */
    public void showList() { //throws IOException {
        ui.lists();
    }

    /**
     * A method to mark the task complete.
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

            tasks.mark(num - 1);

            s.changeMarkInFile(num - 1, true);
            ui.mark(tasks.get(num - 1).toString());//tasks.get(num - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * A method to mark the task that incomplete.
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
            tasks.unmark(num - 1);
            // change status in txt file
            s.changeMarkInFile(num - 1, false);
            ui.unmark(tasks.get(num - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to add a new todo into tasks list.
     *
     * @param cmd An array of String containing the user input
     */
    public void addTodo(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            Todo t;
            t = new Todo(cmd[1]);
            tasks.add(t);
            ui.tasks(t.toString(), tasks.size());
            s.appendToFile(t.format());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to add a new event into tasks list.
     *
     * @param cmd An array of String containing the user input
     */
    public void addEvent(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            Event t;
            String[] temp = p.parseDateAt(cmd[1]);

            if (temp.length < 2) {
                t = new Event(temp[0], " nil");
            } else t = new Event(temp[0], p.convertDate(temp[1]));

            tasks.add(t);
            s.appendToFile(t.format());
            ui.tasks(t.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to add a new deadline into tasks list.
     *
     * @param cmd An array of String containing the user input
     */
    public void addDeadline(String[] cmd) {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            Deadline t;
            String[] temp = p.parseDateBy(cmd[1]);

            if (temp.length < 2) {
                t = new Deadline(temp[0], " nil");
            } else {
                t = new Deadline(temp[0], p.convertDate(temp[1]));
            }
            tasks.add(t);
            s.appendToFile(t.format());
            ui.tasks(t.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to delete an existing task in tasks list.
     *
     * @param cmd An array of String containing the user input
     */
    public void deleteTask(String[] cmd) throws IOException {
        try {
            if (cmd.length < 2) {
                throw new EmptyDescriptionException(cmd[0]);
            }
            int num = Integer.parseInt(cmd[1]);

            if (num > tasks.size()) {
                throw new ListOutOfBound(num);
            }
            ui.delete(tasks.get(num - 1), tasks.size() - 1);
            tasks.remove(num - 1);
            s.deleteLineInFile(num - 1);

        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        }
    }
}
