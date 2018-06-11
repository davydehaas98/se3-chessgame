package chessgameshared;

import chessgameshared.interfaces.IEncapsulatedMessageGenerator;
import chessgameshared.messages.EncapsulatedMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.pieces.*;

public class EncapsulatedMessageGenerator implements IEncapsulatedMessageGenerator {
    RuntimeTypeAdapterFactory<Piece> pieceAdapterFactory = RuntimeTypeAdapterFactory.of(Piece.class, "type")
            .registerSubtype(Pawn.class, "Pawn")
            .registerSubtype(Rook.class, "Rook")
            .registerSubtype(Knight.class, "Knight")
            .registerSubtype(Bishop.class, "Bishop")
            .registerSubtype(King.class, "King")
            .registerSubtype(Queen.class, "Queen");
    private Gson gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();


    public EncapsulatedMessage generateEncapsulatedMessage(Object content) {
        String type = content.getClass().toGenericString();
        String messageForServerJson = gson.toJson(content);
        return new EncapsulatedMessage(type, messageForServerJson);
    }
    public String generateEncapsulatedMessageString(Object content){
        EncapsulatedMessage message = generateEncapsulatedMessage(content);
        return gson.toJson(message);
    }
}
