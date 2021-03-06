package chessgameserver.messagehandlers;

import chessgameshared.interfaces.IMessageHandler;
import chessgameshared.interfaces.IMessageHandlerFactory;
import model.enums.MessageType;
import model.interfaces.IGame;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {
    public IMessageHandler getHandler(String type, Object game) {
        IGame igame = (IGame) game;
        switch (MessageType.valueOf(type)) {
            case RegisterPlayerMessage:
                return new RegisterPlayerMessageHandler(igame);
            case RequestPasswordMessage:
                return new RequestPasswordMessageHandler(igame);
            case LoginPlayerMessage:
                return new LoginPlayerMessageHandler(igame);
            case LogoutPlayerMessage:
                return new LogoutPlayerMessageHandler(igame);
            case MakeMoveMessage:
                return new MakeMoveMessageHandler(igame);
            default:
                return null;
        }
    }
}
