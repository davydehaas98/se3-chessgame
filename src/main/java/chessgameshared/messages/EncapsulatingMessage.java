package chessgameshared.messages;

public class EncapsulatingMessage {
    private String messageType;
    private String messageData;

    public EncapsulatingMessage(String messageType, String messageData) {
        this.messageType = messageType;
        this.messageData = messageData;
    }

    public String getMessageData() {
        return messageData;
    }

    public String getMessageType() {
        return messageType;
    }
}
