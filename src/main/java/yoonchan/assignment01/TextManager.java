package yoonchan.assignment01;

import lombok.Getter;

public class TextManager {
    @Getter
    private static final String[] texts = {
            "Try typing this text. Do it as quickly and accurately as you can.",
            "Next type another line of input data.",
            "The quick brown fox jumps over the lazy dog.",
            "Five big quacking zephyrs jolt my wax bed.",
            "Sympathizing would fix Quaker objectives.",
            "A large fawn jumped quickly over white zinc boxes."
    };

    @Getter
    private static int currentTextIndex = 0;

    @Getter
    private static int correctCount = 0;

    @Getter
    private static int incorrectCount = 0;

    /**
     * Evaluates new text input against the current target text.
     */
    public static void processInput(String oldText, String newText) {
        // Ignore backspaces or text clearing
        if (newText.length() <= oldText.length()) {
            return;
        }

        String target = texts[currentTextIndex];

        for (int i = oldText.length(); i < newText.length(); i++) {
            if (i < target.length() && newText.charAt(i) == target.charAt(i)) {
                correctCount++;
            } else {
                incorrectCount++;
            }
        }
    }

    /**
     * Resets the typing statistics.
     */
    public static void resetStats() {
        correctCount = 0;
        incorrectCount = 0;
    }

    /**
     * Returns the currently active text string.
     */
    public static String getCurrentText() {
        return texts[currentTextIndex];
    }

    /**
     * Retrieves the next text from texts by id, if there is one. Sets the current text index to match.
     */
    public static String cycleToNextText() {
        if (currentTextIndex + 1 >= texts.length) {
            return texts[currentTextIndex];
        }
        return texts[++currentTextIndex];
    }

    /**
     * Retrieves the first text from texts by id. Sets the current text index to match.
     */
    public static String cycleToFirstText() {
        currentTextIndex = 0;
        return texts[0];
    }
}
