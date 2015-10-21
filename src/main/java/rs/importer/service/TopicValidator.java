package rs.importer.service;

import org.springframework.stereotype.Service;
import rs.importer.model.Topic;

import java.util.Collection;

@Service
public class TopicValidator implements Validator<Topic>{
    public Collection<Topic> filter(Collection<Topic> items) {
        return items;
    }
}
