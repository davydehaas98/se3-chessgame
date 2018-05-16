package chessgameserver.messagehandlers;

import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.interfaces.IGame;
import model.enums.MessageType;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {
    public IMessageHandler getHandler(String type, Object game){
        IGame igame = (IGame) game;
        switch (MessageType.valueOf(type)){
            case RegisterPlayerMessage:
                return new RegisterPlayerMessageHandler(igame);
            default: return null;
        }
    }
}
