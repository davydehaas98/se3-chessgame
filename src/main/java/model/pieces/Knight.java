package model.pieces;

import model.*;
import model.Color;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Player player, Color color, Point currentPosition) {
        super(Type.KNIGHT, player, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/resources/imgBlackKnight");
        } else {
            setImage("/resources/imgWhiteKnight");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.knightRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
