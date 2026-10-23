package ua.kpi.comsys.dop;

public class NotificationProcessor {

    public String process(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }
        return switch (notification) {
            case TextMsg t -> """
                    Notification: TEXT
                    From: %s
                    Message: %s""".formatted(t.sender(), t.text());
            case ImageMsg i -> """
                    Notification: IMAGE
                    From: %s
                    URL: %s (%d KB)""".formatted(i.sender(), i.url(), i.sizeKb());
            case AudioMsg a -> """
                    Notification: AUDIO
                    From: %s
                    Duration: %d seconds""".formatted(a.sender(), a.durationSeconds());
        };
    }
}
