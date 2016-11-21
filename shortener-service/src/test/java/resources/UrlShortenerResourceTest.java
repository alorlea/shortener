package resources;

import static org.junit.Assert.*;

import engine.Base62;
import engine.ShortenerEngine;
import io.dropwizard.testing.junit.ResourceTestRule;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import representation.UrlMapping;

/**
 * Created by alberto on 2016-11-21.
 */
public class UrlShortenerResourceTest {
    private static final Map<String,String> testURLs = new HashMap<String,String>();

    @ClassRule
    public static final ResourceTestRule RESOURCE_TEST_RULE = ResourceTestRule.builder().addResource(new UrlShortenerResource(testURLs, new ShortenerEngine("http://localhost", new Base62(), 8))).build();

    private final UrlMapping urlMapping = new UrlMapping("http://www.dice.se", "6MGfYfqMxwd");

    @Before
    public void setup(){
        testURLs.put(urlMapping.getShortURL(), urlMapping.getOriginalURL());
    }

    @Test
    public void testGetOriginalURL(){
        Response response = RESOURCE_TEST_RULE.client().target("/shortener/alilnDyEW").request().get();
        assertEquals(response.getStatus(), 307);
        assertEquals(response.getLocation().toString(), "http://dice.se");
    }
}