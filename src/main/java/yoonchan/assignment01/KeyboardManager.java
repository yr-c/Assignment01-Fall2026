package yoonchan.assignment01;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class KeyboardManager {
    @Getter
    private final static Map<String, Button> keyMap = new HashMap<>(Map.of("ERR", new Button()));
    @Getter
    private static final Stack<KeyCode> pressedKeys = new Stack<>();
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

    private static final String[] punctuationKeyCodes = {
            KeyCode.SEMICOLON.name(),
            KeyCode.QUOTE.name(),
            KeyCode.COMMA.name(),
            KeyCode.PERIOD.name(),
            KeyCode.SLASH.name()
    };

    private static final Insets keyboardInsets = new Insets(1, 1, 1, 1);

    /**
     * Adds a key code to the pressedKeys set of KeyboardManager.
     *
     * @param keyCode The key code to be added.
     * @return Whether the code has been successfully added.
     */
    public static boolean addPressedKey(KeyCode keyCode) {
        if (keyCode == null) {
            return false;
        }

        pressedKeys.push(keyCode);
        return true;
    }

    /**
     * Removes a key code from the pressedKeys set of KeyboardManager.
     */
    public static void removePressedKey(KeyCode keyCode) {
        pressedKeys.remove(keyCode);
    }

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
     * Helper function to retrieve keyMap's registered punctuation keys.
     *
     * @return keyMaps's registered punctuation keys.
     */
    private static Button[] getPunctuationKeys() {
        return new Button[]{
                keyMap.get(KeyCode.SEMICOLON.name()),
                keyMap.get(KeyCode.QUOTE.name()),
                keyMap.get(KeyCode.COMMA.name()),
                keyMap.get(KeyCode.PERIOD.name()),
                keyMap.get(KeyCode.SLASH.name()),
        };
    }

    /**
     * Sets the keyboard's punctuation keys to their symbols depending on the current case (upper or lower).
     *
     * @param lettercase The current state of the case.
     */
    public static void setPunctuationKeys(Case lettercase) {
        Button[] punctKeys = getPunctuationKeys();

        if (lettercase.equals(Case.UPPERCASE)) {
            for (int i = 0; i < punctKeys.length; i++) {
                punctKeys[i].setText(uppercasePunctuationMarks.charAt(i) + "");
            }
        } else {
            for (int i = 0; i < punctKeys.length; i++) {
                punctKeys[i].setText(lowercasePunctuationMarks.charAt(i) + "");
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
     * Helper method to createKeyboard that handles the instantiation and mapping of all keys into the keyMap.
     */
    private static void registerKeys() {
        // Rows 1 & 2
        for (int i = 0; i < 2; i++) {
            for (char c : alphabetKeysPerRow[i].toCharArray()) {
                String key = String.valueOf(c);
                keyMap.put(key, new Button(key));
            }
        }

        // Row 3 (LSHIFT, letters, RSHIFT)
        keyMap.put("LSHIFT", new Button("LSHIFT"));
        for (char c : alphabetKeysPerRow[2].toCharArray()) {
            String key = String.valueOf(c);
            keyMap.put(key, new Button(key));
        }
        keyMap.put("RSHIFT", new Button("RSHIFT"));

        // Row 4 (Space bar)
        Button spaceButton = new Button("SPACE");
        spaceButton.setPrefWidth(150);
        keyMap.put("SPACE", spaceButton);

        // Row 5 (Punctuation)
        for (int i = 0; i < lowercasePunctuationMarks.length(); i++) {
            String symbol = String.valueOf(lowercasePunctuationMarks.charAt(i));
            String keyName = punctuationKeyCodes[i];
            keyMap.put(keyName, new Button(symbol));
        }
    }

    /**
     * Creates a VBox object representing a keyboard. All keys will be buttons and will interact with the user's inputs.
     *
     * @return The keyboard, a VBox.
     */
    public static VBox createKeyboard() {
        VBox keyboard = new VBox();
        keyboard.setPadding(keyboardInsets);
        keyboard.setSpacing(6);
        keyboard.setAlignment(Pos.CENTER);

        // Populate the keyMap with buttons
        registerKeys();

        // Rows 1 & 2 layout
        for (int i = 0; i < 2; i++) {
            HBox row = createCenteredRow();
            for (char c : alphabetKeysPerRow[i].toCharArray()) {
                row.getChildren().add(keyMap.get(String.valueOf(c)));
            }
            keyboard.getChildren().add(row);
        }

        // Row 3 layout
        HBox row3 = createCenteredRow();
        row3.getChildren().add(keyMap.get("LSHIFT"));
        for (char c : alphabetKeysPerRow[2].toCharArray()) {
            row3.getChildren().add(keyMap.get(String.valueOf(c)));
        }
        row3.getChildren().add(keyMap.get("RSHIFT"));

        // Row 4 layout
        HBox row4 = createCenteredRow();
        row4.getChildren().add(keyMap.get("SPACE"));

        // Row 5 layout
        HBox row5 = createCenteredRow();
        for (String keyName : punctuationKeyCodes) {
            row5.getChildren().add(keyMap.get(keyName));
        }

        HBox spacer = new HBox();

        keyboard.getChildren().addAll(row3, row4, spacer, row5);

        for (Button button : keyMap.values()) {
            button.setId("keyboard-button");
        }

        return keyboard;
    }

    public enum Case {
        UPPERCASE, LOWERCASE
    }
}
