package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import javafx.scene.control.Alert;

public abstract class BaseController {
    public IGameClient gameClient;

    BaseController(IGameClient gameClient) {
        this.gameClient = gameClient;
    }

    public IGameClient getGameClient() {
        return gameClient;
    }

    public void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
