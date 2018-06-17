package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.enums.MessageType;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {
    //This Class handles the message send from the Server to the Client
    //For every type of message this class will return the corresponding handler
    //In every handler the handleMessageInternal Method is different
    public IMessageHandler getHandler(String type, Object gc) {
        IGameClient gameClient = (IGameClient) gc;
        switch (MessageType.valueOf(type)) {
            case RegisterPlayerResultMessage:
                return new RegisterPlayerResultMessageHandler(gameClient);
            case RequestPasswordResultMessage:
                return new RequestPasswordResultMessageHandler(gameClient);
            case LoginPlayerResultMessage:
                return new LoginPlayerResultMessageHandler(gameClient);
            case StartGameMessage:
                return new StartGameMessageHandler(gameClient);
            case EndGameMessage:
                return new EndGameMessageHandler(gameClient);
            case UpdateBoardMessage:
                return new UpdateBoardMessageHandler(gameClient);
            case NextTurnMessage:
                return new NextTurnMessageHandler(gameClient);
            case EventsMessage:
                return new EventsMessageHandler(gameClient);
            default:
                return null;
        }
    }
}
