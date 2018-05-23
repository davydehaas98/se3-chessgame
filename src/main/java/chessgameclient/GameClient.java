package chessgameclient;

import chessgameclient.interfaces.IClientMessageGenerator;
import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IClientGUI;
import model.Tile;
import model.enums.TeamColor;

public class GameClient implements IGameClient {
    private IClientMessageGenerator messageGenerator;
    private IClientGUI clientGUI;

    public GameClient(IClientMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public void registerClientGUI(IClientGUI clientGUI) {
        this.clientGUI = clientGUI;
    }

    public void registerPlayer(String name) {
        messageGenerator.registerPlayerOnServer(name);
    }

    public void handleRegistrationResult(TeamColor teamColor) {
        clientGUI.processRegistrationResult(teamColor);
    }

    public void handleAnotherPlayerRegistered(String name) {
        clientGUI.processAnotherPlayerRegistered(name);
    }

    public void handleRoundStarted() {
        clientGUI.processGameStarted();
    }

    public void handleGameEnded() {
        clientGUI.processGameEnded();
    }

    public void handleUpdateBoard(Tile[][] board) {
        clientGUI.processUpdateBoard(board);
    }

    public void makeMove(String from, String to){ messageGenerator.makeMove(from,to);}

    public void handleNextTurn(int turn, TeamColor turnTeamColor){clientGUI.processNextTurn(turn, turnTeamColor);}
}
