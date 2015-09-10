package rs.importer.dao;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import rs.importer.model.Model;

import java.util.Collection;
import java.util.List;

public abstract class ModelDao<T extends Model> {
    final static String INDEX_NAME = "rs2";

    @Autowired
    ElasticsearchTemplate template;

    void save(Collection<T> collection, String index, String type) {
        List<IndexQuery> queries = collection.stream()
                .map(item -> indexQuery(item, index, type))
                .collect(toList());

        if (!queries.isEmpty()) {
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
