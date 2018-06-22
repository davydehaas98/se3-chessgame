package chessgameshared;

import chessgameshared.interfaces.IEncapsulatedMessageGenerator;
import chessgameshared.messages.EncapsulatedMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.pieces.*;

public final class EncapsulatedMessageGenerator implements IEncapsulatedMessageGenerator {
    private static final RuntimeTypeAdapterFactory<Piece> PIECE_ADAPTER_FACTORY = RuntimeTypeAdapterFactory.of(Piece.class, "type")
            .registerSubtype(Pawn.class, "Pawn")
            .registerSubtype(Rook.class, "Rook")
            .registerSubtype(Knight.class, "Knight")
            .registerSubtype(Bishop.class, "Bishop")
            .registerSubtype(King.class, "King")
            .registerSubtype(Queen.class, "Queen");
    private Gson gson = new GsonBuilder().registerTypeAdapterFactory(PIECE_ADAPTER_FACTORY).create();


    public EncapsulatedMessage generateEncapsulatedMessage(Object content) {
        String type = content.getClass().toGenericString();
        String messageForServerJson = gson.toJson(content);
        return new EncapsulatedMessage(type, messageForServerJson);
    }

    public String generateEncapsulatedMessageString(Object content) {
        EncapsulatedMessage message = generateEncapsulatedMessage(content);
        return gson.toJson(message);
    }
}
