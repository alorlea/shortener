package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolType;
import com.netflix.astyanax.model.ConsistencyLevel;
import com.netflix.astyanax.retry.RetryPolicy;
import retry.RetryPolicyFactory;


/**
 * Created by alberto on 2016-11-26.
 */
public class AstyanaxConfiguration {

    @JsonProperty
    private ConsistencyLevel defaultReadConsistencyLevel = ConsistencyLevel.CL_ONE;

    @JsonProperty
    private ConsistencyLevel defaultWriteConsistencyLevel = ConsistencyLevel.CL_ONE;

    @JsonProperty
    private RetryPolicyFactory retryPolicy;

    @JsonProperty
    private NodeDiscoveryType discoveryType = NodeDiscoveryType.NONE;

    @JsonProperty
    private ConnectionPoolType connectionPoolType = ConnectionPoolType.ROUND_ROBIN;

    @JsonProperty
    private String cqlVersion = null;

    @JsonProperty
    private String targetCassandraVersion = "1.1";


    public ConsistencyLevel getDefaultReadConsistencyLevel() {
        return defaultReadConsistencyLevel;
    }

    public ConsistencyLevel getDefaultWriteConsistencyLevel() {
        return defaultWriteConsistencyLevel;
    }

    public RetryPolicyFactory getRetryPolicy() {
        return retryPolicy;
    }

    public NodeDiscoveryType getDiscoveryType() {
        return discoveryType;
    }

    public ConnectionPoolType getConnectionPoolType() {
        return connectionPoolType;
    }

    public String getCqlVersion() {
        return cqlVersion;
    }

    public String getTargetCassandraVersion() {
        return targetCassandraVersion;
    }


}
