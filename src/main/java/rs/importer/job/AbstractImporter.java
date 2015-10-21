package rs.importer.job;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;
import static org.elasticsearch.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.importer.service.LocalManager;
import rs.importer.service.RemoteManager;
import rs.importer.service.Validator;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public abstract class AbstractImporter<T> {
    static int ERROR_SLEEP_IN_SECONDS = 10;

    private Logger log = Logger.getLogger(AbstractImporter.class);
    @Autowired
    private RemoteManager<T> remoteManager;
    @Autowired
    private LocalManager<T> localManager;
    @Autowired
    private Validator<T> validator;

    int maxAttempts = 10;
    int maxEmptyCount = 10;

    public void doImport() {
        AtomicInteger emptyCount = new AtomicInteger(0);
        try {
            iterate(0, i -> i + 1)
                    .peek(i -> log.debug(format("Importing %s, page %d", name(), i)))
                    .mapToObj(this::get)
                    .peek(list -> log.debug(format("Got %d %s", list.size(), name())))
                    .peek(list -> decideToStop(list, emptyCount))
                    .filter(list -> !list.isEmpty())
                    .map(validator::filter)
                    .peek(list -> log.debug(format("Filtered, now have %d %s", list.size(), name())))
                    .forEach(localManager::save);
        } catch (StoppedException ignored) {

        }
    }

    private void decideToStop(Collection<T> list, AtomicInteger emptyCount) {
        if (list.isEmpty()) {
            emptyCount.incrementAndGet();
        } else {
            emptyCount.set(0);
        }

        if (emptyCount.intValue() > maxEmptyCount) {
            throw new StoppedException();
        }
    }

    private Collection<T> get(Integer page) {
        return range(1, maxAttempts)
                .mapToObj(i -> {
                    try {
                        return remoteManager.get(page, 1000);
                    } catch (Exception ignore) {
                        log.info(format("Error retrieving %s: page: %d iteration: %d. Sleeping for %d seconds then trying again", name(), page, i, i * ERROR_SLEEP_IN_SECONDS));
                        sleepUninterruptibly(i * ERROR_SLEEP_IN_SECONDS, SECONDS);
                        return null;
                    }
                })
                .filter(comments -> comments != null)
                .findAny().orElse(emptyList());
    }

    protected abstract String name();
}
