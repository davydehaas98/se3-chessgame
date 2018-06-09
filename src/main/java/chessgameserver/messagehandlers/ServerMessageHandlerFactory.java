package chessgameserver.messagehandlers;

import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.enums.MessageType;
import model.interfaces.IGame;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {
    public IMessageHandler getHandler(String type, Object game){
        IGame igame = (IGame) game;
        switch (MessageType.valueOf(type)){
            case RegisterPlayerMessage:
                return new RegisterPlayerMessageHandler(igame);
            case MakeMoveMessage:
                return new MakeMoveMessageHandler(igame);
            case PlayerDisconnectMessage:
                return new PlayerDisconnectMessageHandler(igame);
            default: return null;
        }
    }
}
