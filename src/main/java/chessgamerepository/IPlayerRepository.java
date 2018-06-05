package chessgamerepository;

import model.Player;

public interface IPlayerRepository extends IRepository<Player>{
    Player loginPlayer(String name, String password);
    boolean createPlayer(String name, String password);
}
