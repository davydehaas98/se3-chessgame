package model.pieces;

import model.*;
import model.Color;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Player player, Color color, Point currentPosition) {
        super(Type.ROOK,player,color,currentPosition);
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