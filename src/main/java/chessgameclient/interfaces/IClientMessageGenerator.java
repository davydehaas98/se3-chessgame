package chessgameclient.interfaces;

import java.awt.*;

public interface IClientMessageGenerator {
    void registerPlayer(String name, String hash);

    void requestPassword(String name);

    void loginPlayer(String name, String password);

    void makeMove(Point from, Point to);

    void logoutPlayer();
}
