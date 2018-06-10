package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.enums.MessageType;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {
    //This Class handles the message send from the Server to the Client
    //For every type of message this class will return the corresponding handler
    //In every handler the handleMessageInternal Method is different
    public IMessageHandler getHandler(String type, Object gameClient){
        IGameClient igameClient = (IGameClient) gameClient;
        switch (MessageType.valueOf(type)){
            case RegistrationResultMessage:
                return new RegistrationResultMessageHandler(igameClient);
            case RequestPasswordResultMessage:
                return new RequestPasswordResultMessageHandler(igameClient);
            case LoginPlayerResultMessage:
                return new LoginPlayerResultMessageHandler(igameClient);
            case StartGameMessage:
                return new StartGameMessageHandler(igameClient);
            case EndGameMessage:
                return new EndGameMessageHandler(igameClient);
            case UpdateBoardMessage:
                return new UpdateBoardMessageHandler(igameClient);
            case NextTurnMessage:
                return new NextTurnMessageHandler(igameClient);
            case NewEventMessage:
                return new NewEventMessageHandler(igameClient);
            default:
                return null;
        }
    }
}
