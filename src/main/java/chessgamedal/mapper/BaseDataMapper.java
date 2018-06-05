package chessgamedal.mapper;

import java.sql.ResultSet;
import java.util.List;

public abstract class BaseDataMapper<T> {
    public List<T> mapFromDatabase(ResultSet resultSet) {
        return mapFromDatabaseInternal(resultSet);
    }

    public String mapToSQL(Object object) {
        return mapToSQLInternal((T) object);
    }

    public abstract String mapToSQLInternal(T object);

    public abstract List<T> mapFromDatabaseInternal(ResultSet resultSet);
}
