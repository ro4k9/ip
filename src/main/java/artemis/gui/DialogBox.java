package artemis.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * A constructor for DialogBox.
     *
     * @param text the text to be displayed on dialog box
     * @param img the image to be displayed on dialog box
     *
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color:ede9e8; -fx-text-fill:dimgrey; -fx-border-radius: 20 20 20 20;"
                + "-fx-background-radius: 20 20 20 20; -fx-label-padding:20 30; -fx-background-insets:5;");
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Get the user's dialog box.
     *
     * @param text the text to be displayed on the user's side
     * @param img the img to be displayed on the user's side
     * @return the user's dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Get the chatbot's dialog box.
     *
     * @param text the text to be displayed on the chatbot's side
     * @param img the img to be displayed on the chatbot's side
     * @return the chatbot's dialog box
     */
    public static DialogBox getArtemisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setMinHeight(Region.USE_PREF_SIZE);
        return db;
    }
}
