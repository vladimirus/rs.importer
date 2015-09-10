package rs.importer.service;

import java.util.Collection;

public interface LocalManager<T> {
    void save(Collection<T> collection);
}
