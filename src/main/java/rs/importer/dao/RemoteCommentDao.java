package rs.importer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import rs.importer.model.Comment;

import java.util.Collection;

@Repository
public class RemoteCommentDao extends AbstractRemoteDao<Comment> implements RemoteDao<Comment> {

    @Autowired
    public RemoteCommentDao(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Collection<Comment> get(Integer pageNumber, Integer size) {
        return get("comments", pageNumber, size, Comment[].class);
    }
}
