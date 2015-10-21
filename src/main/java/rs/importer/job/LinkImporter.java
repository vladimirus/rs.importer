package rs.importer.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.importer.model.Link;

@Service
public class LinkImporter extends AbstractImporter<Link> {

    @Scheduled(initialDelay = 2000, fixedRate = 1800000) // 30 minutes
    public void importLinks() {
        doImport();
    }

    @Override
    protected String name() {
        return "links";
    }
}
