package chessgameserver;

import chessgameserver.interfaces.IServerMessageProcessor;
import chessgameshared.MessageProcessorBase;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;

public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {
    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory) {
        super(messageHandlerFactory);
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        String messageType = type.split("\\.")[type.split("\\.").length - 1];
        IMessageHandler handler = getMessageHandlerFactory().getHandler(messageType, getGame());
        handler.handleMessage(data, sessionId);
    }

    @Override
    public void handleDisconnect(String sessionId) {
        getGame().logoutPlayer(sessionId);
    }
}
