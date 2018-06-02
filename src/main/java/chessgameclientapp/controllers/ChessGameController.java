package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Tile;
import model.enums.TeamColor;
import model.pieces.Piece;

import java.awt.*;

public class ChessGameController extends BaseController implements IClientGUI {
    public GridPane ChessBoard;
    public TextField tbUserName;
    public Button btnRegister;
    public Label lblPlayerTeamColor;
    public Label lblTurn;
    public ListView lvMadeMoves;
    private TeamColor playerTeamColor;
    private TeamColor turnTeamColor;

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

    public void processRegistrationResult(TeamColor teamColor) {
        Platform.runLater(() -> {
            if (teamColor != null) {
                tbUserName.setDisable(true);
                btnRegister.setDisable(true);
                this.playerTeamColor = teamColor;
                lblPlayerTeamColor.setText(playerTeamColor.toString());
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

    public void processNextTurn(int turn, TeamColor turnTeamColor) {
        this.turnTeamColor = turnTeamColor;
        lblTurn.setText("Turn " + turn + " | " + turnTeamColor);
    }

    private void resetStrokes() {
        ChessBoard.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                node.setStyle("-fx-stroke: black; -fx-stroke-width: 2");
            }
        });
    }

    private void resetClickableRectangles() {
        ChessBoard.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                node.setOnMouseClicked(null);
            }
        });
    }

    public void processUpdateBoard(Tile[][] board) {
        Platform.runLater(() -> {
            for (Tile[] tileRow : board) {
                for (Tile tile : tileRow) {
                    Piece pieceOnTile = tile.getPiece();
                    ChessBoard.getChildren().forEach(node -> {
                        //Loop through each childnode
                        if (node instanceof Rectangle && node.getId().equals(tile.getName()) && pieceOnTile != null && pieceOnTile.getTeamColor() == playerTeamColor && turnTeamColor == playerTeamColor) {
                            //Set all legal moves of the corresponding rectangle that contains a piece
                            setClickEventLegalMove(node, pieceOnTile, board);
                        } else if (node instanceof ImageView && node.getId().equals(tile.getName())) {
                            //Set the pieceImage on the corresponding Rectangle
                            if (pieceOnTile != null) {
                                ((ImageView) node).setImage(new Image(pieceOnTile.getImage()));
                            } else {
                                ((ImageView) node).setImage(null);
                            }
                        }
                    });
                }
            }
        });
    }

    private void setClickEventLegalMove(Node selectedRectangle, Piece selectedPiece, Tile[][] board) {
        selectedRectangle.setOnMouseClicked(event -> {
            resetStrokes();
            for (Point legalMove : selectedPiece.getLegalMoves(board)) {
                //Loop through every legal move
                for (Node node : ChessBoard.getChildren()) {
                    //Match the node column and row with the x and the y of the legal move
                    if (node instanceof Rectangle && ChessBoard.getColumnIndex(node) == legalMove.x && ChessBoard.getRowIndex(node) == legalMove.y) {
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
        ChessBoard.getChildren().clear();
        for (int column = 0; column < 8; column++) {
            ChessBoard.addColumn(column);
            for (int row = 0; row < 8; row++) {
                ChessBoard.addRow(row);
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
                ChessBoard.add(rec, column, row);

                ImageView imageView = new ImageView();
                imageView.setId(String.format("%s%d", files.charAt(column), 8 - row));
                imageView.setMouseTransparent(true);
                ChessBoard.add(imageView, column, row);
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