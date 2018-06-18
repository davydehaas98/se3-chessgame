package chessgamerepository;

import chessgamedal.interfaces.IPlayerContext;
import chessgamedal.mysqlcontext.MySQLPlayerContext;
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

    public Player getPlayerByName(String name) {
        return context.getPlayerByName(name);
    }

    public boolean registerPlayer(String name, String password) {
        return context.registerPlayer(name, password);
    }

    public String requestPassword(String name) {
        return context.requestPassword(name);
    }

    public Player loginPlayer(String name, String password) {
        return context.loginPlayer(name, password);
    }
}
