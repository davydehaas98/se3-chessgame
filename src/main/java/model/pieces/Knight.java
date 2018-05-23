package model.pieces;

import model.*;
import model.enums.TeamColor;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(TeamColor teamColor, Point currentPosition) {
        super(PieceType.KNIGHT, teamColor, currentPosition);
        setImage(String.format("/images/%sKnight.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.knightRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}
