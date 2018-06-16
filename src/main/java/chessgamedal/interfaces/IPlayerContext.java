package chessgamedal.interfaces;

import model.Player;

public interface IPlayerContext extends IContext<Player> {
    Player getPlayerByName(String name);

    Player loginPlayer(String name, String password);

    boolean registerPlayer(String name, String password);

    String requestPassword(String name);
}
