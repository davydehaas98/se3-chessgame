package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.Type;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Color color, Point currentPosition) {
        super(Type.ROOK,color,currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackRook.png");
        } else {
            setImage("/images/WhiteRook.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.rookRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}