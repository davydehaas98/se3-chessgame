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
import javafx.scene.input.MouseEvent;
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

    public void processRegistrationResponse(boolean response) {
        Platform.runLater(() -> {
            if (response) {
                loadTiles();
                tbUserName.setDisable(true);
                btnRegister.setDisable(true);
                //showAlert("Registration", "You are now registered");
            } else {
                showAlert("Registration", "Try again :(");
            }
        });
    }
    public void processAnotherPlayerRegistered(String name) {
        Platform.runLater(() -> showAlert("Another Player has registered", "name: " + name));
    }

    public void processGameStarted() {
        System.out.println("Game Started");
        //startGame();
    }

    public void processGameEnded() {
        System.out.println("Game Ended");

    }

    public void processNextTurn() {

    }

    public void processUpdateBoard(Tile[][] board) {
        Platform.runLater(() -> {
            ObservableList<Node> boardNodes = gridpaneChessBoard.getChildren();
            for (Tile[] tileRow : board) {
                for (Tile tile : tileRow) {
                    Piece piece = tile.getPiece();
                    boardNodes.forEach(node -> {
                        if (Objects.equals(node.getId(), tile.getName())) {
                            if (node instanceof Rectangle) {
                                node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                                    //Reset Colors
                                    for (Node boardNode : gridpaneChessBoard.getChildren()) {
                                        if (boardNode instanceof Rectangle) {
                                            boardNode.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
                                        }
                                    }
                                    if(piece != null) {
                                        //Color every legal Moved
                                        for (Point legalMove : piece.getLegalMoves(board)) {
                                            for (Node legalNode : gridpaneChessBoard.getChildren()) {
                                                if (gridpaneChessBoard.getRowIndex((legalNode)) == legalMove.y && gridpaneChessBoard.getColumnIndex(legalNode) == legalMove.x) {
                                                    legalNode.setStyle("-fx-stroke: limegreen;");
                                                    legalNode.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                        Platform.runLater(() -> {
                                                            System.out.println("Move from " + node.getId() + " to " + legalNode.getId());
                                                        });
                                                    });
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
        });
    }

    private void loadTiles() {
        boolean darkTile = true;
        String files = "abcdefgh";
        gridpaneChessBoard.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            gridpaneChessBoard.addRow(row);
            for (int column = 0; column < 8; column++) {
                gridpaneChessBoard.addColumn(column);
                Rectangle rec = new Rectangle(46, 46);
                rec.setId(String.format("%s%d", files.charAt(row), 8 - column));
                rec.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
                if (darkTile) {
                    rec.setFill(Color.BROWN);
                    darkTile = false;
                } else {
                    rec.setFill(Color.GREY);
                    darkTile = true;
                }
                gridpaneChessBoard.add(rec, row, column);

                ImageView imageView = new ImageView();
                imageView.setId(String.format("%s%d", files.charAt(row), 8 - column));
                imageView.setMouseTransparent(true);
                gridpaneChessBoard.add(imageView, row, column);
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