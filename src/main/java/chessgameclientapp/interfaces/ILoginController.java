package chessgameclientapp.interfaces;

import model.Player;

public interface ILoginController extends IController {
    void processRequestPasswordResult(String password);

    void processRegisterPlayerResult(boolean result);

    void processLoginPlayerResult(Player player);
}
