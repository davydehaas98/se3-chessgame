package chessgameshared.interfaces;

public interface IMessageHandlerFactory {
    IMessageHandler getHandler(String type, Object game);
}
