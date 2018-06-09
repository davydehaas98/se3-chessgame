package chessgameclient.interfaces;

public interface IClientMessageGenerator {
    void registerPlayer(String name, String hash);
    void makeMove(String from, String to);
    void playerDisconnect();
}
