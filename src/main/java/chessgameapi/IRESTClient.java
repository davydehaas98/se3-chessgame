package chessgameapi;

import model.Player;

public interface IRESTClient {
    boolean registerPlayer(String name, String password);

    String requestPassword(String name);

    Player loginPlayer(String name, String password);
}
