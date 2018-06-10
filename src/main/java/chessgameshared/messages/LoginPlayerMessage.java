package chessgameshared.messages;

public class LoginPlayerMessage {
    private String name;

    public LoginPlayerMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
