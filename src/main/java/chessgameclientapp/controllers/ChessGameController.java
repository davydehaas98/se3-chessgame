package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Tile;
import model.pieces.Piece;

import java.awt.*;
import java.util.Objects;

public class ChessGameController extends BaseController implements IClientGUI {

    public GridPane gridpaneChessBoard;
    public TextField tbUserName;
    public Button btnRegister;

    public ChessGameController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerClientGUI(this);
        Platform.runLater(this::loadTiles);
    }

    public void register() {
        String name = tbUserName.getText();
        if (name.equals("") || name.length() > 64) {
            showAlert("Registration", "This name is invalid");
        } else {
            //TODO
            getGameClient().registerPlayer(name);
        }
    }

    public void processRegistrationResult(boolean response, model.enums.Color color) {
        Platform.runLater(() -> {
            if (response) {
                tbUserName.setDisable(true);
                btnRegister.setDisable(true);
                //showAlert("Registration", "You are now registered");
            } else {
                showAlert("Registration", "Try again :(");
            }
        });
    }

    public void processAnotherPlayerRegistered(String name) {
        System.out.println("Another Player has registered: " + name);
    }

    public void processGameStarted() {
        System.out.println("Game Started");
    }

    public void processGameEnded() {
        System.out.println("Game Ended");

    }

    public void processNextTurn(int turn, model.enums.Color turnColor) {
        System.out.println("Turn " + turn + " Color: " + turnColor);
    }

    private void resetStrokes() {
        for (Node boardNode : gridpaneChessBoard.getChildren()) {
            if (boardNode instanceof Rectangle) {
                boardNode.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
            }
        }
    }

    public void processUpdateBoard(Tile[][] board) {
        ObservableList<Node> boardNodes = gridpaneChessBoard.getChildren();
        for (Tile[] tileRow : board) {
            for (Tile tile : tileRow) {
                Piece piece = tile.getPiece();
                boardNodes.forEach(node -> {
                    if (Objects.equals(node.getId(), tile.getName())) {
                        if (node instanceof Rectangle) {
                            node.setOnMouseClicked(event -> {
                                resetStrokes();
                                if (piece != null) {
                                    //Color every legal Moved
                                    for (Point legalMove : piece.getLegalMoves(board)) {
                                        for (Node boardNode : boardNodes) {
                                            if (gridpaneChessBoard.getColumnIndex(boardNode) == legalMove.x && gridpaneChessBoard.getRowIndex(boardNode) == legalMove.y && boardNode instanceof Rectangle) {
                                                boardNode.setStyle("-fx-stroke: limegreen;");
                                                boardNode.setOnMouseClicked(event1 -> Platform.runLater(() -> {
                                                    getGameClient().makeMove(node.getId(), boardNode.getId());
                                                    System.out.println("Move from " + node.getId() + " to " + boardNode.getId());
                                                    resetStrokes();
                                                }));
                                            } else {
                                                boardNode.setStyle("-fx-stroke: black;");
                                                boardNode.setOnMouseClicked(null);
                                            }
                                        }
                                    }
                                }
                            });

                        } else if (node instanceof ImageView) {
                            if (piece != null) {
                                ((ImageView) node).setImage(new Image(piece.getImage()));
                            } else {
                                ((ImageView) node).setImage(null);
                            }
                        }
                    }
                });
            }
        }
    }

    private void loadTiles() {
        boolean darkTile = true;
        String files = "abcdefgh";
        gridpaneChessBoard.getChildren().clear();
        for (int column = 0; column < 8; column++) {
            gridpaneChessBoard.addColumn(column);
            for (int row = 0; row < 8; row++) {
                gridpaneChessBoard.addRow(row);
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
                gridpaneChessBoard.add(rec, column, row);

                ImageView imageView = new ImageView();
                imageView.setId(String.format("%s%d", files.charAt(column), 8 - row));
                imageView.setMouseTransparent(true);
                gridpaneChessBoard.add(imageView, column, row);
            }
            darkTile = !darkTile;
        }
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}