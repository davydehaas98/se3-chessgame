package model.pieces;

import model.*;
import model.Color;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King(Player player, Color color, Point currentPosition) {
        super(Type.KING, player, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/resources/imgBlackKing");
        } else {
            setImage("/resources/imgWhiteKing");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.kingRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}

