package chessgameshared;

import chessgameshared.interfaces.IEncapsulatingMessageGenerator;
import chessgameshared.messages.EncapsulatingMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.pieces.*;

public class EncapsulatingMessageGenerator implements IEncapsulatingMessageGenerator {
    RuntimeTypeAdapterFactory<Piece> pieceAdapterFactory = RuntimeTypeAdapterFactory.of(Piece.class, "type")
            .registerSubtype(Pawn.class, "Pawn")
            .registerSubtype(Rook.class, "Rook")
            .registerSubtype(Knight.class, "Knight")
            .registerSubtype(Bishop.class, "Bishop")
            .registerSubtype(King.class, "King")
            .registerSubtype(Queen.class, "Queen");
    private Gson gson = new GsonBuilder().registerTypeAdapterFactory(pieceAdapterFactory).create();


    public EncapsulatingMessage generateMessage(Object content) {
        String messageForServerJson = gson.toJson(content);
        String type = content.getClass().toGenericString();
        return new EncapsulatingMessage(type, messageForServerJson);
    }
    public String generateMessageString(Object content){
        EncapsulatingMessage message = generateMessage(content);
        return gson.toJson(message);
    }
}
