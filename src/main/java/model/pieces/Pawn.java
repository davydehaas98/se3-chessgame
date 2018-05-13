package model.pieces;

import model.*;
import model.Color;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Player player, Color color, Point currentPosition) {
        super(Type.PAWN, player, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/resources/imgBlackPawn");
        } else {
            setImage("/resources/imgWhitePawn");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.pawnRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}