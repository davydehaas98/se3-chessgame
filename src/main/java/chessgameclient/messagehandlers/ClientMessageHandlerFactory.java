package chessgameclient.messagehandlers;

import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import chessgameclient.interfaces.IGameClient;
import model.enums.MessageType;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {
    public IMessageHandler getHandler(String type, Object gameClient){
        IGameClient igameClient = (IGameClient) gameClient;
        switch (MessageType.valueOf(type)){
            case RegistrationResultMessage:
                return new RegistrationResultMessageHandler(igameClient);
            case AnotherPlayerRegistered:
                return new AnotherPlayerRegisteredMessageHandler(igameClient);
            case StartGameMessage:
                return new StartGameMessageHandler(igameClient);
            case EndGameMessage:
                return new EndGameMessageHandler(igameClient);
            case UpdateBoardMessage:
                return new UpdateBoardMessageHandler(igameClient);
            case NextTurnMessage:
                return new NextTurnMessageHandler(igameClient);
            default:
                return null;
        }
    }
}
