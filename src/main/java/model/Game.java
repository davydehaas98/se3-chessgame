package model;

import chessgameapi.IRESTClient;
import chessgameapi.RESTClient;
import chessgameserver.interfaces.IServerMessageGenerator;
import model.enums.GameState;
import model.enums.TeamColor;
import model.interfaces.IGame;
import model.pieces.*;

import java.awt.*;
import java.time.Instant;
import java.util.*;
import java.util.List;

public class Game implements IGame {
    private IServerMessageGenerator messageGenerator;
    private IRESTClient restClient;

    private HashMap<String, Player> players;
    private GameState gameState;
    private Tile[][] board;
    private int turn;
    private List<Event> events;

    public Game(IServerMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
        restClient = new RESTClient();
        players = new HashMap<>();
        events = new ArrayList<>();
        gameState = GameState.WAITINGFORPLAYERS;
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

    public void setBoard() {
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

    public Tile[][] getBoard() {
        return board;
    }

    public void setPiecesOnBoard(TeamColor teamColor, int y) {
        board[0][y].placePiece(new Rook(teamColor, new Point(0, y)));
        board[1][y].placePiece(new Knight(teamColor, new Point(1, y)));
        board[2][y].placePiece(new Bishop(teamColor, new Point(2, y)));
        board[3][y].placePiece(new Queen(teamColor, new Point(3, y)));
        board[4][y].placePiece(new King(teamColor, new Point(4, y)));
        board[5][y].placePiece(new Bishop(teamColor, new Point(5, y)));
        board[6][y].placePiece(new Knight(teamColor, new Point(6, y)));
        board[7][y].placePiece(new Rook(teamColor, new Point(7, y)));
    }

    public void registerPlayer(String name, String password, String sessionId) {
        boolean result = restClient.registerPlayer(name, password);
        messageGenerator.notifyRegisterPlayerResult(result, sessionId);
    }

    public void requestPassword(String name, String sessionId) {
        String password = restClient.requestPassword(name);
        messageGenerator.notifyRequestPasswordResult(password, sessionId);
    }

    private boolean checkIfAlreadyLoggedIn(Player newPlayer) {
        for (Player player : players.values()) {
            if (player.getEntityId() == newPlayer.getEntityId()) {
                return true;
            }
        }
        return false;
    }

    public void loginPlayer(String name, String password, String sessionId) {
        Player newPlayer = restClient.loginPlayer(name, password);
        //Only two players can play at the same time
        if (players.size() < 2 && newPlayer != null && !checkIfAlreadyLoggedIn(newPlayer)) {
            //Set TeamColor
            if (players.size() < 1) {
                newPlayer.setTeamColor(TeamColor.WHITE);
            } else {
                //Check what team the other player is in
                players.values().forEach(player -> {
                    if (player.getTeamColor().equals(TeamColor.WHITE)) {
                        newPlayer.setTeamColor(TeamColor.BLACK);
                    } else {
                        newPlayer.setTeamColor(TeamColor.WHITE);
                    }
                });
            }
            players.put(sessionId, newPlayer);
            messageGenerator.notifyLoginPlayerResult(newPlayer, sessionId);
            if (gameState.equals(GameState.WAITINGFORPLAYERS)) {
                checkStartingCondition();
            } else {
                sendCurrentTurn();
                messageGenerator.notifyEvents(events);
                messageGenerator.notifyUpdateBoard(board);
            }
        } else {
            messageGenerator.notifyLoginPlayerResult(null, sessionId);
        }
        System.out.println("[Players in game]: " + players.size());
    }

    public void logoutPlayer(String sessionId) {
        for (String key : players.keySet()) {
            if (key.equals(sessionId)) {
                players.remove(sessionId);
                System.out.println("[Players in game]: " + players.size());
                return;
            }
        }
    }

    private void checkStartingCondition() {
        if (players.size() == 2) {
            startGame();
        }
    }

    public void startGame() {
        messageGenerator.notifyStartGame();
        turn = 1;
        sendCurrentTurn();
        setBoard();
        messageGenerator.notifyUpdateBoard(board);
        gameState = GameState.ROUNDACTIVE;
    }

    public boolean makeMove(String from, String to, String sessionId) {
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
        if (tileFrom != null && tileTo != null) {
            tileTo.placePiece(tileFrom.getPiece());
            tileFrom.removePiece();
            events.add(new Event(players.get(sessionId), tileFrom, tileTo, turn, Date.from(Instant.now())));
            messageGenerator.notifyEvents(events);
            turn++;
            sendCurrentTurn();
            messageGenerator.notifyUpdateBoard(board);
            return true;
        }
        return false;
    }

    private void sendCurrentTurn() {
        if (turn % 2 == 0) {
            messageGenerator.notifyNextTurn(turn, TeamColor.BLACK);
        } else {
            messageGenerator.notifyNextTurn(turn, TeamColor.WHITE);
        }
    }

    private void endGame() {
        gameState = GameState.FINISHED;
        messageGenerator.notifyEndGame();
    }
}