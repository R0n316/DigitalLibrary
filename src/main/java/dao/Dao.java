package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K,T> {
    List<T> findAll();
    T findById(String id);
    boolean delete(K id);
    void update(K entity);
    T save(T entity);
}
