package rs.importer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.importer.dao.LocalDao;
import rs.importer.dao.RemoteDao;
import rs.importer.model.Comment;

import java.util.Collection;

@Service
public class CommentManager implements RemoteManager<Comment>, LocalManager<Comment> {
    @Autowired
    private LocalDao<Comment> localDao;
    @Autowired
    private RemoteDao<Comment> remoteDao;


    @Override
    public Collection<Comment> get(Integer pageNumber, Integer size) {
        return remoteDao.get(pageNumber, size);
    }

    @Override
    public void save(Collection<Comment> collection) {
        localDao.save(collection);
    }
}
