package chessgamedal.interfaces;

import java.util.List;

public interface IContext<T> {
    T getByID(int id);

    List<T> getAll();

//    void insert(T object);

//    void update(T object);

//    void delete(int id);
}
