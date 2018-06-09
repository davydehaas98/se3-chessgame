package chessgameclientapp.controllers;

import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.interfaces.ILoginController;
import chessgameshared.Cryptography;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        getGameClient().loginPlayer(tbLoginName.getText(), tbLoginPassword.getText());
    }

    public void register() {
        getGameClient().registerPlayer(tbRegisterName.getText(), tbRegisterPassword.getText(), tbRegisterPasswordConfirm.getText());
    }
}
