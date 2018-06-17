package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.interfaces.IChessGameController;
import chessgameclientapp.interfaces.ILoginController;
import chessgameshared.Crypto;
import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;

import java.util.List;

public class GameClient implements IGameClient {
    private IClientMessageGenerator messageGenerator;
    private IChessGameController chessGameController;
    private ILoginController loginController;

    public GameClient(IClientMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public void setChessGameController(IChessGameController chessGameController) {
        this.chessGameController = chessGameController;
    }

    public void setLoginController(ILoginController loginController) {
        this.loginController = loginController;
    }

    public void registerPlayer(String name, String password, String confirmPassword) {
        if (!name.isEmpty() && !name.contains(" ") && name.length() < 65) {
            if (!password.isEmpty() && !password.contains(" ") && password.length() < 65) {
                if (password.equals(confirmPassword)) {
                    //Register a Player on the database
                    messageGenerator.registerPlayer(name, new Crypto().hash(password.toCharArray()));
                } else {
                    loginController.showAlert("", "Passwords do not match");
                }
            } else {
                loginController.showAlert("", "Password is invalid");
            }
        } else {
            loginController.showAlert("Name", "Name is invalid");
        }
    }

    public void requestPassword(String name, String password) {
        if (!name.isEmpty() && !name.contains(" ") && name.length() < 65) {
            if (!password.isEmpty() && !password.contains(" ") && password.length() < 65) {
                //Request the Password from the Player
                messageGenerator.requestPassword(name);
            } else {
                loginController.showAlert("", "Password is invalid");
            }
        } else {
            loginController.showAlert("Name", "Name is invalid");
        }
    }

    public void loginPlayer(String name, String password, String passwordToken) {
        if (new Crypto().authenticate(password.toCharArray(), passwordToken)) {
            //Log the player in on the Game
            messageGenerator.loginPlayer(name, passwordToken);
        } else {
            loginController.showAlert("", "Password was not correct");
        }
    }

    public void logoutPlayer(){
        messageGenerator.logoutPlayer();
    }

    public void makeMove(String from, String to) {
        messageGenerator.makeMove(from, to);
    }

    public void handleRequestPasswordResult(String password) {
        loginController.processRequestPasswordResult(password);
    }

    public void handleRegisterPlayerResult(boolean result) {
        loginController.processRegisterPlayerResult(result);
    }

    public void handleLoginPlayerResult(Player player) {
        chessGameController.setPlayer(player);
        loginController.processLoginPlayerResult(player);
    }

    public void handleRoundStarted() {
        chessGameController.processGameStarted();
    }

    public void handleGameEnded() {
        chessGameController.processGameEnded();
    }

    public void handleUpdateBoard(Tile[][] board) {
        chessGameController.processUpdateBoard(board);
    }

    public void handleNextTurn(int turn, TeamColor turnTeamColor) {
        chessGameController.processNextTurn(turn, turnTeamColor);
    }

    public void handleNewEvent(List<Event> events) {
        chessGameController.processEvents(events);
    }
}
