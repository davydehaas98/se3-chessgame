package chessgameshared.messages;

import model.Tile;

public class UpdateBoardMessage {
    private Tile[][] board;
    public UpdateBoardMessage(Tile[][] board){
        this.board = board;
    }

    public Tile[][] getBoard() {
        return board;
    }
}
