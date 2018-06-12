package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.interfaces.IChessGameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Event;
import model.Tile;
import model.enums.TeamColor;
import model.pieces.Piece;

import java.awt.*;

public class ChessGameController extends BaseController implements IChessGameController {
    @FXML
    private GridPane chessBoard;
    @FXML
    private Label lblPlayerTeamColor;
    @FXML
    private Label lblTurn;
    @FXML
    private ListView lvMadeMoves;
    private TeamColor playerTeamColor;
    private TeamColor turnTeamColor;

    public ChessGameController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerChessgameController(this);
        Platform.runLater(this::loadTiles);
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

    public void processNewEvent(Event event) {
        Platform.runLater(() -> {
            System.out.println(event.toString());
            //lvMadeMoves.getItems().add(event.getInfo());
        });
    }

    private void resetStrokes() {
        chessBoard.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                node.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
            }
        });
    }

    private void resetClickableRectangles() {
        chessBoard.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                node.setOnMouseClicked(null);
            }
        });
    }

    public void processUpdateBoard(Tile[][] board) {
        for (Tile[] tileRow : board) {
            for (Tile tile : tileRow) {
                Piece pieceOnTile = tile.getPiece();
                chessBoard.getChildren().forEach(node -> {
                    //Loop through each childnode
                    if (node instanceof Rectangle && node.getId().equals(tile.getName()) && pieceOnTile != null && pieceOnTile.getTeamColor() == playerTeamColor && turnTeamColor == playerTeamColor) {
                        //Set all legal moves of the corresponding rectangle that contains a piece
                        setClickEventLegalMove(node, pieceOnTile, board);
                    } else if (node instanceof ImageView && node.getId().equals(tile.getName())) {
                        //Set the pieceImage on the corresponding Rectangle
                        if (pieceOnTile != null) {
                            Platform.runLater(() -> {
                                ((ImageView) node).setImage(new Image(pieceOnTile.getImage()));
                            });
                        } else {
                            ((ImageView) node).setImage(null);
                        }
                    }
                });
            }
        }
    }

    private void setClickEventLegalMove(Node selectedRectangle, Piece selectedPiece, Tile[][] board) {
        selectedRectangle.setOnMouseClicked(event -> {
            resetStrokes();
            for (Point legalMove : selectedPiece.getLegalMoves(board)) {
                //Loop through every legal move
                for (Node node : chessBoard.getChildren()) {
                    //Match the node column and row with the x and the y of the legal move
                    if (node instanceof Rectangle && chessBoard.getColumnIndex(node) == legalMove.x && chessBoard.getRowIndex(node) == legalMove.y) {
                        //Create a MouseClick event on the Rectangle
                        node.setOnMouseClicked(event1 -> Platform.runLater(() -> {
                            //Reset all Rectangle click events
                            resetClickableRectangles();
                            //Reset all the strokes to black
                            resetStrokes();
                            //Send the move of the selected piece with the node where it will be placed to the server
                            getGameClient().makeMove(selectedRectangle.getId(), node.getId());
                        }));
                        //Show all the legal moves options in color
                        node.setStyle("-fx-stroke: limegreen;");
                    }
                }
            }
        });
    }

    private void loadTiles() {
        boolean darkTile = true;
        String files = "abcdefgh";
        chessBoard.getChildren().clear();
        for (int column = 0; column < 8; column++) {
            chessBoard.addColumn(column);
            for (int row = 0; row < 8; row++) {
                chessBoard.addRow(row);
                Rectangle rec = new Rectangle(46, 46);
                rec.setId(String.format("%s%d", files.charAt(column), 8 - row));
                rec.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
                if (darkTile) {
                    rec.setFill(Color.BROWN);
                    darkTile = false;
                } else {
                    rec.setFill(Color.GREY);
                    darkTile = true;
                }
                chessBoard.add(rec, column, row);

                ImageView imageView = new ImageView();
                imageView.setId(String.format("%s%d", files.charAt(column), 8 - row));
                imageView.setMouseTransparent(true);
                chessBoard.add(imageView, column, row);
            }
            darkTile = !darkTile;
        }
    }
}