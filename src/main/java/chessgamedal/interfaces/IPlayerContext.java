package chessgamedal.interfaces;

import model.Player;

public interface IPlayerContext extends IDataContext<Player> {
    Player loginPlayer(String name, String password);
    boolean createPlayer(String name, String password);
}
