package chessgamedal.MySQLContext;

import chessgamedal.DatabaseConnection;
import chessgamedal.interfaces.IPlayerContext;
import model.Player;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class MySQLPlayerContext implements IPlayerContext {

    public Player getByID(int id) {
        Player player = null;
        String query = "CALL `getPlayerByID`(?)";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    player = new Player(result.getInt("id"),result.getString("name"), result.getInt("ranking"), result.getInt("wins"), result.getInt("losses"), result.getInt("draws"));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return player;
    }

    public List<Player> getAll() {
        List<Player> players = new ArrayList<>();
        String query = "CALL `getPlayers`()";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    players.add(new Player(result.getInt("id"),result.getString("name"), result.getInt("ranking"), result.getInt("wins"), result.getInt("losses"), result.getInt("draws")));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return players;
    }

    public Player getPlayerByName(String name) {
        Player player = null;
        String query = "CALL `getPlayerByName`(?)";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    player = new Player(result.getInt("id"), result.getString("name"), result.getInt("ranking"), result.getInt("wins"), result.getInt("losses"), result.getInt("draws"));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return player;
    }

    public boolean registerPlayer(String name, String password) {
        String query = "CALL `registerPlayer`(?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.registerOutParameter("oresult", 16);
            statement.executeQuery();
            return statement.getBoolean("oresult");
        } catch (Exception exc) {
            exc.printStackTrace();
            return false;
        }
    }

    public String requestPassword(String name) {
        String password = null;
        String query = "CALL `requestPassword`(?)";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    password = result.getString("password");
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return password;
    }

    public Player loginPlayer(String name, String password) {
        Player player = null;
        String query = "CALL `loginPlayer`(?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {
            statement.setString(1, name);
            statement.setString(2, password);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    player = new Player(result.getInt("id"),result.getString("name"), result.getInt("ranking"), result.getInt("wins"), result.getInt("losses"), result.getInt("draws"));
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return player;
    }
}
