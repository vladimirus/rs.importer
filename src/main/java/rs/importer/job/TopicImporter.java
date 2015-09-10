package rs.importer.job;

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
    private RemoteManager<Topic> topicManager;
    @Autowired
    private LocalManager<Topic> localManager;

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void importTopics() {
        log.debug("importing topics");
        localManager.save(topicManager.get(0, 50));
    }
}
