package yoonchan.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author Yoonchan Rhie
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(100, 10, 10, 10));
        root.setCenter(KeyboardManager.createKeyboard());

        VBox upper = new VBox();
        Label promptText = new Label("Try typing this text. Do it as quickly and accurately as you can.");
        promptText.setMaxWidth(400);
        promptText.setWrapText(true);
        promptText.setAlignment(Pos.CENTER);

        TextField textField = new TextField();
        textField.setMaxWidth(400);
        textField.setAlignment(Pos.CENTER);

        upper.getChildren().addAll(promptText, textField);
        upper.setAlignment(Pos.CENTER);

        root.setTop(upper);

        Label keyInfoText = new Label("Press a key to begin!");
        keyInfoText.setId("key-info-text");
        root.setBottom(keyInfoText);

        Scene scene = new Scene(root, 640, 480);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            keyInfoText.setStyle("-fx-text-fill: black");
            String style = KeyboardManager.getHeldKeyStyleString();

            switch (e.getCode()) {
                case SPACE:
                    Button spaceButton = KeyboardManager.getButtonFromKey("SPACE");
                    spaceButton.setStyle(style);
                    keyInfoText.setText("SPACE");

                    e.consume(); // Prevents the space bar from triggering focused UI controls
                    return;

                case SHIFT:
                    for (Button shift : KeyboardManager.getShiftKeys()) {
                        shift.setStyle(style);
                    }
                    keyInfoText.setText("SHIFT");

                    KeyboardManager.setPunctuationKeys(KeyboardManager.Case.UPPERCASE);
                    return;

                default:
                    String key = e.getText().toUpperCase();
                    if (KeyboardManager.hasKey(key)) {
                        Button button = KeyboardManager.getButtonFromKey(key);
                        button.setStyle(style);
                        keyInfoText.setText(key);
                        return;
                    }

                    keyInfoText.setStyle("-fx-text-fill: red");
                    keyInfoText.setText("UNSUPPORTED");
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
