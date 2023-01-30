package dao;

import java.util.List;

public interface Repository<T> {
    public int add(T z);
    public int delete(int id);
    public int update(T z);
    public List<T> list();
    public T get(int id);
}
