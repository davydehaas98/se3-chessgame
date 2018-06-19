package chessgameshared.messages;

import model.pieces.Piece;

import java.awt.*;

public class RequestLegalMovesMessage {
    private Piece piece;

    public RequestLegalMovesMessage(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
