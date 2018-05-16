package chessgameshared.messages;

public class EncapsulatingMessage {
    private String messageType;
    private String messageData;

    public EncapsulatingMessage(String type, String data) {
        this.messageType = type;
        this.messageData = data;
    }

    public String getMessageData() {
        return messageData;
    }

    public String getMessageType() {
        return messageType;
    }
}
