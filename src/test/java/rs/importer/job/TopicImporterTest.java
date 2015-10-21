package rs.importer.job;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
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
import rs.importer.service.Validator;

@RunWith(MockitoJUnitRunner.class)
public class TopicImporterTest {
    @InjectMocks
    private TopicImporter topicImporter;
    @Mock
    private RemoteManager<Topic> remoteManager;
    @Mock
    private LocalManager<Topic> localManager;
    @Mock
    private Validator<Topic> validator;

    @Test
    public  void shouldImport() {
        // given
        given(remoteManager.get(0, 1000)).willReturn(asList(aTopic("1"), aTopic("2")));
        given(remoteManager.get(1, 1000)).willReturn(emptyList());
        given(validator.filter(anyCollectionOf(Topic.class))).willReturn(singletonList(aTopic("1")));
        topicImporter.maxEmptyCount = 0;

        // when
        topicImporter.importTopics();

        // then
        verify(remoteManager, times(2)).get(anyInt(), anyInt());
        verify(localManager, times(1)).save(anyCollectionOf(Topic.class));
    }
}