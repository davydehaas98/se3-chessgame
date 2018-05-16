package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.Type;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop( Color color, Point currentPosition) {
        super(Type.BISHOP, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackBishop.png");
        } else {
            setImage("/images/WhiteBishop.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.bishopRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
