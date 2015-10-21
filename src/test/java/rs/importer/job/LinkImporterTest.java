package rs.importer.job;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static rs.importer.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.importer.model.Link;
import rs.importer.service.LocalManager;
import rs.importer.service.RemoteManager;
import rs.importer.service.Validator;

@RunWith(MockitoJUnitRunner.class)
public class LinkImporterTest {
    @InjectMocks
    private LinkImporter linkImporter;
    @Mock
    private RemoteManager<Link> remoteManager;
    @Mock
    private LocalManager<Link> localManager;
    @Mock
    private Validator<Link> validator;

    @Test
    public  void shouldImport() {
        // given
        given(remoteManager.get(0, 1000)).willReturn(asList(aLink("1"), aLink("2")));
        given(remoteManager.get(1, 1000)).willReturn(emptyList());
        given(remoteManager.get(2, 1000)).willReturn(emptyList());
        given(validator.filter(anyCollectionOf(Link.class))).willReturn(singletonList(aLink("2")));
        linkImporter.maxEmptyCount = 1;

        // when
        linkImporter.importLinks();

        // then
        verify(remoteManager, times(3)).get(anyInt(), anyInt());
        verify(validator).filter(anyCollectionOf(Link.class));
        verify(localManager).save(anyCollectionOf(Link.class));
    }
}