package artemis.gui;
import java.io.IOException;

import artemis.Artemis;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Artem using FXML.
 */
public class Main extends Application {
    /**
     * A variable storing Artemis chatbot
     */
    private Artemis artemis = new Artemis();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("⋆⁺₊⋆ ☾⋆⁺₊⋆artemis⋆⁺₊⋆ ☾⋆⁺₊⋆");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setArtemis(artemis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
