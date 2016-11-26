package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import systems.composable.dropwizard.cassandra.CassandraFactory;

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
}