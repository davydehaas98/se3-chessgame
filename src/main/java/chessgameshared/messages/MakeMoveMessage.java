package chessgameshared.messages;

import java.awt.*;

public class MakeMoveMessage {
    private Point from;
    private Point to;

    public MakeMoveMessage(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }
}
