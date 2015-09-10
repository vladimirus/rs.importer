package rs.importer.dao;


import java.util.Collection;

public interface RemoteDao<T> {
    Collection<T> get(Integer pageNumber, Integer size);
}
