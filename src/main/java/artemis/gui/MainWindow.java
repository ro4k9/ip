package artemis.gui;

import java.io.IOException;

import artemis.Artemis;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private javafx.scene.control.Button closeButton;

    /**
     * A variable storing Artemis chatbot.
     */
    private Artemis artemis;

    /**
     * A variable storing user image.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    /**
     * A variable storing Artemis image.
     */
    private Image artemisImage = new Image(this.getClass().getResourceAsStream("/images/artemis.png"));

    /**
     * Initialize Javafx scroll panel and initiate greeting message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Hi I'm Artemis~\nWhat can I do for you?";
        dialogContainer.getChildren().add(
                DialogBox.getArtemisDialog(greeting, artemisImage)
        );
    }

    /**
     * Set artemis variable as an instance of artemis given in the parameter
     * @param a an instance of Artemis chatbot
     */
    public void setArtemis(Artemis a) {
        artemis = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Artemis's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();

        String response = artemis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArtemisDialog(response, artemisImage)
        );
        if (input.equals("bye")) {
            javafx.application.Platform.exit();
        }
        userInput.clear();
    }
}
