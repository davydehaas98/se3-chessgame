package chessgameshared;

import chessgameshared.interfaces.IMessageHandlerFactory;
import chessgameshared.interfaces.IMessageProcessor;
import com.google.gson.Gson;
import model.IGame;
import model.enums.MessageType;

public abstract class MessageProcessorBase implements IMessageProcessor {
    private IGame game;
    private IMessageHandlerFactory messageHandlerFactory;
    private Gson gson;

    public MessageProcessorBase(IMessageHandlerFactory messageHandlerFactory){
        this.messageHandlerFactory = messageHandlerFactory;
        gson = new Gson();
    }

    public IGame getGame() {
        return game;
    }

    public IMessageHandlerFactory getMessageHandlerFactory() {
        return messageHandlerFactory;
    }

    public Gson getGson() {
        return gson;
    }
    public void registerGame(IGame game){
        this.game = game;
    }
    public abstract void processMessage(String sessionId, String type, String data);
    public abstract void handleDisconnect(String sessionId);
}
