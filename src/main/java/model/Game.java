package model;

import model.*;
import model.pieces.*;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private Tile[][] board;
    private Player blackPlayer;
    private Player whitePlayer;
    private int turn;
    private Player turnPlayer;
    private ArrayList<Event> events;

    void startGame() {
        setBoard();
        while (!isCheckmate() || turn < 10){

        }
        endGame();
    }
    private void endGame(){

    }
    private boolean isCheck(){
        return false;
    }
    private boolean isCheckmate(){
        return false;
    }
    private void setBoard() {
        board = new Tile[8][8];
        String files = "abcdefgh";
        //Set Tiles
        for (int rank = 7; rank > -1; rank--) {
            for (int file = 0; file < 8; file++) {
                board[rank][file].setPosition(new Point(rank, file));
                board[rank][file].setName(String.format("%s%d", files.charAt(file), rank + 1));
            }
        }
        //Set Pawns
        for (int x = 0; x < 8; x++) {
            board[x][1].placePiece(new Pawn(blackPlayer, Color.BLACK, new Point(x, 1)));
            board[x][6].placePiece(new Pawn(whitePlayer, Color.WHITE, new Point(x, 6)));
        }
        //Set Black Pieces
        board[0][0].placePiece(new Rook(blackPlayer, Color.BLACK, new Point(0, 0)));
        board[1][0].placePiece(new Knight(blackPlayer, Color.BLACK, new Point(1, 0)));
        board[2][0].placePiece(new Bishop(blackPlayer, Color.BLACK, new Point(2, 0)));
        board[3][0].placePiece(new Queen(blackPlayer, Color.BLACK, new Point(3, 0)));
        board[4][0].placePiece(new King(blackPlayer, Color.BLACK, new Point(4, 0)));
        board[5][0].placePiece(new Bishop(blackPlayer, Color.BLACK, new Point(5, 0)));
        board[6][0].placePiece(new Knight(blackPlayer, Color.BLACK, new Point(6, 0)));
        board[7][0].placePiece(new Rook(blackPlayer, Color.BLACK, new Point(7, 0)));
        //Set White Pieces
        board[0][7].placePiece(new Rook(whitePlayer, Color.WHITE, new Point(0, 7)));
        board[1][7].placePiece(new Knight(whitePlayer, Color.WHITE, new Point(1, 7)));
        board[2][7].placePiece(new Bishop(whitePlayer, Color.WHITE, new Point(2, 7)));
        board[3][7].placePiece(new Queen(whitePlayer, Color.WHITE, new Point(3, 7)));
        board[4][7].placePiece(new King(whitePlayer, Color.WHITE, new Point(4, 7)));
        board[5][7].placePiece(new Bishop(whitePlayer, Color.WHITE, new Point(5, 7)));
        board[6][7].placePiece(new Knight(whitePlayer, Color.WHITE, new Point(6, 7)));
        board[7][7].placePiece(new Rook(whitePlayer, Color.WHITE, new Point(7, 7)));
    }
}