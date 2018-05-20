package chessgameshared.messages;

public class AnotherPlayerRegisteredMessage {
    private String name;
    public AnotherPlayerRegisteredMessage(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
