package chessgameapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Chessgame");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("ChessGame.fxml"))));
        primaryStage.getIcons().add(new Image("images/ChessGameIcon.png"));
        primaryStage.show();
    }
}
