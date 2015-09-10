package rs.importer.service;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.verify;
import static rs.importer.TestFactory.aTopic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.importer.dao.RemoteDao;
import rs.importer.dao.LocalDao;
import rs.importer.model.Topic;

@RunWith(MockitoJUnitRunner.class)
public class TopicManagerTest {
    @InjectMocks
    private TopicManager topicManager;
    @Mock
    private LocalDao<Topic> localDao;
    @Mock
    private RemoteDao<Topic> remoteDao;

    @Test
    public void shouldGet() {

        // when
        topicManager.get(0, 50);

        // then
        verify(remoteDao).get(0, 50);
    }

    @Test
    public void shouldSave() {

        // when
        topicManager.save(asList(aTopic("1"), aTopic("2")));

        // then
        verify(localDao).save(anyCollectionOf(Topic.class));
    }
}