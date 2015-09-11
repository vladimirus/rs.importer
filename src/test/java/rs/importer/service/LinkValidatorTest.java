package rs.importer.service;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static rs.importer.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import rs.importer.model.Link;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class LinkValidatorTest {
    @InjectMocks
    private LinkValidator linkValidator;

    @Test
    public void shouldFilter() {

        // when
        Collection<Link> actual = linkValidator.filter(asList(
                aLink().id("1").score(10L).build(),
                aLink().id("2").score(100L).build()));

        // then
        assertThat(actual, hasSize(1));
    }
}