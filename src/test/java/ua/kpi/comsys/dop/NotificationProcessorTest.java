package ua.kpi.comsys.dop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NotificationProcessorTest {

    private final NotificationProcessor processor = new NotificationProcessor();

    @Test
    void processesTextMessage() {
        String expected = """
                Notification: TEXT
                From: Alice
                Message: Hello, world""";

        assertEquals(expected, processor.process(new TextMsg("Alice", "Hello, world")));
    }

    @Test
    void processesImageMessage() {
        String expected = """
                Notification: IMAGE
                From: Bob
                URL: http://img.example/cat.png (512 KB)""";

        assertEquals(expected, processor.process(new ImageMsg("Bob", "http://img.example/cat.png", 512)));
    }

    @Test
    void processesAudioMessage() {
        String expected = """
                Notification: AUDIO
                From: Carol
                Duration: 42 seconds""";

        assertEquals(expected, processor.process(new AudioMsg("Carol", 42)));
    }

    @Test
    void rejectsNullNotification() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> processor.process(null));

        assertEquals("Notification cannot be null", ex.getMessage());
    }
}
