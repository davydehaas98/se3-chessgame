package chessgameclient.interfaces;

public interface IClientWebSocket {
    void start();
    void stop();
    void send(Object object);
    void setMessageHandler(IClientMessageProcessor handler);
    void received(String message, String sessionId);
}
