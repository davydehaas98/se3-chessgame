package model.pieces;

import model.Color;
import model.Tile;
import model.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public final class Rules {
    public static ArrayList<Point> pawnRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        switch (piece.getColor()) {
            case BLACK:
                //Vertical
                if (board[x][y + 1].getPiece() == null) {
                    moves.add(new Point(x, y + 1));
                    if (!piece.hasMoved()) {
                        moves.add(new Point(x, y + 2));
                    }
                }
                //Diagonal
                if (board[x + 1][y + 1].getPiece().getColor() == Color.WHITE) {
                    moves.add(new Point(x + 1, y + 1));
                }
                if (board[x - 1][y + 1].getPiece().getColor() == Color.WHITE) {
                    moves.add(new Point(x - 1, y + 1));
                }
            default:
                //Vertical
                if (board[x][y - 1].getPiece() == null) {
                    moves.add(new Point(x, y - 1));
                    if (!piece.hasMoved()) {
                        moves.add(new Point(x, y - 2));
                    }
                }
                //Diagonal
                if (board[x + 1][y - 1].getPiece().getColor() == Color.BLACK) {
                    moves.add(new Point(x + 1, y - 1));
                }
                if (board[x - 1][y - 1].getPiece().getColor() == Color.BLACK) {
                    moves.add(new Point(x - 1, y - 1));
                }
        }
        return moves;
    }

    public static ArrayList<Point> rookRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        //Vertical Down
        for (int down = y + 1; down < 8; down++) {
            if (board[x][down].getPiece() == null) {
                moves.add(new Point(x, down));
            } else if (board[x][down].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(x, down));
                break;
            } else {
                break;
            }
        }
        //Vertical Up
        for (int up = y - 1; up > -1; up--) {
            if (board[x][up].getPiece() == null) {
                moves.add(new Point(x, up));
            } else if (board[x][up].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(x, up));
                break;
            } else {
                break;
            }
        }
        //Horizontal Right
        for (int right = x + 1; right < 8; right++) {
            if (board[right][y].getPiece() == null) {
                moves.add(new Point(right, y));
            } else if (board[right][y].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(right, y));
                break;
            } else {
                break;
            }
        }
        //Horizontal Left
        for (int left = x - 1; left > -1; left--) {
            if (board[left][y].getPiece() == null) {
                moves.add(new Point(left, y));
            } else if (board[left][y].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(left, y));
                break;
            } else {
                break;
            }
        }
        return moves;
    }

    public static ArrayList<Point> knightRules(Tile[][] board, Piece piece) {
        return null;
    }

    public static ArrayList<Point> bishopRules(Tile[][] board, Piece piece){
        return null;
    }

    public static ArrayList<Point> queenRules(Tile[][] board, Piece piece){
        ArrayList<Point> moves = rookRules(board,piece);
        moves.addAll(bishopRules(board,piece));
        return moves;
    }

    public static ArrayList<Point> kingRules(Tile[][] board, Piece piece){
        return null;
    }
}
