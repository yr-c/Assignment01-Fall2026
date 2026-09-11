package yoonchan.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(100, 10, 10, 10));
        root.setCenter(KeyboardManager.createKeyboard());

        Label text = new Label("Try typing this text. Do it as quickly and accurately as you can.");
        text.setMaxWidth(400);
        text.setWrapText(true);

        BorderPane.setAlignment(text, javafx.geometry.Pos.CENTER);
        root.setTop(text);

        Scene scene = new Scene(root, 640, 480);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            String style = KeyboardManager.getHeldKeyStyleString();

            switch (e.getCode()) {
                case SPACE:
                    Button spaceButton = KeyboardManager.getButtonFromKey("SPACE");
                    spaceButton.setStyle(style);

                    e.consume(); // Prevents the space bar from triggering focused UI controls
                    return;

                case SHIFT:
                    for (Button shift : KeyboardManager.getShiftKeys()) {
                        shift.setStyle(style);
                    }

                    KeyboardManager.setPunctuationKeys(KeyboardManager.Case.UPPERCASE);
                    return;

                default:
                    String key = e.getText().toUpperCase();
                    if (KeyboardManager.hasKey(key)) {
                        Button button = KeyboardManager.getButtonFromKey(key);
                        button.setStyle(style);
                    }
                    break;
            }
        });

        scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
            String style = KeyboardManager.getReleasedKeyStyleString();

            switch (e.getCode()) {
                case SPACE:
                    Button spaceButton = KeyboardManager.getButtonFromKey("SPACE");
                    spaceButton.setStyle(style);

                    e.consume(); // Prevents the space bar from triggering focused UI controls
                    return;

                case SHIFT:
                    for (Button shift : KeyboardManager.getShiftKeys()) {
                        shift.setStyle(style);
                    }

                    KeyboardManager.setPunctuationKeys(KeyboardManager.Case.LOWERCASE);
                    return;

                default:
                    String key = e.getText().toUpperCase();
                    if (KeyboardManager.hasKey(key)) {
                        Button button = KeyboardManager.getButtonFromKey(key);
                        button.setStyle(style);
                    }
                    break;
            }
        });

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
