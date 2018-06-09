package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IGameClient;
import chessgameclientapp.interfaces.IChessGameController;
import chessgameclientapp.interfaces.ILoginController;
import chessgameshared.Cryptography;
import model.Event;
import model.Tile;
import model.enums.TeamColor;

public class GameClient implements IGameClient {
    private IClientMessageGenerator messageGenerator;
    private IChessGameController chessGameController;
    private ILoginController loginController;

    public GameClient(IClientMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public void registerChessgameController(IChessGameController chessGameController) {
        this.chessGameController = chessGameController;
    }

    public void registerLoginController(ILoginController loginController) {
        this.loginController = loginController;
    }

    public void registerPlayer(String name, String password, String confirmPassword) {
        if (!name.isEmpty() && !name.contains(" ") && name.length() < 65) {
            if (!password.isEmpty() && !password.contains(" ") && password.length() < 65) {
                if (password.equals(confirmPassword)) {
                    messageGenerator.registerPlayer(name, Cryptography.hash(password.toCharArray()));
                } else {
                    loginController.showAlert("Password", "Passwords do not match");
                }
            } else {
                loginController.showAlert("Password", "Password is invalid");
            }
        } else {
            loginController.showAlert("Name", "Name is invalid");
        }
        //messageGenerator.registerPlayerOnServer(name);
    }

    public void loginPlayer(String name, String password) {
        if (!name.isEmpty() && !name.contains(" ") && name.length() < 65) {
            if (!password.isEmpty() && !password.contains(" ") && password.length() < 65) {
                messageGenerator.requestPassword(name);
            }
        }
    }

    public void handleRegistrationResult(TeamColor teamColor) {
        chessGameController.processRegistrationResult(teamColor);
    }

    public void handleAnotherPlayerRegistered(String name) {
        chessGameController.processAnotherPlayerRegistered(name);
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

    public void makeMove(String from, String to) {
        messageGenerator.makeMove(from, to);
    }

    public void handleNextTurn(int turn, TeamColor turnTeamColor) {
        chessGameController.processNextTurn(turn, turnTeamColor);
    }

    public void handleNewEvent(Event event) {
        chessGameController.processNewEvent(event);
    }
}
