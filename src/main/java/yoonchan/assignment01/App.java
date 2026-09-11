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
import javafx.scene.text.TextAlignment;
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
        Scene scene = new Scene(root, 640, 480);

        root.setPadding(new Insets(100, 10, 10, 10));
        root.setCenter(KeyboardManager.createKeyboard());

        // Upper
        VBox upper = new VBox();
        upper.setSpacing(20);

        Label promptText = new Label(TextManager.getFirstText());
        promptText.setPrefWidth(500);
        promptText.setMaxWidth(550);
        promptText.setWrapText(true);
        promptText.setAlignment(Pos.CENTER);
        promptText.setTextAlignment(TextAlignment.CENTER);

        HBox textFieldSection = new HBox();
        textFieldSection.setAlignment(Pos.CENTER);
        textFieldSection.setSpacing(20);

        Button resetButton = new Button("Reset");
        Button nextButton = new Button("Next");

        TextField textField = new TextField();
        textField.setPrefWidth(400);
        promptText.setMaxWidth(550);
        textField.setAlignment(Pos.CENTER);

        textFieldSection.getChildren().addAll(resetButton, textField, nextButton);

        upper.getChildren().addAll(promptText, textFieldSection);
        upper.setAlignment(Pos.CENTER);

        root.setTop(upper);

        // Lower
        HBox lower = new HBox();
        lower.prefWidthProperty().bind(scene.widthProperty());

        Label keyInfoText = new Label("Press a key to begin!");
        keyInfoText.setId("info-text");

        Label progressInfoText = new Label("1/6");
        progressInfoText.setId("info-text");

        // Force the first label to expand horizontally
        HBox.setHgrow(keyInfoText, javafx.scene.layout.Priority.ALWAYS);
        // Ensure its text aligns to the left side of its expanded bounds
        keyInfoText.setMaxWidth(Double.MAX_VALUE);
        keyInfoText.setAlignment(Pos.BOTTOM_LEFT);

        // Ensure the second label aligns to the right side
        progressInfoText.setMaxWidth(Double.MAX_VALUE);
        progressInfoText.setAlignment(Pos.BOTTOM_RIGHT);

        lower.getChildren().addAll(keyInfoText, progressInfoText);
        root.setBottom(lower);

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
                    } else if (e.getCode() == KeyCode.ENTER) {
                        keyInfoText.setText("");
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

        resetButton.setOnAction(e -> {
            promptText.setText(TextManager.getFirstText());
            progressInfoText.setText(String.format("%d/5", TextManager.getCurrentTextIndex() + 1));
        });

        nextButton.setOnAction(e -> {
            promptText.setText(TextManager.getNextText());
            progressInfoText.setText(String.format("%d/5", TextManager.getCurrentTextIndex() + 1));
        });

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
