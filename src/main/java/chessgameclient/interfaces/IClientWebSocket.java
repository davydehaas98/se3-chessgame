package chessgameclient.interfaces;

public interface IClientWebSocket {
    void setMessageHandler(IClientMessageProcessor handler);

    void start();

    void stop();

    void onMessageReceived(String message, String sessionId);

    void onMessageSend(Object object);
}
