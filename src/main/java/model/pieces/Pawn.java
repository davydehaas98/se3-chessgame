package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(TeamColor teamColor, Point currentPosition) {
        super(PieceType.PAWN, teamColor, currentPosition);
        setImage(String.format("/images/%sPawn.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        return Rules.pawnRules(board, this);
    }
}