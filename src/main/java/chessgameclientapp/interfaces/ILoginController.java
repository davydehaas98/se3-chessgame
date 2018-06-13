package chessgameclientapp.interfaces;

import model.Player;

public interface ILoginController {
    void showAlert(String header, String content);
    void processRequestPasswordResult(String password);
    void processRegistrationResult(boolean result);
    void processLoginPlayerResult(Player player);
}
