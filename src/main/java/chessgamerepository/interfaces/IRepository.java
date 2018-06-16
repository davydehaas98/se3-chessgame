package chessgamerepository.interfaces;

import java.util.List;

public interface IRepository<T> {
    T getByID(int id);

    List<T> getAll();
//    void insert(T object);
//    void delete(int id);
//    void update(T entity);
}
