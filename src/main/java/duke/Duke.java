package duke;

import java.io.IOException;
import java.util.Scanner;

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
    protected TaskList tasks;



    /**
     * Constructor for Duke.
     *
     * @param path path to the textfile
     */
    public Duke(String path) {
        tasks = new TaskList();
        ui = new Ui(tasks);
        s = new Storage(path, tasks);

    }

    /**
     * Constructor for Duke.
     */
    public Duke() {
        try {
            tasks = new TaskList();
            ui = new Ui(tasks);
            s = new Storage("data/duke.txt", tasks);
            s.loadTextFile();
            s.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

    /**
     * Execute the command corresponding to the user input.
     */
    public void run() throws IOException {
        s.loadTextFile();
        s.load();
        ui.greeting();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();

                Command c = Parser.parser(fullCommand);
                c.execute(tasks, ui, s);
                isExit = c.isExit();
            } catch (UnknownCmdException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Execute the command corresponding to the user input.
     *
     * @param fullCommand user input command
     * @return string message baseds on user input command
     */
    public String getResponse(String fullCommand) throws IOException {
        Command c = Parser.parser(fullCommand);
        try {
            return c.execute(tasks, ui, s);
        } catch (UnknownCmdException | EmptyDescriptionException e) {
            return e.getMessage();
        }
    }
}
