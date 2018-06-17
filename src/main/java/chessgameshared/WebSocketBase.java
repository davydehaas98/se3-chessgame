package chessgameshared;

import chessgameshared.interfaces.IEncapsulatedMessageGenerator;
import chessgameshared.interfaces.IMessageProcessor;
import chessgameshared.interfaces.IWebSocket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.pieces.*;

public abstract class WebSocketBase implements IWebSocket {
    private IMessageProcessor handler;
    private IEncapsulatedMessageGenerator encapsulatedMessageGenerator;
    private Gson gson;


    public WebSocketBase() {
        encapsulatedMessageGenerator = new EncapsulatedMessageGenerator();
        RuntimeTypeAdapterFactory<Piece> pieceAdapterFactory = RuntimeTypeAdapterFactory.of(Piece.class, "type")
                .registerSubtype(Pawn.class, "Pawn")
                .registerSubtype(Rook.class, "Rook")
                .registerSubtype(Knight.class, "Knight")
                .registerSubtype(Bishop.class, "Bishop")
                .registerSubtype(King.class, "King")
                .registerSubtype(Queen.class, "Queen");
        gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();
    }

    protected IEncapsulatedMessageGenerator getEncapsulatedMessageGenerator() {
        return encapsulatedMessageGenerator;
    }

    protected Gson getGson() {
        return gson;
    }

    protected IMessageProcessor getHandler() {
        return handler;
    }

    public void setMessageHandler(IMessageProcessor handler) {
        this.handler = handler;
    }

    public abstract void start();

    public abstract void stop();
}
