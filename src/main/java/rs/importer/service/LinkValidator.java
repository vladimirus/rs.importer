package rs.importer.service;

import static java.util.stream.Collectors.toList;

import org.springframework.stereotype.Service;
import rs.importer.model.Link;

import java.util.Collection;

@Service
public class LinkValidator {

    public Collection<Link> filter(Collection<Link> links) {
        return links.stream()
                .filter(link -> link.getScore() >= 100)
                .collect(toList());
    }
}
