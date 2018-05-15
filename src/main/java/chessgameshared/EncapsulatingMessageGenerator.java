package chessgameshared;

import chessgameshared.interfaces.IEncapsulatingMessageGenerator;
import chessgameshared.messages.EncapsulatingMessage;
import model.enums.MessageType;
import com.google.gson.Gson;

public class EncapsulatingMessageGenerator implements IEncapsulatingMessageGenerator {
    private Gson gson = new Gson();

    public EncapsulatingMessage generateMessage(Object content) {
        String messageForServerJson = gson.toJson(content);
        MessageType type = MessageType.valueOf(content.getClass().toGenericString());
        return new EncapsulatingMessage(type, messageForServerJson);
    }
    public String generateMessageString(Object content){
        EncapsulatingMessage message = generateMessage(content);
        return gson.toJson(message);
    }
}
