package rs.importer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.importer.dao.RemoteDao;
import rs.importer.dao.LocalDao;
import rs.importer.model.Topic;

import java.util.Collection;

@Service
public class TopicManager implements RemoteManager<Topic>, LocalManager<Topic> {
    @Autowired
    private LocalDao<Topic> localDao;
    @Autowired
    private RemoteDao<Topic> remoteDao;


    @Override
    public Collection<Topic> get(Integer pageNumber, Integer size) {
        return remoteDao.get(pageNumber, size);
    }

    @Override
    public void save(Collection<Topic> collection) {
        localDao.save(collection);
    }
}
