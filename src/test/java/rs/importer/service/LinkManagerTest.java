package rs.importer.service;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.verify;
import static rs.importer.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.importer.dao.LocalDao;
import rs.importer.dao.RemoteDao;
import rs.importer.model.Link;

@RunWith(MockitoJUnitRunner.class)
public class LinkManagerTest {
    @InjectMocks
    private LinkManager linkManager;
    @Mock
    private LocalDao<Link> localDao;
    @Mock
    private RemoteDao<Link> remoteDao;

    @Test
    public void shouldGet() {

        // when
        linkManager.get(0, 50);

        // then
        verify(remoteDao).get(0, 50);
    }

    @Test
    public void shouldSave() {

        // when
        linkManager.save(asList(aLink("1"), aLink("2")));

        // then
        verify(localDao).save(anyCollectionOf(Link.class));
    }
}