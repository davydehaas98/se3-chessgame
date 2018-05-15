package chessgameshared.messages;

import model.enums.MessageType;
public class EncapsulatingMessage {
    private MessageType messageType;
    private String messageData;

    public EncapsulatingMessage(MessageType type, String data) {
        this.messageType = type;
        this.messageData = data;
    }

    public String getMessageData() {
        return messageData;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
