package chessgameshared.messages;

public class RegisterPlayerMessage {
    private String name;
    public RegisterPlayerMessage(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
