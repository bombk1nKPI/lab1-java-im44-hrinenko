package ua.kpi.comsys.dop;

public record TextMsg(String sender, String text) implements Notification {

    public TextMsg {
        Validation.requireNonBlank(sender, "sender");
        Validation.requireNonBlank(text, "text");
    }
}
