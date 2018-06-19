package chessgameclient.interfaces;

import model.pieces.Piece;

import java.awt.*;

public interface IClientMessageGenerator {
    void registerPlayer(String name, String hash);

    void requestPassword(String name);

    void loginPlayer(String name, String password);

    void requestLegalMoves(Piece piece);

    void makeMove(Point from, Point to);

    void logoutPlayer();
}
