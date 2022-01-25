import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    protected File f;
    protected String path;

    public Duke() {
        tasks = new ArrayList<>();
        display = new Display(tasks);
    }

    public static void main(String[] args)  throws IOException{
        new Duke().run();
    }

    /**
     * Execute the command corresponding to the user input
     */
    public void run() throws IOException{
        path = "data/duke.txt";
        f = new File(path);
        f.getParentFile().mkdirs();

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public void showList() throws IOException {
        List<String> texts =  Files.readAllLines(Paths.get(path), StandardCharsets.US_ASCII);
        display.lists(texts);
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

            changeMarkOnFile(path, num-1, true);

            display.mark(tasks.get(num - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        } catch (IOException e) {
            e.printStackTrace();
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
            // change status in txt file
            changeMarkOnFile(path, num-1, false);
            display.unmark(tasks.get(num - 1).toString());
        } catch (ListOutOfBound | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The second argument of the " + cmd[0] + " must be an integer.");
        } catch (IOException e) {
            e.printStackTrace();
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
            Todo t;
            t = new Todo(cmd[1]);
            tasks.add(t);
            display.tasks(t.toString(), tasks.size());
            appendToFile(path, t.format());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            e.printStackTrace();
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
            Event t;
            String[] temp = cmd[1].split(" /at", 2);

            if (temp.length < 2) {
                t = new Event(temp[0], " nil");
            } else t = new Event(temp[0], temp[1]);

            tasks.add(t);
            appendToFile(path, t.format());
            display.tasks(t.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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
            Deadline t;
            String[] temp = cmd[1].split(" /by", 2);

            if (temp.length < 2) {
                t = new Deadline(temp[0], " nil");
            } else {
                t = new Deadline(temp[0], temp[1]);
            }
            tasks.add(t);
            appendToFile(path, t.format());
            display.tasks(t.toString(), tasks.size());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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

    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend+ "\n");
        fw.close();
    }

    private void changeMarkOnFile(String filePath, int n, boolean isDone) throws IOException{
        List<String> texts =  Files.readAllLines(Paths.get(filePath), StandardCharsets.US_ASCII);
        String temp = texts.get(n);
        String data = temp.replace(temp.substring(2,3), (isDone? "1":"0"));
        texts.set(n, data);
        Files.write(Paths.get(filePath), texts, StandardCharsets.US_ASCII);
    }
}
