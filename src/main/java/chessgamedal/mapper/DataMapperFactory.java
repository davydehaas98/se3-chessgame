package chessgamedal.mapper;

import model.*;

public class DataMapperFactory {
    public static BaseDataMapper getMapper(Class entityType) {
        if(entityType.equals(Player.class)){
            return new PlayerDataMapper();
        }
//        else if(entityType.equals(Event.class)){
//            return new EventDataMapper();
//        }
        return null;
    }
}
