package model.pieces;

import model.*;
import model.Color;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Player player, Color color, Point currentPosition) {
        super(Type.BISHOP, player, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/resources/imgBlackBishop");
        } else {
            setImage("/resources/imgWhiteBishop");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.bishopRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
