package config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class ConnectionPoolConfiguration {

    @JsonProperty
    private int maxConnsPerPartition = 3;

    @JsonProperty
    private int initConnsPerPartition = 0;

    @JsonProperty
    private int maxConns = 1;

    @JsonProperty
    private int port = 9160;

    @JsonProperty
    private int socketTimeout = 11000;

    @JsonProperty
    private int connectTimeout = 2000;
    @JsonProperty
    private int maxTimeoutCount = 3;

    @JsonProperty
    private int maxOperationsPerConnection = 10000;

    @JsonProperty
    private int maxTimeoutWhenExhausted = 2000;

    @JsonProperty
    private String localDatacenter = null;

    @JsonProperty
    private String name = "myConnectionPool";

    @JsonProperty
    private List seeds = Collections.emptyList();

    public ConnectionPoolConfiguration() {
    }

    public int getMaxConnsPerPartition() {
        return maxConnsPerPartition;
    }

    public int getInitConnsPerPartition() {
        return initConnsPerPartition;
    }

    public int getMaxConns() {
        return maxConns;
    }

    public int getPort() {
        return port;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getMaxTimeoutCount() {
        return maxTimeoutCount;
    }

    public int getMaxOperationsPerConnection() {
        return maxOperationsPerConnection;
    }

    public int getMaxTimeoutWhenExhausted() {
        return maxTimeoutWhenExhausted;
    }

    public String getLocalDatacenter() {
        return localDatacenter;
    }

    public String getName() {
        return name;
    }

    public List getSeeds() {
        return seeds;
    }



}
