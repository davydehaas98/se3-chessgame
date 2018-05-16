package chessgameapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Game;

import javafx.scene.input.MouseEvent;
import model.pieces.Piece;

import java.awt.*;

public class MainController extends BaseController implements IClientGUI {
    @FXML
    private TextField tbUserName;
    @FXML
    private Button btnRegister;
    @FXML
    private GridPane gridpaneChessBoard;

    private Game game;

    public MainController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerClientGUI(this);
    }
    public void register() {
        String name = tbUserName.getText();
        if (name.equals("")) {
            //TODO
        } else {
            //TODO
            getGameClient().registerPlayer(name);
        }
    }
    public void processRegistrationResponse(boolean response){
        Platform.runLater(()-> {
            if(response){
                tbUserName.setDisable(true);
                btnRegister.setDisable(true);
                showAlert("Registration", "You are now registered!");
            }
            else{
                showAlert("Registration", "Try again :(");
            }
        });
    }

    public void processPlayerRegistered(String name) {
        Platform.runLater(() -> showAlert("Player has registered!", "name: " + name));
    }

    private void loadTiles() {
        boolean darkTile = true;
        gridpaneChessBoard.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            gridpaneChessBoard.addRow(row);
            for (int column = 0; column < 8; column++) {
                gridpaneChessBoard.addColumn(column);
                Rectangle rec = new Rectangle(50, 50);
                rec.setId(game.getBoard()[row][column].getName());
                rec.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
                if (darkTile) {
                    rec.setFill(Color.DARKGREY);
                    darkTile = false;
                } else {
                    rec.setFill(Color.GREY);
                    darkTile = true;
                }
                Piece piece = game.getChessPiece(row, column);

                if (piece != null) {
                    rec.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    System.out.println("You Selected " + piece.getColor() + piece.getType() + " on Tile " + rec.getId());
                                    for (Node node : gridpaneChessBoard.getChildren()) {
                                        if (node instanceof Rectangle) {
                                            node.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
                                        }
                                    }
                                    for (Point legalMove : piece.getLegalMoves(game.getBoard())) {
                                        for (Node node : gridpaneChessBoard.getChildren()) {
                                            if (node instanceof Rectangle) {
                                                if (gridpaneChessBoard.getRowIndex(node) == legalMove.y && gridpaneChessBoard.getColumnIndex(node) == legalMove.x) {
                                                    node.setStyle("-fx-stroke: limegreen; -fx-stroke-width: 2");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                    );
                    gridpaneChessBoard.add(rec, row, column);
                    ImageView imageView = new ImageView(new Image(piece.getImage()));
                    imageView.setMouseTransparent(true);
                    gridpaneChessBoard.add(imageView, row, column);
                } else {
                    gridpaneChessBoard.add(rec, row, column);
                    ImageView imageView = new ImageView();
                    imageView.setMouseTransparent(true);
                    gridpaneChessBoard.add(new ImageView(), row, column);
                }
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