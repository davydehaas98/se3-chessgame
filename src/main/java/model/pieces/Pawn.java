package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.Type;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn( Color color, Point currentPosition) {
        super(Type.PAWN, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackPawn.png");
        } else {
            setImage("/images/WhitePawn.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.pawnRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}