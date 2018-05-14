package chessgameapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane gridpaneChessBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTiles();
    }

    private void loadTiles() {
        boolean darkTile = true;
        for (int row = 0; row < 8; row++) {
            gridpaneChessBoard.addRow(row);
            for (int column = 0; column < 8; column++) {
                gridpaneChessBoard.addColumn(column);
                Pane pane = new Pane();
                pane.setId(row + column + "");
                if (darkTile) {
                    pane.setStyle("-fx-background-color: #777777");
                    darkTile = false;
                } else {
                    pane.setStyle("-fx-background-color: #cccccc");
                    darkTile = true;
                }
                gridpaneChessBoard.add(pane, column, row);
                gridpaneChessBoard.add(new ImageView(new Image("/images/WhiteBishop.png")), column, row);
            }
            if (darkTile) {
                darkTile = false;
            } else {
                darkTile = true;
            }
        }
    }
}
