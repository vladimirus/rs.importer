package rs.importer.job;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.importer.model.Link;
import rs.importer.service.LinkValidator;
import rs.importer.service.LocalManager;
import rs.importer.service.RemoteManager;

@Service
public class LinkImporter {
    private Logger log = Logger.getLogger(LinkImporter.class);
    @Autowired
    private RemoteManager<Link> remoteManager;
    @Autowired
    private LocalManager<Link> localManager;
    @Autowired
    private LinkValidator linkValidator;

    int pagesToCheck = 600000;

    @Scheduled(initialDelay = 2000, fixedRate = 1800000) // 30 minutes
    public void importLinks() {

        range(0, pagesToCheck)
                .peek(i -> log.debug(format("Importing links, page %d", i)))
                .mapToObj(i -> remoteManager.get(i, 1000))
                .peek(list -> log.debug(format("Got %d links", list.size())))
                .filter(list -> !list.isEmpty())
                .map(linkValidator::filter)
                .peek(list -> log.debug(format("Filtered, now have %d links", list.size())))
                .forEach(localManager::save);
    }
}
