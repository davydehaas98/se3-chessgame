package chessgameserver.messagehandlers;

import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.IGame;
import model.enums.MessageType;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {
    public IMessageHandler getHandler(MessageType type, Object game){
        IGame igame = (IGame) game;
        switch (type){
            default: return null;
        }
    }
}
