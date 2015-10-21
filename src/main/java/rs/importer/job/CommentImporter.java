package rs.importer.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.importer.model.Comment;

@Service
public class CommentImporter extends AbstractImporter<Comment> {

    @Scheduled(initialDelay = 3000, fixedRate = 1800000) // 30 minutes
    public void importTopics() {
        doImport();
    }

    @Override
    protected String name() {
        return "comments";
    }
}
