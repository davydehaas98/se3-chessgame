package chessgameclientapp;

import chessgameclient.ClientMessageGenerator;
import chessgameclient.ClientMessageProcessor;
import chessgameclient.ClientWebSocket;
import chessgameclient.GameClient;
import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IClientMessageProcessor;
import chessgameclient.interfaces.IClientWebSocket;
import chessgameclient.interfaces.IGameClient;
import chessgameclient.messagehandlers.ClientMessageHandlerFactory;
import chessgameshared.interfaces.IMessageHandlerFactory;
import chessgameshared.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.lang.reflect.Constructor;

public class Main extends Application {
    private static Stage mainStage, loginStage;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static Stage getLoginStage() {
        return loginStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        IClientWebSocket clientWebSocket = new ClientWebSocket();
        IClientMessageGenerator clientMessageGenerator = new ClientMessageGenerator(clientWebSocket);
        IGameClient gameClient = new GameClient(clientMessageGenerator);
        IMessageHandlerFactory clientMessageHandlerFactory = new ClientMessageHandlerFactory();
        IClientMessageProcessor clientMessageProcessor = new ClientMessageProcessor(clientMessageHandlerFactory);
        clientWebSocket.setMessageHandler(clientMessageProcessor);
        clientWebSocket.start();
        clientMessageProcessor.registerGameClient(gameClient);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        setLoaderControllerFactory(gameClient, loader);
        loginStage = new Stage();
        loginStage.setScene(new Scene(loader.load()));
        loginStage.setTitle("Login");
        loginStage.getIcons().add(new Image("/images/ChessGameIcon.png"));
        loginStage.setResizable(false);
        loginStage.setOnCloseRequest(event -> {
            clientWebSocket.stop();
            Platform.exit();
            System.exit(0);
        });

        loader = new FXMLLoader(getClass().getResource("/ChessGame.fxml"));
        setLoaderControllerFactory(gameClient, loader);
        mainStage = primaryStage;
        mainStage.setScene(new Scene(loader.load()));
        mainStage.setTitle("ChessGame");
        mainStage.getIcons().add(new Image("/images/ChessGameIcon.png"));
        mainStage.setResizable(false);
        mainStage.setOnCloseRequest(event -> {
            clientWebSocket.stop();
            Platform.exit();
            System.exit(0);
        });

        loginStage.show();
    }

    private void setLoaderControllerFactory(IGameClient gameClient, FXMLLoader loader) {
        //Use reflection for dependency injection:
        //Inject every controller instance dynamically with the same game client instance
        loader.setControllerFactory((Class<?> type) -> {
            try {
                //Look for constructor taking GameClient as a parameter
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == IGameClient.class) {
                        return c.newInstance(gameClient);
                    }
                }
                //Default constructor:
                return type.newInstance();
            } catch (Exception exc) {
                exc.printStackTrace();
                return null;
            }
        });
    }
}
