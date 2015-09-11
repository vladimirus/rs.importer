package rs.importer.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import rs.importer.dao.RemoteTopicDao;
import rs.importer.model.Topic;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class RsDaoIT {
    private RemoteTopicDao remoteTopicDao;

    @Before
    public void before() {
        remoteTopicDao = new RemoteTopicDao(new RestTemplate());
    }

    @Test
    public void shouldConnect() {

        // when
        Collection<Topic> actual = remoteTopicDao.get(0, 50);

        // then
        assertThat(actual, hasSize(50));
    }


}
