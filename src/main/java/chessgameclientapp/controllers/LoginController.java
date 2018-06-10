package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.Main;
import chessgameclientapp.interfaces.ILoginController;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.enums.TeamColor;

public class LoginController extends BaseController implements ILoginController {
    public TextField tbLoginName;
    public PasswordField tbLoginPassword;
    public TextField tbRegisterName;
    public PasswordField tbRegisterPassword;
    public PasswordField tbRegisterPasswordConfirm;

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

    public void processLoginPlayerResult(TeamColor teamColor) {
        if(teamColor != null){
            showAlert("Login", "Login successful");
            Platform.runLater(()->{
                Main.getMainStage().show();
                Main.getLoginStage().hide();
            });
        }else{
            showAlert("Login", "Login failed");
        }
    }
}
