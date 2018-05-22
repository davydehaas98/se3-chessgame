package chessgameshared.messages;

public class PlayerDisconnectMessage {
    private String sessionId;
    public PlayerDisconnectMessage(String sessionId){
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
