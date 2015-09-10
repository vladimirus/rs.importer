package rs.importer.dao;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RsDaoTest {
    @InjectMocks
    private RsDao rsDao;
    @Mock
    private RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void should() {
        // given
        ResponseEntity entity = mock(ResponseEntity.class);
        given(restTemplate.getForEntity(anyString(), any())).willReturn(entity);

        // when
        rsDao.get(0, 50);

        // then
        verify(restTemplate).getForEntity(anyString(), any());
    }
}