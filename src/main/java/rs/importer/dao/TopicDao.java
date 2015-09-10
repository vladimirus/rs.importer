package rs.importer.dao;

import org.springframework.stereotype.Repository;
import rs.importer.model.Topic;

import java.util.Collection;

@Repository
public class TopicDao extends ModelDao<Topic> implements LocalDao<Topic> {
    private final static String TYPE = "topic";

    @Override
    public void save(Collection<Topic> collection) {
        save(collection, INDEX_NAME, TYPE);
    }
}
