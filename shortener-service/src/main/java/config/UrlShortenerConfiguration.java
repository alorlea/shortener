package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by alberto on 2016-11-19.
 */
public class UrlShortenerConfiguration extends Configuration{
    @NotEmpty
    private String baseURL;

    @JsonProperty
    public String getBaseURL() {
        return baseURL;
    }

    @JsonProperty
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    @JsonProperty("cassandra")
    protected CassandraConfiguration cassandraConfiguration;

    public CassandraConfiguration getCassandraConfiguration(){
        return cassandraConfiguration;
    }
}