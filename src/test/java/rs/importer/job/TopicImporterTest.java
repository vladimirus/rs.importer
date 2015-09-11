package rs.importer.job;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static rs.importer.TestFactory.aTopic;

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
    private RemoteManager<Topic> remoteManager;
    @Mock
    private LocalManager<Topic> localManager;

    @Test
    public  void shouldImport() {
        // given
        given(remoteManager.get(0, 1000)).willReturn(asList(aTopic("1"), aTopic("2")));
        topicImporter.pagesToCheck = 2;

        // when
        topicImporter.importTopics();

        // then
        verify(remoteManager, times(2)).get(anyInt(), anyInt());
        verify(localManager, times(1)).save(anyCollectionOf(Topic.class));
    }
}