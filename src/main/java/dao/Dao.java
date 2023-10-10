package dao;

import java.util.List;

public interface Dao<K,T> {
    List<T> findAll();
    T findById(String id);
    boolean delete(K id);
    void update(K entity);
    boolean save(T entity);
}
