package model.pieces;

import model.*;
import model.enums.TeamColor;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(TeamColor teamColor, Point currentPosition) {
        super(PieceType.ROOK, teamColor,currentPosition);
        setImage(String.format("/images/%sRook.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.rookRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}