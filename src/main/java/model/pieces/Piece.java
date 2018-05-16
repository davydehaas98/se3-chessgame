package model.pieces;

import model.enums.Color;
import model.Player;
import model.Tile;
import model.enums.Type;

import java.awt.*;
import java.util.ArrayList;

public abstract class Piece {
    private final Type type;
    private final Color color;
    private Point currentPosition;
    private ArrayList<Point> moveHistory;
    private String image;
    private ArrayList<Point> legalMoves;

    public Piece(Type type, Color color, Point currentPosition) {
        this.type = type;
        this.color = color;
        this.currentPosition = currentPosition;
        this.moveHistory = new ArrayList<>();
        this.legalMoves = new ArrayList<>();
    }

    public Type getType() {
        return type;
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
