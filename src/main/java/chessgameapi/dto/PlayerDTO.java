package chessgameapi.dto;

import java.util.Date;

public class PlayerDTO {
    private int playerId;
    private int name;
    private int ranking;
    private int wins;
    private int losses;
    private int draws;
    private Date dateCreated;

    public PlayerDTO() {

    }

    public PlayerDTO(int playerId, int name, int ranking, int wins, int losses,int draws, Date dateCreated) {
        this.playerId = playerId;
        this.name  = name;
        this.ranking = ranking;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.dateCreated = dateCreated;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
