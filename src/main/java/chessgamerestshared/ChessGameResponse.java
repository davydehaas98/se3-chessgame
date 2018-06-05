package chessgamerestshared;

import java.util.List;

public class ChessGameResponse {
    private boolean succes;
    private List<PlayerDTO> players;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
