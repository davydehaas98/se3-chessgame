package chessgameserver.interfaces;

public interface IServerMessageGenerator {
    void notifyRegisterResult(String sessionId, boolean succes);
    void notifyPlayerAdded(String sessionId, String name);
    void notifyStartGame();
}
