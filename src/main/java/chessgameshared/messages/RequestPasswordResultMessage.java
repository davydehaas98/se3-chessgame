package chessgameshared.messages;

public class RequestPasswordResultMessage {
    private String password;

    public RequestPasswordResultMessage(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
