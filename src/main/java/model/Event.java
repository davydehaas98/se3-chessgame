package model;

import java.util.Date;

public class Event  extends Entity{
    private Player player;
    private Tile from;
    private Tile to;
    private int turn;
    private Date time;

    Event(Player player, Tile from, Tile to, int turn, Date time) {
        this.player = player;
        this.from = from;
        this.to = to;
        this.turn = turn;
        this.time = time;
    }

    public String getInfo() {
        return String.format("[%s]:[%s] player: %s played %s from %s to %s.", turn, time.toString(), player.getName(), from.getPiece().getPieceType().toString(), from.getName(), to.getName());
    }
}
