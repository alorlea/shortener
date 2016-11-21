package representation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alberto on 2016-11-21.
 */
public class UrlTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJSON() throws Exception {
        final Url url = new Url("http://www.google.com");
        assertThat(MAPPER.writeValueAsString(url)).isEqualTo(fixture("fixtures/url.json"));
    }

    @Test
    public void deserializeFromJSON() throws Exception {
        final Url url = new Url("http://www.google.com");
        assertThat(MAPPER.readValue(fixture("fixtures/url.json"), Url.class));
    }

}