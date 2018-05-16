package chessgameclient;

import chessgameclient.interfaces.IClientMessageProcessor;
import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageProcessorBase;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;

public class ClientMessageProcessor extends MessageProcessorBase implements IClientMessageProcessor {
    private IGameClient gameClient;

    public ClientMessageProcessor(IMessageHandlerFactory messageHandlerFactory) {
        super(messageHandlerFactory);
    }

    public void registerGameClient(IGameClient gameClient) {
        this.gameClient = gameClient;
    }

    @Override
    public void handleDisconnect(String sessionId) {
        //Do nothing?
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        String messageType = type.split("\\.")[type.split("\\.").length - 1];
        IMessageHandler handler = getMessageHandlerFactory().getHandler(messageType, gameClient);
        handler.handleMessage(data, sessionId);
    }
}
