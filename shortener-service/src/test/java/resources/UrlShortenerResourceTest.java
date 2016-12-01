package resources;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import dao.UrlMappingDAO;
import engine.Base62;
import engine.ShortenerEngine;
import io.dropwizard.testing.junit.ResourceTestRule;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Matchers;
import representation.Url;
import representation.UrlMapping;

/**
 * Created by alberto on 2016-11-21.
 */
public class UrlShortenerResourceTest {
    private static final Map<String,String> testURLs = new HashMap<String,String>();

    private final static Cluster cassandra = mock(Cluster.class);
    private final static Session client = mock(Session.class);

    private final static UrlMappingDAO URL_MAPPING_DAO = new UrlMappingDAO("url-mappings","authentication", cassandra);
    @ClassRule
    public final static ResourceTestRule RESOURCE_TEST_RULE = ResourceTestRule.builder().addResource(new UrlShortenerResource(testURLs, new ShortenerEngine("http://localhost", new Base62(), 8), URL_MAPPING_DAO)).build();

    private final UrlMapping urlMapping = new UrlMapping("http://www.dice.se", "6MGfYfqMxwd");

    @Before
    public void setup(){
        testURLs.put(urlMapping.getShortURL(), urlMapping.getOriginalURL());
    }

    @Test
    public void testGetFailsWithUnprocessedURL(){
        ResultSet set = mock(ResultSet.class);
        when(cassandra.connect()).thenReturn(client);
        when(client.execute(Matchers.anyString())).thenReturn(set);
        when(set.one()).thenReturn(null);
        Response response = RESOURCE_TEST_RULE.client().target("/shortener/thisTestFails").request().get();
        assertEquals(response.getStatus(),404);
    }

    @Test
    public void testPutOriginalURL(){
        Url url = new Url("http://www.elotrolado.net/");
        Response response = RESOURCE_TEST_RULE.client().target("/shortener").request(MediaType.APPLICATION_JSON_TYPE).put(
            Entity.json(url));
        Url newUrl = response.readEntity(Url.class);
        assertEquals(newUrl.getUrl(),"http://localhost/shortener/1OcJ3mANiUY");
    }
}