package model.interfaces;

import model.Tile;
import model.pieces.Piece;

public interface IGame {
    void registerNewPlayer(String name, String sessionId);
    void makeMove(String from, String to);
    void startGame();
    void processPlayerDisconnect(String sessionId);
    int getNumberOfPlayers();
    Piece getChessPiece(int row, int column);
    Tile[][] getBoard();
}
