package rs.importer.dao;

import org.springframework.stereotype.Repository;
import rs.importer.model.Comment;

import java.util.Collection;

@Repository
public class CommentDao extends AbstractModelDao<Comment> implements LocalDao<Comment> {
    private final static String TYPE = "comment";

    @Override
    public void save(Collection<Comment> collection) {
        save(collection, indexName, TYPE);
    }
}
