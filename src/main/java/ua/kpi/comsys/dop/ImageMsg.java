package ua.kpi.comsys.dop;

public record ImageMsg(String sender, String url, int sizeKb) implements Notification {

    public ImageMsg {
        Validation.requireNonBlank(sender, "sender");
        Validation.requireNonBlank(url, "url");
        Validation.requirePositive(sizeKb, "sizeKb");
    }
}
