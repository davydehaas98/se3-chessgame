package model;

import model.pieces.Piece;

import java.awt.*;

public class Tile {
    private String name;
    private Point position;
    private Piece piece;

    Tile(String name, Point position, Piece piece) {
        this.name = name;
        this.position = position;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void placePiece(Piece piece) {
        piece.addMoveToHistory(this.position);
        piece.setCurrentPosition(this.position);
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }
}