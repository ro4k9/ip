package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class provides the functionality for Duke chatbot.
 *
 * @author Rosa Kang
 */
public class Duke extends Application {
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

    public Duke(){

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

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
