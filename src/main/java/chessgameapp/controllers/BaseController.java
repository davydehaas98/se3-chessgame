package chessgameapp.controllers;

import chessgameclient.interfaces.IGameClient;

public abstract class BaseController {
    public IGameClient gameClient;
    public BaseController(IGameClient gameClient){this.gameClient = gameClient;}

    public IGameClient getGameClient() {
        return gameClient;
    }
}
