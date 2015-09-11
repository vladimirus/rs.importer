package rs.importer.dao;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

public abstract class AbstractRemoteDao<T> {
    private Logger log = Logger.getLogger(AbstractRemoteDao.class);
    private RestTemplate restTemplate;
    @Value("${rs.base.url}")
    private String rsBaseUrl;

    public AbstractRemoteDao(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public Collection<T> get(String name, Integer pageNumber, Integer size, Class clazz) {
        String url = format("http://%s/api/%s?pageNumber=%d&size=%d", rsBaseUrl, name, pageNumber, size);
        log.debug(format("Connecting to %s", url));
        ResponseEntity<T[]> responseEntity = restTemplate.getForEntity(url, clazz);
        return ofNullable(responseEntity.getBody()).map(Arrays::asList).orElse(emptyList());
    }
}
