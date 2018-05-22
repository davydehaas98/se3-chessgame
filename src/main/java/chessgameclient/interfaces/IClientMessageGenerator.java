package chessgameclient.interfaces;

public interface IClientMessageGenerator {
    void registerPlayerOnServer(String name);
    void makeMove(String from, String to);
}
