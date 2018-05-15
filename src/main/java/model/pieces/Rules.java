package model.pieces;

import model.enums.Color;
import model.Tile;

import java.awt.*;
import java.util.ArrayList;

final class Rules {
    static ArrayList<Point> pawnRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<>();
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
                try {
                    if (board[x + 1][y + 1].getPiece().getColor() == Color.WHITE) {
                        moves.add(new Point(x + 1, y + 1));
                    }
                } catch (Exception exc) {
                }
                try {
                    if (board[x - 1][y + 1].getPiece().getColor() == Color.WHITE) {
                        moves.add(new Point(x - 1, y + 1));
                    }
                } catch (Exception exc) {
                    return moves;
                }
            case WHITE:
                //Vertical
                if (board[x][y - 1].getPiece() == null) {
                    moves.add(new Point(x, y - 1));
                    if (!piece.hasMoved()) {
                        moves.add(new Point(x, y - 2));
                    }
                }
                //Diagonal
                try {
                    if (board[x + 1][y - 1].getPiece().getColor() == Color.BLACK) {
                        moves.add(new Point(x + 1, y - 1));
                    }
                } catch (Exception exc) {
                }
                try {
                    if (board[x - 1][y - 1] != null && board[x - 1][y - 1].getPiece().getColor() == Color.BLACK) {
                        moves.add(new Point(x - 1, y - 1));
                    }
                } catch (Exception exc) {
                }
        }
        return moves;
    }

    static ArrayList<Point> rookRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        //Vertical Down
        for (int row = y + 1; row < 8; row++) {
            if (board[x][row].getPiece() == null) {
                moves.add(new Point(x, row));
            } else if (board[x][row].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(x, row));
                break;
            } else {
                break;
            }
        }
        //Vertical Up
        for (int row = y - 1; row > -1; row--) {
            if (board[x][row].getPiece() == null) {
                moves.add(new Point(x, row));
            } else if (board[x][row].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(x, row));
                break;
            } else {
                break;
            }
        }
        //Horizontal Right
        for (int column = x + 1; column < 8; column++) {
            if (board[column][y].getPiece() == null) {
                moves.add(new Point(column, y));
            } else if (board[column][y].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(column, y));
                break;
            } else {
                break;
            }
        }
        //Horizontal Left
        for (int column = x - 1; column > -1; column--) {
            if (board[column][y].getPiece() == null) {
                moves.add(new Point(column, y));
            } else if (board[column][y].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(column, y));
                break;
            } else {
                break;
            }
        }
        return moves;
    }

    static ArrayList<Point> knightRules(Tile[][] board, Piece piece) {
        int[][] offsets = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        return offsetsIterator(offsets, board, piece);
    }

    static ArrayList<Point> bishopRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<Point>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        //Diagonal LeftUp
        for (int row = x - 1, column = y - 1; row > -1 && column > -1; row--, column--) {
            if (board[row][column].getPiece() == null) {
                moves.add(new Point(row, column));
            } else if (board[row][column].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(row, column));
                break;
            } else {
                break;
            }
        }
        //Diagonal LeftDown
        for (int row = x - 1, column = y + 1; row > -1 && column < 8; row--, column++) {
            if (board[row][column].getPiece() == null) {
                moves.add(new Point(row, column));
            } else if (board[row][column].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(row, column));
                break;
            } else {
                break;
            }
        }
        //Diagonal RightUp
        for (int row = x + 1, colomn = y - 1; row < 8 && colomn > -1; row++, colomn--) {
            if (board[row][colomn].getPiece() == null) {
                moves.add(new Point(row, colomn));
            } else if (board[row][colomn].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(row, colomn));
                break;
            } else {
                break;
            }
        }
        //Diagonal RightDown
        for (int row = x + 1, column = y + 1; row < 8 && column < 8; row++, column++) {
            if (board[row][column].getPiece() == null) {
                moves.add(new Point(row, column));
            } else if (board[row][column].getPiece().getColor() != piece.getColor()) {
                moves.add(new Point(row, column));
                break;
            } else {
                break;
            }
        }
        return moves;
    }

    static ArrayList<Point> queenRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = rookRules(board, piece);
        moves.addAll(bishopRules(board, piece));
        return moves;
    }

    static ArrayList<Point> kingRules(Tile[][] board, Piece piece) {
        int[][] offsets = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, +1}, {0, +1}, {-1, +1}, {-1, 0}};
        return offsetsIterator(offsets, board, piece);
    }

    private static ArrayList<Point> offsetsIterator(int[][] offsets, Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        for (int[] offset : offsets) {
            try {
                Tile tile = board[(x + offset[0])][(y + offset[1])];
                if (tile.getPiece() == null || tile.getPiece().getColor() != piece.getColor()) {
                    moves.add(tile.getPosition());
                }
            } catch (Exception exc) {

            }
        }
        return moves;
    }
}
