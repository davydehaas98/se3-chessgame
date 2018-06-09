package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(TeamColor teamColor, Point currentPosition) {
        super(PieceType.QUEEN, teamColor, currentPosition);
        setImage(String.format("/images/%sQueen.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        return Rules.queenRules(board, this);
    }
}
