package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(TeamColor teamColor, Point currentPosition) {
        super(PieceType.KNIGHT, teamColor, currentPosition);
        setImage(String.format("/images/%sKnight.png", teamColor));
    }

    public ArrayList<Point> getLegalMoves(Tile[][] board) {
        return Rules.knightRules(board, this);
    }
}
