package memorycontext;

import chessgamedal.interfaces.IPlayerContext;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerMemoryContext implements IPlayerContext {
    private List<Player> players;
    private List<Player> loggedInPlayers;

    public PlayerMemoryContext(){
        players = new ArrayList<>();
        loggedInPlayers = new ArrayList<>();
    }

    public Player getByID(int id) {
        for (Player player : players) {
            if (player.getEntityId() == id) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getAll() {
        return players;
    }

    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public boolean registerPlayer(String name, String password) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return false;
            }
        }
        players.add(new Player(0, name, 0, 0, 0, 0));
        return true;
    }

    public String requestPassword(String name) {
        return null;
    }

    public Player loginPlayer(String name, String password) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return null;
            }
        }
        loggedInPlayers.add(new Player(0, name, 0, 0, 0, 0));
        return loggedInPlayers.get(loggedInPlayers.size() - 1);
    }
}
