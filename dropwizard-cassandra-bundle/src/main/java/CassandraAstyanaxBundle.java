import config.CassandraConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alberto on 2016-11-26.
 */
public class CassandraAstyanaxBundle<C extends Configuration> implements ConfiguredBundle <C> {

    public static Logger log = LoggerFactory.getLogger(CassandraAstyanaxBundle.class);

    protected String healthCheckName;
    protected ConfigurationAccessor<C> configurationAccessor;
    
    public CassandraAstyanaxBundle(ConfigurationAccessor<C> configurationAccessor, String healthCheckName) {

    }

    public static interface ConfigurationAccessor<C extends Configuration>{
        public CassandraConfiguration configuration(C configuration);
    }

    public static class Builder<C extends Configuration> {
        protected ConfigurationAccessor<C> configurationAccessor;
        protected String healthCheckName = "cassandra";

        public Builder<C> withConfiguration( ConfigurationAccessor<C> configurationAccessor ) {
            this.configurationAccessor = configurationAccessor;
            return this;
        }

        public Builder<C> withHealthCheckName( String healthCheckName ) {
            this.healthCheckName = healthCheckName;
            return this;
        }

        public CassandraAstyanaxBundle<C> build() {
            if( configurationAccessor == null ) {
                throw new IllegalArgumentException("configuration accessor is required.");
            }
            return new CassandraAstyanaxBundle<C>(configurationAccessor, healthCheckName);
        }
    }

    public static <C extends Configuration> Builder<C> builder() {
        return new Builder<C>();
    }

    @Override
    public void run(C c, Environment environment) throws Exception {

    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }
}
