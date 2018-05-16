package chessgameshared;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IMessageHandler;
import com.google.gson.Gson;
import model.interfaces.IGame;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandler<T> implements IMessageHandler {
    private Gson gson;
    private IGame game;
    private IGameClient gameClient;
    public MessageHandler(IGameClient gameClient){
        this.gameClient = gameClient;
        gson = new Gson();
    }
    public MessageHandler(IGame game){
        this.game = game;
        gson = new Gson();
    }

    public IGame getGame() {
        return game;
    }

    public IGameClient getGameClient() {
        return gameClient;
    }
    public void handleMessage(String data, String sessionId){
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T message = gson.fromJson(data,type);
        handleMessageInternal(message, sessionId);
    }
    public abstract void handleMessageInternal(T message, String sessionId);
}
