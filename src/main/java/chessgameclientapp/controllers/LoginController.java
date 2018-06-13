package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.Main;
import chessgameclientapp.interfaces.ILoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Player;

public class LoginController extends BaseController implements ILoginController {
    @FXML
    private TextField tbLoginName;
    @FXML
    private PasswordField tbLoginPassword;
    @FXML
    private TextField tbRegisterName;
    @FXML
    private PasswordField tbRegisterPassword;
    @FXML
    private PasswordField tbRegisterPasswordConfirm;

    public LoginController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerLoginController(this);
    }

    public void login() {
        getGameClient().requestPassword(tbLoginName.getText(), tbLoginPassword.getText());
    }

    public void register() {
        getGameClient().registerPlayer(tbRegisterName.getText(), tbRegisterPassword.getText(), tbRegisterPasswordConfirm.getText());
    }

    public void processRegistrationResult(boolean result) {
        if (result) {
            showAlert("Registration", "Registration successful");
        } else {
            showAlert("Registration", "Registration failed");
        }
    }

    public void processRequestPasswordResult(String passwordToken) {
        getGameClient().loginPlayer(tbLoginName.getText(), tbLoginPassword.getText(), passwordToken);
    }

    public void processLoginPlayerResult(Player player) {
        if(player != null){
            Platform.runLater(()->{
                Main.getMainStage().show();
                Main.getLoginStage().hide();
            });
        }else{
            Platform.runLater(() -> showAlert("Login", "Login failed"));
        }
    }
}
