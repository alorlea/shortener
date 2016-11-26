package managed;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Joiner;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;
import config.AstyanaxConfiguration;
import config.CassandraConfiguration;
import config.ConnectionPoolConfiguration;
import io.dropwizard.lifecycle.Managed;

/**
 * Created by alberto on 2016-11-26.
 */
public class ManagedAstyanaxClient implements Managed {


    AstyanaxContext<Keyspace> context;

    public ManagedAstyanaxClient(AstyanaxContext<Keyspace> context) {
        this.context = checkNotNull(context, "AstyanaxContext must not be null");
    }

    public ManagedAstyanaxClient(CassandraConfiguration config) {
        checkNotNull(config, "AstyanaxConfiguration must not be null");

        this.context = new AstyanaxContext.Builder()
            .forCluster(config.getClusterName())
            .forKeyspace(config.getKeyspaceName())
            .withAstyanaxConfiguration(getAstyanaxConfigurationImpl(config.getAstyanaxConfiguration()))
            .withConnectionPoolConfiguration(getConnectionPoolConfigurationImpl(config.getConnectionPoolConfiguration()))
            .buildKeyspace(ThriftFamilyFactory.getInstance());

    }

    private AstyanaxConfigurationImpl getAstyanaxConfigurationImpl(AstyanaxConfiguration astyanaxConfiguration) {

        return new AstyanaxConfigurationImpl()
            .setDefaultReadConsistencyLevel(astyanaxConfiguration.getDefaultReadConsistencyLevel())
            .setDefaultWriteConsistencyLevel(astyanaxConfiguration.getDefaultReadConsistencyLevel())
            .setRetryPolicy(astyanaxConfiguration.getRetryPolicy().build())
            .setDiscoveryType(astyanaxConfiguration.getDiscoveryType())
            .setConnectionPoolType(astyanaxConfiguration.getConnectionPoolType())
            .setTargetCassandraVersion(astyanaxConfiguration.getTargetCassandraVersion())
            .setCqlVersion(astyanaxConfiguration.getCqlVersion());
    }

    private ConnectionPoolConfigurationImpl getConnectionPoolConfigurationImpl(ConnectionPoolConfiguration connectionPoolConfiguration) {

        return new ConnectionPoolConfigurationImpl(connectionPoolConfiguration.getName())
            .setPort(connectionPoolConfiguration.getPort())
            .setMaxConnsPerHost(connectionPoolConfiguration.getMaxConnsPerPartition())
            .setInitConnsPerHost(connectionPoolConfiguration.getInitConnsPerPartition())
            .setMaxConns(connectionPoolConfiguration.getMaxConns())
            .setSocketTimeout(connectionPoolConfiguration.getSocketTimeout())
            .setConnectTimeout(connectionPoolConfiguration.getConnectTimeout())
            .setMaxTimeoutCount(connectionPoolConfiguration.getMaxTimeoutCount())
            .setMaxOperationsPerConnection(connectionPoolConfiguration.getMaxOperationsPerConnection())
            .setMaxTimeoutWhenExhausted(connectionPoolConfiguration.getMaxTimeoutWhenExhausted())
            .setLocalDatacenter(connectionPoolConfiguration.getLocalDatacenter())
            .setSeeds(Joiner.on(",").join(connectionPoolConfiguration.getSeeds()));
    }

    public final Keyspace getClient() {
        return context.getClient();
    }

    @Override
    public void start() throws Exception {
        context.start();
    }

    @Override
    public void stop() throws Exception {
        if (context != null) {
            context.shutdown();
        }
    }

}
