package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.enums.MessageType;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {
    public IMessageHandler getHandler(String type, Object gameClient){
        IGameClient igameClient = (IGameClient) gameClient;
        switch (MessageType.valueOf(type)){
            case RegistrationResultMessage:
                return new RegistrationResultMessageHandler(igameClient);
            case PlayerHasRegisteredMessage:
                return new PlayerHasRegisteredMessageHandler(igameClient);
            default:
                return null;
        }
    }
}
