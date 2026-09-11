package yoonchan.assignment01;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KeyboardManager {
    static final String[] alphabetKeysPerRow = {
            "QWERTYUIOP",
            "ASDFGHJKL",
            "ZXCVBNM",
            "" // Space
    };

    static final Insets keyboardInsets = new Insets(1, 1, 1, 1);

    /**
     * Helper method to createKeyboard to make formatted HBox objects for keyboard rows.
     *
     * @return The formatted keyboard row, an HBox.
     */
    private static HBox createCenteredRow() {
        HBox row = new HBox();
        row.setSpacing(6); // Horizontal space between character keys
        row.setAlignment(Pos.CENTER); // Perfectly balances elements in the middle
        return row;
    }

    /**
     * Creates a VBox object representing a keyboard. All keys will be buttons and will interact with the user's inputs.
     *
     * @return The keyboard, a VBox.
     */
    static VBox createKeyboard() {
        VBox keyboard = new VBox();
        keyboard.setPadding(keyboardInsets);
        keyboard.setSpacing(6);
        keyboard.setAlignment(Pos.CENTER);

        HBox[] keyboardRows = new HBox[4];

        // Rows 1 & 2
        for (int i = 0; i < 2; i++) {
            HBox row = createCenteredRow();

            for (int j = 0; j < alphabetKeysPerRow[i].length(); j++) {
                String key = alphabetKeysPerRow[i].charAt(j) + "";
                row.getChildren().add(new Button(key));
            }

            keyboard.getChildren().add(row);
        }

        // Rows 3 & 4
        HBox row3 = createCenteredRow();
        row3.getChildren().add(new Button("LSHIFT"));
        for (char c : alphabetKeysPerRow[2].toCharArray()) {
            row3.getChildren().add(new Button(c + ""));
        }
        row3.getChildren().add(new Button("RSHIFT"));

        HBox row4 = createCenteredRow();
        Button space = new Button("SPACE");
        space.setPrefWidth(150);
        row4.getChildren().add(space);

        keyboard.getChildren().addAll(row3, row4);

        return keyboard;
    }
}
