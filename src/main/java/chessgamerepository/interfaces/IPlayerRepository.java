package chessgamerepository.interfaces;

import model.Player;

public interface IPlayerRepository extends IRepository<Player> {
    Player getPlayerByName(String name);

    boolean registerPlayer(String name, String password);

    String requestPassword(String name);
}
