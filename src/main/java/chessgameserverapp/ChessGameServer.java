package chessgameserverapp;

import chessgameserver.ServerMessageGenerator;
import chessgameserver.ServerMessageProcessor;
import chessgameserver.ServerWebSocket;
import chessgameserver.interfaces.IServerMessageGenerator;
import chessgameserver.messagehandlers.ServerMessageHandlerFactory;
import chessgameshared.interfaces.IMessageHandlerFactory;
import chessgameshared.interfaces.IMessageProcessor;
import chessgameshared.logging.Logger;
import model.Game;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class ChessGameServer {
    private static final int PORT = 8008;

    public static void main(String[] args) {
        IMessageHandlerFactory factory = new ServerMessageHandlerFactory();
        IMessageProcessor messageHandler = new ServerMessageProcessor(factory);
        final ServerWebSocket serverWebSocket = new ServerWebSocket();
        serverWebSocket.setMessageHandler(messageHandler);

        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(serverWebSocket);
        messageHandler.registerGame(new Game(messageGenerator));

        //Setup handler tree at "/"
        Server wsServer = new Server();
        ServerConnector connector = new ServerConnector(wsServer);
        connector.setPort(PORT);
        wsServer.addConnector(connector);
        ServletContextHandler wsContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        wsContextHandler.setContextPath("/");
        wsServer.setHandler(wsContextHandler);

        try {
            ServerEndpointConfig.Configurator configurator = new ServerEndpointConfig.Configurator() {
                @Override
                public <T> T getEndpointInstance(Class<T> endpointClass) {
                    return (T) serverWebSocket;
                }
            };
            //Initialize JavaX Websocket layer
            ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(wsContextHandler);
            //Add Webosocket endpoint to JavaX Websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(serverWebSocket.getClass(), serverWebSocket.getClass().getAnnotation(ServerEndpoint.class).value()).configurator(configurator).build();
            wsContainer.addEndpoint(config);
            wsServer.start();
            wsServer.join();
        } catch (Exception exc) {
            Logger.getInstance().log(exc);
        } finally {
            wsServer.destroy();
        }
    }
}
