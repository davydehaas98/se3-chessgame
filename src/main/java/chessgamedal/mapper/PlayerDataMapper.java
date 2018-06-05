package chessgamedal.mapper;

import model.Player;

public class PlayerDataMapper<T> extends BaseDataMapper<Player> {
    @Override
    public String mapToSQLInternal(Player object) {
        if(object.getEntityId() == 0){
            return String.format("INSERT INTO player (name, password, ranking, wins, losses,draws) VALUES ('{0}','{1}', {2}, {3}, {4} , {5}", object.getName(), object.getPassword(), 500,0,0,0);
        }
            return null;
    }
}
