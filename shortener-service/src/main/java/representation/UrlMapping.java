package representation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alberto on 2016-11-19.
 */
public class UrlMapping {
    private String originalURL;
    private String shortURL;

    public UrlMapping() {
    }

    public UrlMapping(String originalURL, String shortURL) {
        this.originalURL = originalURL;
        this.shortURL = shortURL;
    }

    @JsonProperty
    public String getOriginalURL() {
        return originalURL;
    }
    @JsonProperty
    public String getShortURL() {
        return shortURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlMapping that = (UrlMapping) o;

        if (originalURL != null ? !originalURL.equals(that.originalURL) : that.originalURL != null) return false;
        return shortURL != null ? shortURL.equals(that.shortURL) : that.shortURL == null;

    }

    @Override
    public int hashCode() {
        int result = originalURL != null ? originalURL.hashCode() : 0;
        result = 31 * result + (shortURL != null ? shortURL.hashCode() : 0);
        return result;
    }
}
