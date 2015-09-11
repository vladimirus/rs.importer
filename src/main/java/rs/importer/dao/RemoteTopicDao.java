package rs.importer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import rs.importer.model.Topic;

import java.util.Collection;

@Repository
public class RemoteTopicDao extends AbstractRemoteDao<Topic> implements RemoteDao<Topic> {

    @Autowired
    public RemoteTopicDao(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Topic> get(Integer pageNumber, Integer size) {
        return get("topics", pageNumber, size, Topic[].class);
    }
}
