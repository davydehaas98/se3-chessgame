package chessgameshared;

import chessgameshared.interfaces.IMessageHandlerFactory;
import chessgameshared.interfaces.IMessageProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.interfaces.IGame;
import model.pieces.*;

public abstract class MessageProcessorBase implements IMessageProcessor {
    private IGame game;
    private IMessageHandlerFactory messageHandlerFactory;
    private Gson gson;

    public MessageProcessorBase(IMessageHandlerFactory messageHandlerFactory) {
        this.messageHandlerFactory = messageHandlerFactory;
        RuntimeTypeAdapterFactory<Piece> pieceAdapterFactory = RuntimeTypeAdapterFactory.of(Piece.class, "type")
                .registerSubtype(Pawn.class, "Pawn")
                .registerSubtype(Rook.class, "Rook")
                .registerSubtype(Knight.class, "Knight")
                .registerSubtype(Bishop.class, "Bishop")
                .registerSubtype(King.class, "King")
                .registerSubtype(Queen.class, "Queen");
        gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();
    }

    public IGame getGame() {
        return game;
    }

    protected IMessageHandlerFactory getMessageHandlerFactory() {
        return messageHandlerFactory;
    }

    public Gson getGson() {
        return gson;
    }

    public void registerGame(IGame game) {
        this.game = game;
    }

    public abstract void processMessage(String sessionId, String type, String data);

    public abstract void handleDisconnect(String sessionId);
}
