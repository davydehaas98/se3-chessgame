package chessgameclient.messagehandlers;

import chessgameclient.interfaces.IGameClient;
import chessgameshared.MessageHandler;
import chessgameshared.messages.UpdateBoardMessage;

public class UpdateBoardMessageHandler extends MessageHandler<UpdateBoardMessage> {
    public UpdateBoardMessageHandler(IGameClient gameClient) {
        super(gameClient);
    }

    @Override
    public void handleMessageInternal(UpdateBoardMessage message, String sessionId) {
        getGameClient().handleUpdateBoard(message.getBoard());
    }
}
