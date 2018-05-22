package chessgameshared.messages;

public class MakeMoveMessage {
    private String from;
    private String to;
    public MakeMoveMessage(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
