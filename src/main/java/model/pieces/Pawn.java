package model.pieces;

import model.*;
import model.enums.Color;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn( Color color, Point currentPosition) {
        super(PieceType.PAWN, color, currentPosition);
        if (getColor() == Color.BLACK) {
            setImage("/images/BlackPawn.png");
        } else if (getColor() == Color.WHITE){
            setImage("/images/WhitePawn.png");
        }
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.pawnRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}