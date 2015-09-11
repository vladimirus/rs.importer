package rs.importer.job;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.importer.model.Topic;
import rs.importer.service.LocalManager;
import rs.importer.service.RemoteManager;

@Service
public class TopicImporter {
    private Logger log = Logger.getLogger(TopicImporter.class);
    @Autowired
    private RemoteManager<Topic> remoteManager;
    @Autowired
    private LocalManager<Topic> localManager;

    int pagesToCheck = 600;

    @Scheduled(initialDelay = 1000, fixedRate = 1800000) // 30 minutes
    public void importTopics() {

        range(0, pagesToCheck)
                .peek(i -> log.debug(format("Importing topics, page %d", i)))
                .mapToObj(i -> remoteManager.get(i, 1000))
                .peek(list -> log.debug(format("Got %d topics", list.size())))
                .filter(list -> !list.isEmpty())
                .forEach(localManager::save);
    }
}
