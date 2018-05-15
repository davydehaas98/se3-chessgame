package chessgameshared.interfaces;

import model.enums.MessageType;

public interface IMessageHandlerFactory {
    IMessageHandler getHandler(MessageType messageType, Object game);
}
