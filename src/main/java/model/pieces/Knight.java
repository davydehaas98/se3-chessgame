package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Color color, Point currentPosition) {
        super(PieceType.KNIGHT, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackKnight.png");
        } else {
            setImage("/images/WhiteKnight.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.knightRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
