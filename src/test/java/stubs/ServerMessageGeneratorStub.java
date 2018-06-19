package stubs;

import chessgameserver.interfaces.IServerMessageGenerator;
import model.Event;
import model.Player;
import model.Tile;
import model.enums.TeamColor;
import model.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerMessageGeneratorStub implements IServerMessageGenerator {
    @Override
    public void notifyRegisterPlayerResult(boolean result, String sessionId) {

    }

    @Override
    public void notifyRequestPasswordResult(String password, String sessionId) {

    }

    @Override
    public void notifyLoginPlayerResult(Player player, String sessionId) {

    }

    @Override
    public void notifyStartGame() {

    }

    @Override
    public void notifyEndGame() {

    }

    @Override
    public void notifyRequestLegalMovesResult(Piece piece, ArrayList<Point> confirmedLegalMoves, String sessionId) {

    }

    @Override
    public void notifyUpdateBoard(Tile[][] board) {

    }

    @Override
    public void notifyNextTurn(int turn, TeamColor turnTeamColor) {

    }

    @Override
    public void notifyEvents(List<Event> events) {

    }
}
