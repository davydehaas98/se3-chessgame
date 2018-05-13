package model.pieces;

import model.*;
import model.Color;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Player player, Color color, Point currentPosition) {
        super(Type.QUEEN, player, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/resources/imgBlackQueen");
        } else {
            setImage("/resources/imgWhiteQueen");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.queenRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
