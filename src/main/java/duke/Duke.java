package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownCmdException;
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
