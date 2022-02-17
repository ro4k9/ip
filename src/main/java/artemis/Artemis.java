package artemis;

import java.io.IOException;

import artemis.exception.EmptyDescriptionException;
import artemis.exception.UnknownCmdException;
import artemis.storage.Storage;
import artemis.storage.TaskList;

/**
 * Artemis class provides the functionality for Artemis chatbot.
 *
 * @author Rosa Kang
 */
public class Artemis {
    /**
     * Variable to store Ui object responsible for displaying the output of user command in String.
     */
    protected Ui ui;
    /**
     * Variable to store Storage object responsible for storing a task list in local drive.
     */
    protected Storage s;
    /**
     * Variable to store TaskList object responsible for managing a task list.
     */
    protected TaskList tasks;

    /**
     * Constructor for Artemis.
     */
    public Artemis() {
        try {
            tasks = new TaskList();
            ui = new Ui(tasks);
            s = new Storage("data/artemis.txt", tasks);
            s.loadTextFile();
            s.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute the command given by the user.
     *
     * @param fullCommand user input command
     * @return String representation of the output of the command
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
