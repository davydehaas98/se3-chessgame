package chessgameshared.messages;

import model.enums.Color;

public class NextTurnMessage {
    private int turn;
    private Color turnColor;
    public NextTurnMessage(int turn, Color turnColor) {
        this.turn = turn;
        this.turnColor = turnColor;
    }

    public int getTurn() {
        return turn;
    }

    public Color getTurnColor() {
        return turnColor;
    }
}
