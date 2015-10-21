package rs.importer.service;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface Validator<T> {
    Collection<T> filter(Collection<T> links);
}
