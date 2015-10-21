package rs.importer.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.importer.model.Topic;

@Service
public class TopicImporter extends AbstractImporter<Topic> {

    @Scheduled(initialDelay = 1000, fixedRate = 1800000) // 30 minutes
    public void importTopics() {
        doImport();
    }

    @Override
    protected String name() {
        return "topics";
    }
}
