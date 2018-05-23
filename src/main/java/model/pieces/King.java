package model.pieces;

import model.*;
import model.enums.TeamColor;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King(TeamColor teamColor, Point currentPosition) {
        super(PieceType.KING, teamColor, currentPosition);
        setImage(String.format("/images/%sKing.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        ArrayList<Point> moves = Rules.kingRules(board, this);
        setLegalMoves(moves);
        return moves;
    }
}

