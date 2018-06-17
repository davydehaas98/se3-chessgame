package chessgameshared.interfaces;

public interface IWebSocket {
    void setMessageHandler(IMessageProcessor handler);

    void start();

    void stop();
}
