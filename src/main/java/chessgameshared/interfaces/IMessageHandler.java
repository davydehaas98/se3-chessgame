package chessgameshared.interfaces;

public interface IMessageHandler {
    void handleMessage(String message, String sessionId);
}
