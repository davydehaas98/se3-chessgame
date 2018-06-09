package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(TeamColor teamColor, Point currentPosition) {
        super(PieceType.BISHOP, teamColor, currentPosition);
        setImage(String.format("/images/%sBishop.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        return Rules.bishopRules(board, this);
    }
}
