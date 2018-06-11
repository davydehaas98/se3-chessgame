package chessgameshared.messages;

public class EncapsulatedMessage {
    private String messageType;
    private String messageData;

    public EncapsulatedMessage(String messageType, String messageData) {
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
