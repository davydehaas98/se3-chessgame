package chessgameshared.messages;

public class RequestPasswordMessage {
    private String name;

    public RequestPasswordMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
