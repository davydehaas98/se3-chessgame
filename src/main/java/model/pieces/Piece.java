package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece implements Cloneable{
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private Point currentPosition;
    private ArrayList<Point> moveHistory;
    private String image;

    public Piece(PieceType pieceType, TeamColor teamColor, Point currentPosition) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.currentPosition = currentPosition;
        this.moveHistory = new ArrayList<>();
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        }catch (Exception exc){
            return null;
        }
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getImage() {
        return image;
    }

    void setImage(String image) {
        this.image = image;
    }

    public void addMoveToHistory(Point point) {
        moveHistory.add(point);
    }

    public ArrayList<Point> getMoveHistory() {
        return moveHistory;
    }

    public abstract ArrayList<Point> getLegalMoves(Tile[][] board);
}
