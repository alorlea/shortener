import config.UrlShortenerConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Created by alberto on 2016-11-19.
 */
public class UrlShortenerApplication extends Application<UrlShortenerConfiguration> {

    public static void main(String[] args) throws Exception {
        new UrlShortenerApplication().run(args);
    }

    @Override
    public void run(UrlShortenerConfiguration urlShortenerConfiguration, Environment environment) throws Exception {

    }
}
