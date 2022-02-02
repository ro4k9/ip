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
    protected TaskList tasks;

    /**
     * Constructor for Duke.
     */
    public Duke(String path) {
        tasks = new TaskList();
        ui = new Ui(tasks);
        s = new Storage(path, tasks);

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
}
