package rs.importer.dao;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static rs.importer.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@RunWith(MockitoJUnitRunner.class)
public class LinkDaoTest {
    @InjectMocks
    private LinkDao linkDao;
    @Mock
    private ElasticsearchTemplate template;

    @Test
    public void shouldSaveCollection() {

        // when
        linkDao.save(asList(aLink("1"), aLink("2")));

        // then
        verify(template).bulkIndex(any());
    }
}