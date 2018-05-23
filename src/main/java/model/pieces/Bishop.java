package model.pieces;

import model.*;
import model.enums.TeamColor;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(TeamColor teamColor, Point currentPosition) {
        super(PieceType.BISHOP, teamColor, currentPosition);
        setImage(String.format("/images/%sBishop.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.bishopRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
