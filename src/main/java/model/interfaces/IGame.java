package model.interfaces;

import model.Tile;
import model.pieces.Piece;

public interface IGame {
    void processClientDisconnect(String sessionId);
    void registerNewPlayer(String name, String sessionId);
    void startGame();
    Piece getChessPiece(int row, int column);
    Tile[][] getBoard();
}
