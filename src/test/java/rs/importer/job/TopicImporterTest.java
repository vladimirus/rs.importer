package rs.importer.job;

import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.importer.model.Topic;
import rs.importer.service.LocalManager;
import rs.importer.service.RemoteManager;

@RunWith(MockitoJUnitRunner.class)
public class TopicImporterTest {
    @InjectMocks
    private TopicImporter topicImporter;
    @Mock
    private RemoteManager<Topic> topicManager;
    @Mock
    private LocalManager<Topic> localManager;

    @Test
    public  void shouldImport() {

        // when
        topicImporter.importTopics();

        // then
        verify(topicManager).get(0, 50);
        verify(localManager).save(anyCollectionOf(Topic.class));
    }

}