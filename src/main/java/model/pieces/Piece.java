package model.pieces;

import model.Tile;
import model.enums.PieceType;
import model.enums.TeamColor;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private Point currentPosition;
    private ArrayList<Point> moveHistory;
    private String image;
    private ArrayList<Point> legalMoves;

    public Piece(PieceType pieceType, TeamColor teamColor, Point currentPosition) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.currentPosition = currentPosition;
        this.moveHistory = new ArrayList<>();
        this.legalMoves = new ArrayList<>();
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

    public String getImage() {
        return image;
    }

    public void setCurrentPosition(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    void setImage(String image) {
        this.image = image;
    }

    public boolean hasMoved() {
        return moveHistory.size() > 1;
    }

    public void addMoveToHistory(Point point) {
        moveHistory.add(point);
    }

    public ArrayList<Point> getMoveHistory() {
        return moveHistory;
    }

    public abstract ArrayList<Point> getLegalMoves(Tile[][] board);
}
