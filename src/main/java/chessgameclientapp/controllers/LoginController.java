package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.Main;
import chessgameclientapp.interfaces.ILoginController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Player;

public class LoginController extends BaseController implements ILoginController {
    @FXML
    private TextField tbLoginName;
    @FXML
    private PasswordField tbLoginPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField tbRegisterName;
    @FXML
    private PasswordField tbRegisterPassword;
    @FXML
    private PasswordField tbRegisterPasswordConfirm;
    @FXML
    private Button btnRegister;

    public LoginController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().setLoginController(this);
    }

    private void setDisableAll(boolean bool) {
        tbLoginName.setDisable(bool);
        tbLoginPassword.setDisable(bool);
        btnLogin.setDisable(bool);
        tbRegisterName.setDisable(bool);
        tbRegisterPassword.setDisable(bool);
        tbRegisterPasswordConfirm.setDisable(bool);
        btnRegister.setDisable(bool);
    }

    public void register() {
        setDisableAll(true);
        getGameClient().registerPlayer(tbRegisterName.getText(), tbRegisterPassword.getText(), tbRegisterPasswordConfirm.getText());
    }

    public void login() {
        setDisableAll(true);
        getGameClient().requestPassword(tbLoginName.getText(), tbLoginPassword.getText());
    }

    public void processRegisterPlayerResult(boolean result) {
        setDisableAll(false);
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
        setDisableAll(false);
        if (player != null) {
            Platform.runLater(() -> {
                Main.getMainStage().show();
                Main.getLoginStage().hide();
            });
        } else {
            Platform.runLater(() -> showAlert("Login", "Login failed"));
        }
    }
}
