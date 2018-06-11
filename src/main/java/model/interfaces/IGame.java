package model.interfaces;

import model.Tile;
import model.pieces.Piece;

public interface IGame {
    void registerPlayer(String name, String password, String sessionId);

    void requestPassword(String name, String sessionId);

    void loginPlayer(String name, String password, String sessionId);

    void makeMove(String from, String to, String sessionId);

    void startGame();

    void processPlayerDisconnect(String sessionId);

    int getNumberOfPlayers();

    Piece getChessPiece(int row, int column);

    Tile[][] getBoard();
}
