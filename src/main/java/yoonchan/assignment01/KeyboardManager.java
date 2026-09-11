package yoonchan.assignment01;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class KeyboardManager {
    private static Map<String, Button> keyMap = new HashMap<>(Map.of("ERR", new Button()));

    @Getter
    private static final String heldKeyStyleString = "-fx-background-color: gray";
    @Getter
    private static final String releasedKeyStyleString = "-fx-background-color: white";

    private static final String[] alphabetKeysPerRow = {
            "QWERTYUIOP",
            "ASDFGHJKL",
            "ZXCVBNM",
            "" // Space
    };

    private static final String lowercasePunctuationMarks = ";',./";

    private static final String uppercasePunctuationMarks = ":\"<>?";

    private static final Insets keyboardInsets = new Insets(1, 1, 1, 1);

    /**
     * Checks whether keyMap has a specific key.
     *
     * @param key The input key to be checked.
     * @return Whether keyMap has the input key.
     */
    public static boolean hasKey(String key) {
        return keyMap.containsKey(key);
    }

    /**
     * Finds a button from keyMap using a String key.
     *
     * @param key The input key.
     * @return The button corresponding to the key.
     */
    public static Button getButtonFromKey(String key) {
        return keyMap.get(key.toUpperCase());
    }

    /**
     * Retrieves both shift keys from keyMap.
     *
     * @return The references of both shift keys from keyMap.
     */
    public static Button[] getShiftKeys() {
        return new Button[]{keyMap.get("LSHIFT"), keyMap.get("RSHIFT")};
    }

    /**
     *
     * @return
     */
    private static Button[] getPunctuationKeys() {
        return new Button[]{
                keyMap.get(";"),
                keyMap.get("'"),
                keyMap.get(","),
                keyMap.get("."),
                keyMap.get("/"),
        };
    }

    public static void setPunctuationKeys(Case lettercase) {
        if (lettercase.equals(Case.UPPERCASE)) {
            for (int i = 0; i < getPunctuationKeys().length; i++) {
                Button button = keyMap.get(lowercasePunctuationMarks.charAt(i) + "");
                button.setText(uppercasePunctuationMarks.charAt(i) + "");
            }
        } else {
            for (int i = 0; i < getPunctuationKeys().length; i++) {
                Button button = keyMap.get(lowercasePunctuationMarks.charAt(i) + "");
                button.setText(lowercasePunctuationMarks.charAt(i) + "");
            }
        }
    }


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
                Button button = new Button(key);

                row.getChildren().add(button);
                keyMap.put(key, button);
            }

            keyboard.getChildren().add(row);
        }

        // Row 3
        HBox row3 = createCenteredRow();

        String LShiftKey = "LSHIFT";
        Button LShiftButton = new Button(LShiftKey);
        row3.getChildren().add(LShiftButton);
        keyMap.put(LShiftKey, LShiftButton);

        for (char c : alphabetKeysPerRow[2].toCharArray()) {
            String key = c + "";
            Button button = new Button(key);

            row3.getChildren().add(button);
            keyMap.put(key, button);
        }

        String RShiftKey = "RSHIFT";
        Button RShiftButton = new Button(RShiftKey);
        row3.getChildren().add(RShiftButton);
        keyMap.put(RShiftKey, RShiftButton);

        // Row 4
        HBox row4 = createCenteredRow();
        String spaceKey = "SPACE";
        Button spaceButton = new Button(spaceKey);
        spaceButton.setPrefWidth(150);

        row4.getChildren().add(spaceButton);
        keyMap.put(spaceKey, spaceButton);

        // Row 5
        HBox row5 = createCenteredRow();
        for (char c : lowercasePunctuationMarks.toCharArray()) {
            String key = c + "";
            Button button = new Button(key);

            row5.getChildren().add(button);
            keyMap.put(key, button);
        }

        // Spacer
        HBox spacer = new HBox();

        keyboard.getChildren().addAll(row3, row4, spacer, row5);

        return keyboard;
    }

    public enum Case {
        UPPERCASE, LOWERCASE
    }
}
