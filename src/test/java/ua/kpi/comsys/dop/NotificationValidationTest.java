package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NotificationValidationTest {

    @Nested
    class TextMsgValidation {

        @Test
        void acceptsValidData() {
            assertDoesNotThrow(() -> new TextMsg("Alice", "Hi"));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsBlankSender(String sender) {
            assertThrows(IllegalArgumentException.class, () -> new TextMsg(sender, "Hi"));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsBlankText(String text) {
            assertThrows(IllegalArgumentException.class, () -> new TextMsg("Alice", text));
        }
    }

    @Nested
    class ImageMsgValidation {

        @Test
        void acceptsValidData() {
            assertDoesNotThrow(() -> new ImageMsg("Bob", "http://x/y.png", 1));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsBlankSender(String sender) {
            assertThrows(IllegalArgumentException.class, () -> new ImageMsg(sender, "http://x/y.png", 1));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsBlankUrl(String url) {
            assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Bob", url, 1));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
        void rejectsNonPositiveSize(int sizeKb) {
            assertThrows(IllegalArgumentException.class, () -> new ImageMsg("Bob", "http://x/y.png", sizeKb));
        }
    }

    @Nested
    class AudioMsgValidation {

        @Test
        void acceptsValidData() {
            assertDoesNotThrow(() -> new AudioMsg("Carol", 1));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "   ", "\t", "\n"})
        void rejectsBlankSender(String sender) {
            assertThrows(IllegalArgumentException.class, () -> new AudioMsg(sender, 10));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
        void rejectsNonPositiveDuration(int durationSeconds) {
            assertThrows(IllegalArgumentException.class, () -> new AudioMsg("Carol", durationSeconds));
        }
    }
}
