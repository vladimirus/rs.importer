package rs.importer.dao;


import java.util.Collection;

public interface LocalDao<T> {
    void save(Collection<T> collection);
}
