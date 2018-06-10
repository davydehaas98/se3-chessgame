package chessgameshared.messages;

public class RegisterPlayerResultMessage {
    private boolean result;

    public RegisterPlayerResultMessage(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
