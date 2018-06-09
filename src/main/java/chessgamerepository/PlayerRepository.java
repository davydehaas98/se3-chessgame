package chessgamerepository;

import chessgamedal.MySQLContext.MySQLPlayerContext;
import chessgamedal.interfaces.IPlayerContext;
import chessgamerepository.interfaces.IPlayerRepository;
import model.Player;

import java.util.List;

public class PlayerRepository implements IPlayerRepository {
    private IPlayerContext context;

    //Default Context is MySQL
    public PlayerRepository() {
        context = new MySQLPlayerContext();
    }

    public PlayerRepository(IPlayerContext context) {
        this.context = context;
    }

    public Player getByID(int id) {
        return context.getByID(id);
    }

    public List<Player> getAll() {
        return context.getAll();
    }

    public Player loginPlayer(String name, String password) {
        return context.loginPlayer(name, password);
    }

    public boolean createPlayer(String name, String password) {
        return context.createPlayer(name, password);
    }
}
