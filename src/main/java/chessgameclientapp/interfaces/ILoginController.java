package chessgameclientapp.interfaces;

import model.enums.TeamColor;

public interface ILoginController {
    void showAlert(String header, String content);
    void processRequestPasswordResult(String password);
    void processRegistrationResult(boolean result);
    void processLoginPlayerResult(TeamColor teamColor);
}
