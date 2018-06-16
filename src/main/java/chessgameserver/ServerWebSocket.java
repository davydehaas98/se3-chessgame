package chessgameserver;

import chessgameserver.interfaces.IServerWebSocket;
import chessgameshared.WebSocketBase;
import chessgameshared.logging.LogLevel;
import chessgameshared.logging.Logger;
import chessgameshared.messages.EncapsulatedMessage;

import javax.inject.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@Singleton
@ServerEndpoint(value = "/chessgame/")
public class ServerWebSocket extends WebSocketBase implements IServerWebSocket {
    private static ArrayList<Session> sessions = new ArrayList<>();

    @Override
    public void start() {
        //Start is handled elsewhere, so it is not needed here
    }

    @Override
    public void stop() {
        //Stop is handled elsewhere, so it is not needed here
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
        EncapsulatedMessage encapMessage = getGson().fromJson(message, EncapsulatedMessage.class);
        getHandler().processMessage(session.getId(), encapMessage.getMessageType(), encapMessage.getMessageData());
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("[Connected] SessionId: " + session.getId());
        sessions.add(session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        getHandler().handleDisconnect(session.getId());
        System.out.println("[Disconnect] SessionId: " + session.getId());
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    public void sendTo(String sessionId, Object object) {
        String EncapMessage = getEncapsulatedMessageGenerator().generateEncapsulatedMessageString(object);
        try {
            getSessionFromId(sessionId).getBasicRemote().sendText(EncapMessage);
        } catch (IOException exc) {
            Logger.getInstance().log(exc);
        }
    }

    public Session getSessionFromId(String sessionId) {
        for (Session s : sessions) {
            if (s.getId().equals(sessionId))
                return s;
        }
        return null;
    }

    public void broadcast(Object object) {
        for (Session session : sessions) {
            sendTo(session.getId(), object);
        }
    }

    public void sendToOthers(String sessionId, Object object) {
        for (Session session : sessions) {
            if (!session.getId().equals(sessionId)) {
                sendTo(session.getId(), object);
            }
        }
    }
}
