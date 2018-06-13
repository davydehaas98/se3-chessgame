package chessgameshared.messages;

import model.Player;

public class LoginPlayerResultMessage {
    private Player player;

    public LoginPlayerResultMessage(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
