package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public class King extends Piece {
    public King(TeamColor teamColor, Point currentPosition) {
        super(PieceType.KING, teamColor, currentPosition);
        setImage(String.format("/images/%sKing.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        return Rules.kingRules(board, this);
    }
}

