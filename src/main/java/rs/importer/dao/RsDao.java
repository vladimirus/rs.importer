package rs.importer.dao;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import rs.importer.model.Topic;

import java.util.Arrays;
import java.util.Collection;

@Repository
public class RsDao implements RemoteDao<Topic> {
    private RestTemplate restTemplate;

    @Autowired
    public RsDao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Topic> get(Integer pageNumber, Integer size) {
        ResponseEntity<Topic[]> responseEntity =
                restTemplate.getForEntity(format("http://rs.supr.me/api/topics?pageNumber=%d&size=%d", pageNumber, size), Topic[].class);

        return ofNullable(responseEntity.getBody()).map(Arrays::asList).orElse(emptyList());
    }
}
