package model.pieces;

import model.*;
import model.enums.TeamColor;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(TeamColor teamColor, Point currentPosition) {
        super(PieceType.PAWN, teamColor, currentPosition);
        setImage(String.format("/images/%sPawn.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.pawnRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}