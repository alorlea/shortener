import com.datastax.driver.core.Cluster;
import config.UrlShortenerConfiguration;
import dao.UrlMappingDAO;
import engine.Base62;
import engine.ShortenerEngine;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import java.util.concurrent.ConcurrentHashMap;
import resources.UrlShortenerResource;

/**
 * Created by alberto on 2016-11-19.
 */
public class UrlShortenerApplication extends Application<UrlShortenerConfiguration> {

    public static void main(String[] args) throws Exception {
        new UrlShortenerApplication().run(args);
    }

    @Override
    public void run(UrlShortenerConfiguration urlShortenerConfiguration, Environment environment) throws Exception {
        final Cluster cassandra = urlShortenerConfiguration.getCassandraFactory().build(environment);
        final UrlShortenerResource resource = new UrlShortenerResource(
            new ConcurrentHashMap<>(),
            new ShortenerEngine(urlShortenerConfiguration.getBaseURL(),
                new Base62(), 8), new UrlMappingDAO(
                    "url-mappings",
                    urlShortenerConfiguration.getCassandraFactory().getKeyspace(),
                    cassandra
            )
        );

        environment.jersey().register(resource);
    }
}
