package model.interfaces;

import model.Tile;

import java.awt.*;

public interface IGame {
    void registerPlayer(String name, String password, String sessionId);

    void requestPassword(String name, String sessionId);

    void loginPlayer(String name, String password, String sessionId);

    boolean makeMove(Point from, Point to, String sessionId);

    void startGame();

    void logoutPlayer(String sessionId);

    Tile[][] getBoard();

    void setBoard();
}
