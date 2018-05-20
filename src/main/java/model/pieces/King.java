package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King(Color color, Point currentPosition) {
        super(PieceType.KING, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackKing.png");
        } else {
            setImage("/images/WhiteKing.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.kingRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}

