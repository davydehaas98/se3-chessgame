package chessgameapi.dto;

import model.Player;

public class LoginResultDTO extends BaseResultDTO{
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
