package model.interfaces;

import model.Tile;

public interface IGame {
    void registerPlayer(String name, String password, String sessionId);

    void requestPassword(String name, String sessionId);

    void loginPlayer(String name, String password, String sessionId);

    boolean makeMove(String from, String to, String sessionId);

    void startGame();

    void logoutPlayer(String sessionId);

    Tile[][] getBoard();

    void setBoard();
}
