package model.pieces;

import model.enums.TeamColor;
import model.Tile;
import model.enums.PieceType;
import java.awt.*;
import java.util.ArrayList;

final class Rules {
    static ArrayList<Point> pawnRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        switch (piece.getTeamColor()) {
            case BLACK:
                //Vertical
                if (board[x][y + 1].getPiece() == null) {
                    moves.add(new Point(x, y + 1));
                    //Can make two moves at once if the piece has not yet been moved
                    if (!piece.hasMoved()) {
                        moves.add(new Point(x, y + 2));
                    }
                }
                //Diagonal
                if (x < 7 && board[x + 1][y + 1].getPiece() != null && board[x + 1][y + 1].getPiece().getTeamColor() == TeamColor.WHITE) {
                    moves.add(new Point(x + 1, y + 1));
                }
                if (x > 0 && board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getTeamColor() == TeamColor.WHITE) {
                    moves.add(new Point(x - 1, y + 1));
                }
                break;
            case WHITE:
                //Vertical
                if (board[x][y - 1].getPiece() == null) {
                    moves.add(new Point(x, y - 1));
                    //Can make two moves at once if the piece has not yet been moved
                    if (!piece.hasMoved()) {
                        moves.add(new Point(x, y - 2));
                    }
                }
                //Diagonal
                if (x < 7 && board[x + 1][y - 1].getPiece() != null && board[x + 1][y - 1].getPiece().getTeamColor() == TeamColor.BLACK) {
                    moves.add(new Point(x + 1, y - 1));
                }
                if (x > 0 && board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getTeamColor() == TeamColor.BLACK) {
                    moves.add(new Point(x - 1, y - 1));
                }
                break;
        }
//        //En Passant
//        Piece targetedPiece;
//        if (x > 0) {
//            targetedPiece = board[x - 1][y].getPiece();
//            if (targetedPiece != null && !targetedPiece.getTeamColor().equals(piece.getTeamColor()) && targetedPiece.getPieceType().equals(PieceType.PAWN) && targetedPiece.getMoveHistory().size() == 1) {
//                moves.add(new Point(x - 1, y - 1));
//            }
//        }
//        if (x < 7) {
//            targetedPiece = board[x + 1][y].getPiece();
//            if (targetedPiece != null && !targetedPiece.getTeamColor().equals(piece.getTeamColor()) && targetedPiece.getPieceType().equals(PieceType.PAWN) && targetedPiece.getMoveHistory().size() == 1) {
//                moves.add(new Point(x + 1, y - 1));
//            }
//        }
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
            } else if (!board[x][row].getPiece().getTeamColor().equals(piece.getTeamColor())) {
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
            } else if (!board[x][row].getPiece().getTeamColor().equals(piece.getTeamColor())) {
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
            } else if (board[column][y].getPiece().getTeamColor() != piece.getTeamColor()) {
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
            } else if (board[column][y].getPiece().getTeamColor() != piece.getTeamColor()) {
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
        return offsetsIterator(board, piece, offsets);
    }

    static ArrayList<Point> bishopRules(Tile[][] board, Piece piece) {
        ArrayList<Point> moves = new ArrayList<>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        //Diagonal LeftUp
        for (int row = x - 1, column = y - 1; row > -1 && column > -1; row--, column--) {
            if (board[row][column].getPiece() == null) {
                moves.add(new Point(row, column));
            } else if (!board[row][column].getPiece().getTeamColor().equals(piece.getTeamColor())) {
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
            } else if (!board[row][column].getPiece().getTeamColor().equals(piece.getTeamColor())) {
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
            } else if (!board[row][colomn].getPiece().getTeamColor().equals(piece.getTeamColor())) {
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
            } else if (!board[row][column].getPiece().getTeamColor().equals(piece.getTeamColor())) {
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
        ArrayList<Point> legalMoves = offsetsIterator(board, piece, offsets);
        ArrayList<Point> legalMovesToCheck = legalMoves;
        //Check if the move checks the King
        legalMovesToCheck.forEach(checkingMove -> {
            //Pawn legal moves aren't generated when the tile is empty so we have to know if a Pawn can move there on the next turn
            int xcheck = checkingMove.x;
            int ycheck = checkingMove.y;
            switch (piece.getTeamColor()) {
                //Remove the currently checked move from the legal moves list if a Pawn can attack the tile the next turn
                case BLACK:
                    if (xcheck > 0 && board[xcheck - 1][ycheck + 1].getPiece() != null && board[xcheck - 1][ycheck + 1].getPiece().getPieceType().equals(PieceType.PAWN)) {
                        legalMoves.remove(checkingMove);
                        break;
                    } else if (xcheck < 7 && board[xcheck + 1][ycheck + 1].getPiece() != null && board[xcheck + 1][ycheck + 1].getPiece().getPieceType().equals(PieceType.PAWN)) {
                        legalMoves.remove(checkingMove);
                        break;
                    }
                    break;
                case WHITE:
                    if (xcheck > 0 && board[xcheck - 1][ycheck - 1].getPiece() != null && board[xcheck - 1][ycheck - 1].getPiece().getPieceType().equals(PieceType.PAWN)) {
                        legalMoves.remove(checkingMove);
                        break;
                    } else if (xcheck < 7 && board[xcheck + 1][ycheck - 1].getPiece() != null && board[xcheck + 1][ycheck - 1].getPiece().getPieceType().equals(PieceType.PAWN)) {
                        legalMoves.remove(checkingMove);
                        break;
                    }
                    break;
            }
            //Check if the move currently checked is still in the legal moves list
            if (legalMoves.contains(checkingMove)) {
                for (Tile[] tileRow : board) {
                    for (Tile tile : tileRow) {
                        Piece pieceOnTile = tile.getPiece();
                        if (pieceOnTile != null && !pieceOnTile.getPieceType().equals(PieceType.PAWN) && !pieceOnTile.getPieceType().equals(PieceType.KING)) {
                            if (!pieceOnTile.getTeamColor().equals(piece.getTeamColor()) && pieceOnTile.getLegalMoves(board).contains(checkingMove)) {
                                legalMoves.remove(checkingMove);
                                break;
                            }
                        }
                    }
                }
            }
        });

        return legalMoves;
    }

    private static ArrayList<Point> offsetsIterator(Tile[][] board, Piece piece, int[][] offsets) {
        ArrayList<Point> moves = new ArrayList<>();
        int x = piece.getCurrentPosition().x;
        int y = piece.getCurrentPosition().y;
        for (int[] offset : offsets) {
            if (x + offset[0] > -1 && x + offset[0] < 8 && y + offset[1] > -1 && y + offset[1] < 8) {
                Tile selectedTile = board[(x + offset[0])][(y + offset[1])];
                if (selectedTile.getPiece() == null || selectedTile.getPiece().getTeamColor() != piece.getTeamColor()) {
                    moves.add(selectedTile.getPosition());
                }
            }
        }
        return moves;
    }
}
