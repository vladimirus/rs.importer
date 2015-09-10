package rs.importer.dao;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static rs.importer.TestFactory.aTopic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@RunWith(MockitoJUnitRunner.class)
public class TopicDaoTest {
    @InjectMocks
    private TopicDao topicDao;
    @Mock
    private ElasticsearchTemplate template;

    @Test
    public void shouldSaveCollection() {

        // when
        topicDao.save(asList(aTopic("1"), aTopic("2")));

        // then
        verify(template).bulkIndex(any());
    }
}