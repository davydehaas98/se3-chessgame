package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        if (name.equals("")) {
            //TODO
        } else {
            //TODO
            getGameClient().registerPlayer(name);
        }
    }

    public void processRegistrationResponse(boolean response) {
        Platform.runLater(() -> {
            if (response) {
                tbUserName.setDisable(true);
                btnRegister.setDisable(true);
                showAlert("Registration", "You are now registered!");
                loadTiles();
            } else {
                showAlert("Registration", "Try again :(");
            }
        });
    }

    public void processPlayerRegistered(String name) {
        Platform.runLater(() -> showAlert("Player has registered!", "name: " + name));
    }

    private void loadTiles() {
        boolean darkTile = true;
        String files = "abcdefgh";
        gridpaneChessBoard.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            gridpaneChessBoard.addRow(row);
            for (int column = 0; column < 8; column++) {
                gridpaneChessBoard.addColumn(column);
                Rectangle rec = new Rectangle(50, 50);
                rec.setId(String.format("%s%d", files.charAt(row), 8 - column));
                rec.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
                if (darkTile) {
                    rec.setFill(Color.DARKGREY);
                    darkTile = false;
                } else {
                    rec.setFill(Color.GREY);
                    darkTile = true;
                }

                gridpaneChessBoard.add(rec, row, column);
                ImageView imageView = new ImageView();
                imageView.setMouseTransparent(true);
                gridpaneChessBoard.add(new ImageView(), row, column);
            }
            darkTile = !darkTile;
        }
//        Piece piece = game.getChessPiece(row, column);
//
//        if (piece != null) {
//            rec.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent event) {
//                            System.out.println("You Selected " + piece.getColor() + piece.getType() + " on Tile " + rec.getId());
//                            for (Node node : gridpaneChessBoard.getChildren()) {
//                                if (node instanceof Rectangle) {
//                                    node.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
//                                }
//                            }
//                            for (Point legalMove : piece.getLegalMoves(game.getBoard())) {
//                                for (Node node : gridpaneChessBoard.getChildren()) {
//                                    if (node instanceof Rectangle) {
//                                        if (gridpaneChessBoard.getRowIndex(node) == legalMove.y && gridpaneChessBoard.getColumnIndex(node) == legalMove.x) {
//                                            node.setStyle("-fx-stroke: limegreen; -fx-stroke-width: 2");
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//            );
//            gridpaneChessBoard.add(rec, row, column);
//            ImageView imageView = new ImageView(new Image(piece.getImage()));
//            imageView.setMouseTransparent(true);
//            gridpaneChessBoard.add(imageView, row, column);
//        }
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}