package rs.importer.dao;

import org.springframework.stereotype.Repository;
import rs.importer.model.Link;

import java.util.Collection;

@Repository
public class LinkDao extends ModelDao<Link> implements LocalDao<Link> {
    private final static String TYPE = "link";

    @Override
    public void save(Collection<Link> collection) {
        save(collection, INDEX_NAME, TYPE);
    }
}
