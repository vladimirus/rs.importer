package rs.importer.dao;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import rs.importer.model.Model;

import java.util.Collection;
import java.util.List;

public abstract class AbstractModelDao<T extends Model> {
    private Logger log = Logger.getLogger(AbstractModelDao.class);
    @Value("${rs.index.name}")
    String indexName;

    @Autowired
    ElasticsearchTemplate template;

    void save(Collection<T> collection, String index, String type) {
        List<IndexQuery> queries = collection.stream()
                .map(item -> indexQuery(item, index, type))
                .collect(toList());

        if (!queries.isEmpty()) {
            log.debug(format("Saving %d %s into %s", collection.size(), type, index));
            template.bulkIndex(queries);
        }
    }

    IndexQuery indexQuery(T item, String index, String type) {
        return new IndexQueryBuilder()
                .withObject(item)
                .withId(item.getId())
                .withIndexName(index)
                .withType(type)
                .build();
    }
}
