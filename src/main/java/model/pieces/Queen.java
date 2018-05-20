package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Color color, Point currentPosition) {
        super(PieceType.QUEEN, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackQueen.png");
        } else {
            setImage("/images/WhiteQueen.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.queenRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
