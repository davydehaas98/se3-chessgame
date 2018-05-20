package model.pieces;

import model.enums.Color;
import model.Tile;
import model.enums.PieceType;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    private final PieceType pieceType;
    private final Color color;
    private Point currentPosition;
    private ArrayList<Point> moveHistory;
    private String image;
    private ArrayList<Point> legalMoves;

    public Piece(PieceType pieceType, Color color, Point currentPosition) {
        this.pieceType = pieceType;
        this.color = color;
        this.currentPosition = currentPosition;
        this.moveHistory = new ArrayList<>();
        this.legalMoves = new ArrayList<>();
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
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

    public void setLegalMoves(ArrayList<Point> legalMoves) {
        this.legalMoves = legalMoves;
    }

    public boolean hasMoved() {
        return moveHistory.size() > 1;
    }

    public void addMoveToHistory(Point point) {
        moveHistory.add(point);
    }

    public abstract ArrayList<Point> getLegalMoves(Tile[][] board);
}
