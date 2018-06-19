package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.interfaces.IChessGameController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;
import model.pieces.Piece;

import java.awt.*;
import java.util.List;

public class ChessGameController extends BaseController implements IChessGameController {
    @FXML
    private GridPane chessBoard;
    @FXML
    private Label lblPlayerTeamColor;
    @FXML
    private Label lblTurn;
    @FXML
    private ListView lvMadeMoves;
    private Player player;
    private TeamColor turnTeamColor;
    private Rectangle[][] rectangles;
    private ImageView[][] imageViews;

    public ChessGameController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().setChessGameController(this);
        Platform.runLater(this::loadTiles);
    }

    public void setPlayer(Player player) {
        this.player = player;
        Platform.runLater(() -> lblPlayerTeamColor.setText("You are: " + player.getTeamColor().toString()));
    }

    private void setImage(Point point, Image image) {
        Platform.runLater(() -> imageViews[point.x][point.y].setImage(image));
    }

    public void processGameStarted() {
        System.out.println("Game Started");
    }

    public void processGameEnded() {
        System.out.println("Game Ended");
    }

    public void processNextTurn(int turn, TeamColor turnTeamColor) {
        this.turnTeamColor = turnTeamColor;
        Platform.runLater(() -> {
            lblTurn.setText("Turn " + turn + " | " + turnTeamColor);
        });
    }

    public void processEvents(List<Event> events) {
        lvMadeMoves.getItems().clear();
        Platform.runLater(() -> {
            lvMadeMoves.setItems((ObservableList<Event>) events);
        });
    }

    public void processUpdateBoard(Tile[][] board) {
        //Reset all Rectangle click events
        resetClickableRectangles();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = board[row][column].getPiece();
                if (piece != null) {
                    setImage(new Point(row, column), new Image(piece.getImage()));
                    if (piece.getTeamColor().equals(player.getTeamColor()) && turnTeamColor.equals(player.getTeamColor())) {
                        setClickable(rectangles[row][column], piece);
                    }
                } else {
                    setImage(new Point(row, column), null);
                }
            }
        }
    }

    private void setClickable(Rectangle rectangle, Piece piece) {
        rectangle.setOnMouseClicked(event -> {
            getGameClient().requestLegalMoves(piece);
        });
    }

    public void processRequestLegalMovesResult(Piece piece, List<Point> legalMoves) {
        resetStrokes();
        for (Point legalMove : legalMoves) {
            Platform.runLater(() -> {
                rectangles[legalMove.x][legalMove.y].setOnMouseClicked(event -> {
                    getGameClient().makeMove(piece.getCurrentPosition(), legalMove);
                    resetStrokes();
                });
                rectangles[legalMove.x][legalMove.y].setStyle("-fx-stroke: blue; -fx-stroke-width: 2");
            });
        }
    }

    private void resetStrokes() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                rectangles[row][column].setStyle("-fx-stroke: black; -fx-stroke-width: 2");
            }
        }
    }

    private void resetClickableRectangles() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                rectangles[row][column].setOnMouseClicked(null);
            }
        }
    }

    private void loadTiles() {
        boolean darkTile = true;
        chessBoard.getChildren().clear();
        rectangles = new Rectangle[8][8];
        imageViews = new ImageView[8][8];

        for (int row = 0; row < 8; row++) {
            chessBoard.addRow(row);
            for (int column = 0; column < 8; column++) {
                chessBoard.addColumn(column);
                //Rectangle
                rectangles[row][column] = new Rectangle(46, 46);
                if (darkTile) {
                    rectangles[row][column].setFill(Color.BROWN);
                } else {
                    rectangles[row][column].setFill(Color.LIGHTGRAY);
                }
                chessBoard.add(rectangles[row][column], row, column);
                darkTile = !darkTile;
                //ImageView
                imageViews[row][column] = new ImageView();
                imageViews[row][column].setMouseTransparent(true);
                chessBoard.add(imageViews[row][column], row, column);
            }
            darkTile = !darkTile;
        }
        resetStrokes();
        resetClickableRectangles();
    }
}