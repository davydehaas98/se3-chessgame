package chessgameshared;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IMessageHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.interfaces.IGame;
import model.pieces.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandler<T> implements IMessageHandler {
    private Gson gson;
    private IGame game;
    private IGameClient gameClient;
    private RuntimeTypeAdapterFactory<Piece> pieceAdapterFactory = RuntimeTypeAdapterFactory.of(Piece.class, "type")
            .registerSubtype(Pawn.class, "Pawn")
            .registerSubtype(Rook.class, "Rook")
            .registerSubtype(Knight.class, "Knight")
            .registerSubtype(Bishop.class, "Bishop")
            .registerSubtype(King.class, "King")
            .registerSubtype(Queen.class, "Queen");

    public MessageHandler(IGameClient gameClient) {
        this.gameClient = gameClient;
        gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();
    }

    public MessageHandler(IGame game) {
        this.game = game;
        gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();
    }

    public IGame getGame() {
        return game;
    }

    public IGameClient getGameClient() {
        return gameClient;
    }

    public void handleMessage(String data, String sessionId) {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T message = gson.fromJson(data, type);
        handleMessageInternal(message, sessionId);
    }

    public abstract void handleMessageInternal(T message, String sessionId);
}
