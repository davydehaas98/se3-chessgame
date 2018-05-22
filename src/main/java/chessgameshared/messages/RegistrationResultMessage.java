package chessgameshared.messages;

import model.enums.Color;

public class RegistrationResultMessage {
    private boolean result;
    private Color color;
    public RegistrationResultMessage(boolean result, Color color){
        this.result = result;
        this.color = color;
    }

    public boolean isResult() {
        return result;
    }

    public Color getColor() {
        return color;
    }
}
