package chessgameclient.interfaces;

public interface IClientMessageProcessor {
    void registerGameClient(IGameClient gameClient);

    void processMessage(String sessionId, String type, String data);
}
