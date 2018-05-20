package chessgameshared;

import chessgameshared.interfaces.IEncapsulatingMessageGenerator;
import chessgameshared.interfaces.IMessageProcessor;
import chessgameshared.interfaces.IWebSocket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.pieces.*;

public abstract class WebSocketBase implements IWebSocket {
    private IMessageProcessor handler;
    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator;
    private Gson gson;


    public WebSocketBase() {
        encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();
        RuntimeTypeAdapterFactory<Piece> pieceAdapterFactory = RuntimeTypeAdapterFactory.of(Piece.class, "type")
                .registerSubtype(Pawn.class, "Pawn")
                .registerSubtype(Rook.class, "Rook")
                .registerSubtype(Knight.class, "Knight")
                .registerSubtype(Bishop.class, "Bishop")
                .registerSubtype(King.class, "King")
                .registerSubtype(Queen.class, "Queen");
        gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();
    }

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    public Gson getGson() {
        return gson;
    }

    public IMessageProcessor getHandler() {
        return handler;
    }

    public void setMessageHandler(IMessageProcessor handler) {
        this.handler = handler;
    }

    public abstract void start();

    public abstract void stop();
}
