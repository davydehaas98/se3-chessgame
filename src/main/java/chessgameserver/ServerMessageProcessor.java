package chessgameserver;

import chessgameshared.MessageProcessorBase;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.enums.MessageType;

public class ServerMessageProcessor extends MessageProcessorBase {
    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory){
        super(messageHandlerFactory);
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        MessageType enumType = MessageType.valueOf(type.split("\\.")[type.split("\\.").length - 1]);
        IMessageHandler handler = getMessageHandlerFactory().getHandler(enumType, getGame());
        handler.handleMessage(data, sessionId);
    }

    @Override
    public void handleDisconnect(String sessionId) {
        getGame().processClientDisconnect(sessionId);
    }
}
