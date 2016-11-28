import config.UrlShortenerConfiguration;
import dao.UrlDAO;
import engine.Base62;
import engine.ShortenerEngine;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.concurrent.ConcurrentHashMap;
import resources.UrlShortenerResource;

/**
 * Created by alberto on 2016-11-19.
 */
public class UrlShortenerApplication extends Application<UrlShortenerConfiguration> {

    private CassandraAstyanaxBundle cassandraAstyanaxBundle;

    public static void main(String[] args) throws Exception {
        new UrlShortenerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<UrlShortenerConfiguration> bootstrap) {

        cassandraAstyanaxBundle = CassandraAstyanaxBundle.<UrlShortenerConfiguration>builder().withConfiguration(UrlShortenerConfiguration::getCassandraConfiguration).withHealthCheckName("cassandra").build();
        bootstrap.addBundle(cassandraAstyanaxBundle);
    }

    @Override
    public void run(UrlShortenerConfiguration urlShortenerConfiguration, Environment environment) throws Exception {
        final UrlDAO urlDAO = new UrlDAO(cassandraAstyanaxBundle.getManagedAstyanaxClient().getClient());
        final UrlShortenerResource resource = new UrlShortenerResource(
            new ConcurrentHashMap<>(),
            new ShortenerEngine(urlShortenerConfiguration.getBaseURL(),
                new Base62(), 8),
            urlDAO
        );

        environment.jersey().register(resource);
    }
}
