package chessgameshared.messages;

public class RegisterPlayerMessage {
    private String name;
    private String password;

    public RegisterPlayerMessage(String name, String password) {
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
