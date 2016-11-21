package representation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alberto on 2016-11-19.
 */
public class Url {
    private String url;

    public Url() {
    }

    public Url(String url) {
        this.url = url;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }
}
