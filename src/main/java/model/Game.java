package model;

import chessgamerepository.PlayerRepository;
import chessgamerepository.interfaces.IPlayerRepository;
import chessgameserver.interfaces.IServerMessageGenerator;
import model.enums.GameState;
import model.enums.TeamColor;
import model.interfaces.IGame;
import model.pieces.*;

import java.awt.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Game implements IGame {
    private IServerMessageGenerator messageGenerator;
    private HashMap<String, Player> players;
    private GameState gameState;
    private int turn;

    private IPlayerRepository playerRepository;

    private Tile[][] board;
    private List<Event> events;

    public Game(IServerMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
        players = new HashMap<>();
        gameState = GameState.WAITINGFORPLAYERS;
        playerRepository = new PlayerRepository();
    }

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

    @Override
    public void registerPlayer(String name, String password, String sessionId) {
        messageGenerator.notifyRegisterPlayerResult(playerRepository.registerPlayer(name, password), sessionId);
        //TODO connect to Database
    }

    public void requestPassword(String name, String sessionId) {
        String password = playerRepository.requestPassword(name);
        messageGenerator.notifyRequestPasswordResult(password, sessionId);
    }

    public void loginPlayer(String name, String sessionId) {
        //Only two players can play at the same time
        if (players.size() < 2) {
            //TODO Database Connection
            //Check if the Player is already registered
            if (isAlreadyLoggedIn(name)) {
                messageGenerator.notifyLoginPlayerResult(null, sessionId);
                return;
            }
            Player newPlayer = playerRepository.getPlayerByName(name);
            //Add Player to the players Hashmap
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
            System.out.println("[Players in game]: " + players.size());
            messageGenerator.notifyLoginPlayerResult(newPlayer.getTeamColor(), sessionId);
            if (gameState.equals(GameState.WAITINGFORPLAYERS)) {
                checkStartingCondition();
            }
            messageGenerator.notifyUpdateBoard(board);
        } else {
            messageGenerator.notifyLoginPlayerResult(null, sessionId);
        }
    }

    private boolean isAlreadyLoggedIn(String name) {
        for (Player player : players.values())
            if (player.getName().equals(name)) {
                return true;
            }
        return false;
    }

    public void processPlayerDisconnect(String sessionId) {
        for (String key : players.keySet()) {
            if (key.equals(sessionId)) {
                players.remove(sessionId);
            }
        }
        System.out.println("[Players in game]: " + players.size());
    }

    private void checkStartingCondition() {
        if (players.size() == 2) {
            startGame();
        }
    }

    public void startGame() {
        turn = 1;
        messageGenerator.notifyStartGame();
        messageGenerator.notifyNextTurn(turn, TeamColor.WHITE);
        setBoard();
        messageGenerator.notifyUpdateBoard(board);
        gameState = GameState.ROUNDACTIVE;
    }

    public void makeMove(String from, String to, String sessionId) {
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
            turn++;
            if (turn % 2 == 0) {
                messageGenerator.notifyNextTurn(turn, TeamColor.BLACK);
            } else {
                messageGenerator.notifyNextTurn(turn, TeamColor.WHITE);
            }
            messageGenerator.notifyNewEvent(new Event(players.get(sessionId), tileFrom, tileTo, turn, Date.from(Instant.now())));
            messageGenerator.notifyUpdateBoard(board);
        }
    }

    private void endGame() {
        gameState = GameState.FINISHED;
        messageGenerator.notifyEndGame();
    }
}