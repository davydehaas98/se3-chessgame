package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(TeamColor teamColor, Point currentPosition) {
        super(PieceType.ROOK, teamColor, currentPosition);
        setImage(String.format("/images/%sRook.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        return Rules.rookRules(board, this);
    }
}