package model;

import chessgameserver.interfaces.IServerMessageGenerator;
import model.enums.TeamColor;
import model.enums.GameState;
import model.enums.PieceType;
import model.interfaces.IGame;
import model.interfaces.IPlayer;
import model.pieces.*;
import java.awt.*;
import java.time.Instant;
import java.util.*;

public class Game implements IGame {
    //private HashMap<String, Player> players;
    private ArrayList<IPlayer> players;
    private IServerMessageGenerator messageGenerator;
    private GameState gameState = GameState.WAITINGFORPLAYERS;
    private int turn = 1;

    private Tile[][] board;
    private ArrayList<Event> events;

    public Game(IServerMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
        players = new ArrayList<>();
        //players = new HashMap<>();
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        o.deleteObserver(this);
//        gameState = GameState.ROUNDACTIVE;
//    }

    public int getNumberOfPlayers() {
        return players.size();
    }


    private boolean isCheck(Tile[][] board) {
        return false;
    }

    private boolean isCheckmate(Tile[][] board) {
//        for(Tile [] tilesRow : board){
//            for(Tile tile : tilesRow){
//                Piece piece = tile.getPiece();
//                if(piece.getPieceType() == PieceType.KING && piece.getTeamColor() == TeamColor.BLACK){
//                    return piece.getLegalMoves(board).size() < 1;
//                }
//            }
//        }
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
            board[x][1].placePiece(new Pawn(TeamColor.BLACK, new Point(x, 1)));
            board[x][6].placePiece(new Pawn(TeamColor.WHITE, new Point(x, 6)));
        }
        //Set Black Pieces
        setPiecesOnBoard(TeamColor.BLACK, 0);
        //Set White Pieces
        setPiecesOnBoard(TeamColor.WHITE, 7);
    }

    private void setPiecesOnBoard(TeamColor teamColor, int y) {
        board[0][y].placePiece(new Rook(teamColor, new Point(0, y)));
        board[1][y].placePiece(new Knight(teamColor, new Point(1, y)));
        board[2][y].placePiece(new Bishop(teamColor, new Point(2, y)));
        board[3][y].placePiece(new Queen(teamColor, new Point(3, y)));
        board[4][y].placePiece(new King(teamColor, new Point(4, y)));
        board[5][y].placePiece(new Bishop(teamColor, new Point(5, y)));
        board[6][y].placePiece(new Knight(teamColor, new Point(6, y)));
        board[7][y].placePiece(new Rook(teamColor, new Point(7, y)));
    }

    public Piece getChessPiece(int row, int column) {
        return board[row][column].getPiece();
    }

    public Tile[][] getBoard() {
        return board;
    }
    public void registerNewPlayer(String name, String sessionId) {
        if (players.size() < 2) {
            //TODO Database Connection
            if (checkPlayerNameAlreadyExists(name)) {
                messageGenerator.notifyRegistrationResult(sessionId, null);
                return;
            }
            if (players.size() < 1) {
                players.add(new Player(name, sessionId, TeamColor.WHITE, 0, 0, 0, 500));
                messageGenerator.notifyRegistrationResult(sessionId, TeamColor.WHITE);
                messageGenerator.notifyUpdateBoard(board);
            } else if (players.size() == 1) {
                players.add(new Player(name, sessionId, TeamColor.BLACK, 0,0,0,500));
                messageGenerator.notifyRegistrationResult(sessionId, TeamColor.BLACK);
            }
            messageGenerator.notifyPlayerAdded(sessionId, name);
            checkStartingCondition();
        } else {
            messageGenerator.notifyRegistrationResult(sessionId, null);
        }
    }

    private boolean checkPlayerNameAlreadyExists(String name) {
        for (IPlayer player : players)
            if (player.getName().equals(name)) {
                return true;
            }
        return false;
    }

    public void processPlayerDisconnect(String sessionId) {
        for (IPlayer pl : players)
            if (pl.getSessionId().equals(sessionId)) {
                players.remove(pl);
            }

        if (gameState != GameState.WAITINGFORPLAYERS) {
            gameState = GameState.WAITINGFORPLAYERS;
        }
    }

    private void checkStartingCondition() {
        if (players.size() == 2) {
            startGame();
        }
    }

    public void startGame() {
        gameState = GameState.ROUNDACTIVE;
        messageGenerator.notifyStartGame();
        messageGenerator.notifyNextTurn(turn, TeamColor.WHITE);
        setBoard();
        messageGenerator.notifyUpdateBoard(board);
    }

    public void makeMove(String from, String to) {
        Tile tileFrom = null;
        Tile tileTo = null;
        for (Tile[] tilesRow : board) {
            for (Tile tile : tilesRow) {
                if (Objects.equals(tile.getName(), from)) {
                    tileFrom = tile;
                } else if (Objects.equals(tile.getName(), to)) {
                    tileTo = tile;
                }
            }
        }
        try {
            tileTo.placePiece(tileFrom.getPiece());
            tileFrom.removePiece();
        } catch (NullPointerException exc) {
            exc.printStackTrace();
        }
        turn++;
        if (turn % 2 == 0) {
            messageGenerator.notifyNextTurn(turn, TeamColor.BLACK);
        } else {
            messageGenerator.notifyNextTurn(turn, TeamColor.WHITE);
        }
        messageGenerator.notifyNewEvent(new Event(players.forEach(player -> ); tileFrom.getPiece().getTeamColor() ,tileFrom, tileTo, turn, Instant.now()));
        messageGenerator.notifyUpdateBoard(board);
    }

    private void endGame() {
        messageGenerator.notifyEndGame();
    }
}