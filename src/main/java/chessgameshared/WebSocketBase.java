package chessgameshared;

import chessgameshared.interfaces.IEncapsulatingMessageGenerator;
import chessgameshared.interfaces.IMessageProcessor;
import chessgameshared.interfaces.IWebSocket;
import com.google.gson.Gson;

public abstract class WebSocketBase implements IWebSocket {
    private IMessageProcessor handler;
    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator;
    private Gson gson;

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    public WebSocketBase() {
        encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();
        gson = new Gson();
    }
    public Gson getGson(){
        return gson;
    }
    public IMessageProcessor getHandler() {
        return handler;
    }

    public void setMessageHandler(IMessageProcessor handler) {
        this.handler = handler;
    }

    public abstract void start();

    public abstract void stop();
}
