package rs.importer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.importer.dao.LocalDao;
import rs.importer.dao.RemoteDao;
import rs.importer.model.Link;

import java.util.Collection;

@Service
public class LinkManager implements RemoteManager<Link>, LocalManager<Link> {
    @Autowired
    private LocalDao<Link> localDao;
    @Autowired
    private RemoteDao<Link> remoteDao;


    @Override
    public Collection<Link> get(Integer pageNumber, Integer size) {
        return remoteDao.get(pageNumber, size);
    }

    @Override
    public void save(Collection<Link> collection) {
        localDao.save(collection);
    }
}
