package rs.importer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import rs.importer.model.Link;

import java.util.Collection;

@Repository
public class RemoteLinkDao extends AbstractRemoteDao<Link> implements RemoteDao<Link> {

    @Autowired
    public RemoteLinkDao(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Link> get(Integer pageNumber, Integer size) {
        return get("links", pageNumber, size, Link[].class);
    }
}
