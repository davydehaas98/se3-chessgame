package model;

import model.pieces.Piece;

public interface IGame {
    void startGame();
    Piece getChessPiece(int row, int column);
    Tile[][] getBoard();
}
