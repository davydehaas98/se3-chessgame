package chessgameclientapp;

import chessgameclient.*;
import chessgameclient.interfaces.*;
import chessgameclient.messagehandlers.ClientMessageHandlerFactory;
import chessgameshared.interfaces.IMessageHandlerFactory;
import chessgameshared.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
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
/*
                USE REFLECTION FOR DEPENDENCY INJECTION:
                INJECT EVERY CONTROLLER INSTANCE, WHICH IS CREATED DYNAMICALLY WHILE LOADING THE FXML,
                WITH THE SAME GAME CLIENT INSTANCE
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChessGame.fxml"));
        loader.setControllerFactory((Class<?> type) -> {
            try {
                // look for constructor taking MyService as a parameter
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == IGameClient.class) {
                        return c.newInstance(gameClient);
                    }
                }
                // didn't find appropriate constructor, just use default constructor:
                return type.newInstance();
            } catch (Exception exc) {
                Logger.getInstance().log(exc);
                return null;
            }
        });
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.getIcons().add(new Image("/images/ChessGameIcon.png"));
        primaryStage.setTitle("Chessgame");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(closeEvent -> {
            clientWebSocket.stop();
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}
