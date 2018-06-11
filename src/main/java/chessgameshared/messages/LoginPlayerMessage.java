package chessgameshared.messages;

public class LoginPlayerMessage {
    private String name;
    private String password;

    public LoginPlayerMessage(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
