package chessgameshared.messages;

import model.pieces.Piece;

import java.awt.*;
import java.util.ArrayList;

public class RequestLegalMovesResultMessage {
    private Piece piece;
    private ArrayList<Point> legalMoves;

    public RequestLegalMovesResultMessage(Piece piece, ArrayList<Point> legalMoves) {
        this.piece = piece;
        this.legalMoves = legalMoves;
    }

    public Piece getPiece() {
        return piece;
    }

    public ArrayList<Point> getLegalMoves() {
        return legalMoves;
    }
}
