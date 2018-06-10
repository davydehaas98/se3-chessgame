package chessgameclient.interfaces;

public interface IClientMessageGenerator {
    void registerPlayer(String name, String hash);
    void requestPassword(String name);
    void loginPlayer(String name);
    void makeMove(String from, String to);
    void playerDisconnect();
}
