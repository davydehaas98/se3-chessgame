package model.pieces;

import model.*;
import model.enums.TeamColor;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(TeamColor teamColor, Point currentPosition) {
        super(PieceType.QUEEN, teamColor, currentPosition);
        setImage(String.format("/images/%sQueen.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.queenRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
