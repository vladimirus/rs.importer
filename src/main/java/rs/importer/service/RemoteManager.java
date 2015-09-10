package rs.importer.service;

import java.util.Collection;

public interface RemoteManager<T> {
    Collection<T> get(Integer pageNumber, Integer size);
}
