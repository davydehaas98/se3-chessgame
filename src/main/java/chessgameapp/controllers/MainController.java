package chessgameapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    Game game;
    @FXML
    private GridPane gridpaneChessBoard;

    MainController(IGameClient gameClient){
        super(gameClient);
    }

    public void register(){
        String name = "Davy";
        if(name.equals("")){
            //TODO
        }
        else{
            //TODO
            getGameClient().registerPlayer(name);
        }
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
}