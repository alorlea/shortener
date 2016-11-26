package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alberto on 2016-11-26.
 */
public class CassandraConfiguration {

    public static Logger log = LoggerFactory.getLogger(CassandraConfiguration.class);

    @NotEmpty
    @JsonProperty
    private String clusterName = "Test_Cluster";

    @JsonProperty
    private String keyspaceName = "DropwizardAstyanaxKeyspace";

    @JsonProperty
    AstyanaxConfiguration astyanaxConfiguration;

    @JsonProperty
    ConnectionPoolConfiguration connectionPoolConfiguration;

    public CassandraConfiguration() {
    }

    public String getClusterName() {
        return clusterName;
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    public ConnectionPoolConfiguration getConnectionPoolConfiguration() {
        return connectionPoolConfiguration;
    }

    public AstyanaxConfiguration getAstyanaxConfiguration() {
        return astyanaxConfiguration;
    }
}
