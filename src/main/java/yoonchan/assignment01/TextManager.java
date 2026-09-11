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

    /**
     * Retrieves the next text from texts by id, if there is one. Sets the current text index to match.
     *
     * @return The next text from texts.
     */
    public static String cycleToNextText() {
        if (currentTextIndex + 1 >= texts.length) {
            return texts[currentTextIndex];
        }

        return texts[++currentTextIndex];
    }

    /**
     * Retrieves the previous text from texts by id, if there is one. Sets the current text index to match.
     *
     * @return The previous text from texts.
     */
    public static String getPreviousText() {
        if (currentTextIndex - 1 <= 0) {
            return texts[currentTextIndex];
        }

        return texts[--currentTextIndex];
    }

    /**
     * Retrieves the first text from texts by id. Sets the current text index to match.
     *
     * @return The first text from texts.
     */
    public static String cycleToFirstText() {
        currentTextIndex = 0;
        return texts[0];
    }
}
