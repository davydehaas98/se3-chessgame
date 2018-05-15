package model;

import chessgameserver.IServerMessageGenerator;
import model.enums.Color;
import model.enums.GameState;
import model.pieces.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game implements IGame, Observer {
    private ArrayList<IPlayer> players = new ArrayList<>();
    private IServerMessageGenerator messageGenerator;
    private GameState gameState = GameState.WAITINGFORPLAYERS;
    private int turn = 0;

    private Tile[][] board;
    private ArrayList<Event> events;

    public Game(IServerMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void registerNewPlayer(String name, String sessionId){
        if(players.size() < 2){
            if(checkPlayerNameAlreadyExists(name)){
                messageGenerator.notifyRegisterResult(sessionId,false);
                return;
            }
            players.add(new Player(name,sessionId));
        }

    }
    private boolean checkPlayerNameAlreadyExists(String name) {
        for (IPlayer player : players)
            if (player.getName().equals(name)) {
                return true;
            }
        return false;
    }
    public void processClientDisconnect(String sessionId)
    {
        for(IPlayer pl : players)
            if(pl.getSessionId().equals(sessionId)) {
                players.remove(pl);
            }

        if(gameState != GameState.WAITINGFORPLAYERS)
        {
            gameState = GameState.WAITINGFORPLAYERS;
        }
    }
    private void checkStartingCondition(){
        if(players.size() == 2){
            startGame();
        }
    }
    public void startGame() {
        new Player("Black", model.enums.Color.BLACK);
        new Player("White", model.enums.Color.WHITE);
        turn = 1;
        setBoard();
//        while (!isCheckmate() || turn < 10){
//
//        }
//        endGame();
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
        for (int file = 0; file < 8; file++) {
            for (int rank = 0; rank < 8; rank++) {
                board[file][rank] = new Tile(String.format("%s%d", files.charAt(file), 8 - rank), new Point(file, rank), null);
            }
        }
        //Set Pawns
        for (int x = 0; x < 8; x++) {
            //board[x][1].placePiece(new Pawn(blackPlayer, model.enums.Color.BLACK, new Point(x, 1)));
            //board[x][6].placePiece(new Pawn(whitePlayer, Color.WHITE, new Point(x, 6)));
        }
        //Set Black Pieces
        //setPiecesOnBoard(blackPlayer, model.enums.Color.BLACK, 0);
        //Set White Pieces
        //setPiecesOnBoard(whitePlayer, model.enums.Color.WHITE, 7);
    }
    private void setPiecesOnBoard(Player player, Color color, int y){
        board[0][y].placePiece(new Rook(player, color, new Point(0, y)));
        board[1][y].placePiece(new Knight(player, color, new Point(1, y)));
        board[2][y].placePiece(new Bishop(player, color, new Point(2, y)));
        board[3][y].placePiece(new Queen(player, color, new Point(3, y)));
        board[4][y].placePiece(new King(player, color, new Point(4, y)));
        board[5][y].placePiece(new Bishop(player, color, new Point(5, y)));
        board[6][y].placePiece(new Knight(player, color, new Point(6,y)));
        board[7][y].placePiece(new Rook(player, color, new Point(7, y)));
    }
    public Piece getChessPiece(int row, int column){
        return board[row][column].getPiece();
    }

    public Tile[][] getBoard() {
        return board;
    }
}