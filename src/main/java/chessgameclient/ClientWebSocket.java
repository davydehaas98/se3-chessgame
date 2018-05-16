package chessgameclient;

import chessgameclient.interfaces.IClientMessageProcessor;
import chessgameclient.interfaces.IClientWebSocket;
import chessgameshared.WebSocketBase;
import chessgameshared.logging.LogLevel;
import chessgameshared.logging.Logger;
import chessgameshared.messages.EncapsulatingMessage;
import javafx.application.Platform;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebSocket extends WebSocketBase implements IClientWebSocket {
    private static final String serverURI = "ws://localhost:8008/chessgame/";
    private Session session;
    private IClientMessageProcessor handler;
    private static ClientWebSocket instance = null;

    public static ClientWebSocket getInstance() {
        if (instance == null) {
            instance = new ClientWebSocket();
        }
        return instance;
    }

    @Override
    public void setMessageHandler(IClientMessageProcessor handler) {
        this.handler = handler;
    }

    @Override
    public void start() {
        try {
            WebSocketContainer wsContainer = ContainerProvider.getWebSocketContainer();
            wsContainer.connectToServer(this, new URI(serverURI));
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        }
    }

    @Override
    public void stop() {
        try {
            if (session != null) {
                session.close();
            }
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose(CloseReason reason) {
        session = null;
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        onMessageReceived(message, session.getId());
    }

    @OnError
    public void onError(Session session, Throwable cause) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }
    @Override
    public void onMessageReceived(String message, String sessionId) {
        EncapsulatingMessage encapMessage = getGson().fromJson(message, EncapsulatingMessage.class);
        handler.processMessage(sessionId, encapMessage.getMessageType(), encapMessage.getMessageData());
    }
    @Override
    public void onMessageSend(Object object) {
        String message = getEncapsulatingMessageGenerator().generateMessageString(object);
        sendMessageToServer(message);
    }

    private void sendMessageToServer(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }
}
