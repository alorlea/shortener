package resources;

import com.codahale.metrics.annotation.Timed;
import com.netflix.astyanax.Keyspace;
import engine.ShortenerEngine;
import representation.Url;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by alberto on 2016-11-19.
 */

@Path("/shortener")
public class UrlShortenerResource {
    private Map<String, String> cachedUrls;
    private ShortenerEngine shortenerEngine;
    private Keyspace keyspace;

    public UrlShortenerResource(Map<String, String> cachedUrls, ShortenerEngine shortenerEngine, Keyspace keyspace) {
        this.cachedUrls = cachedUrls;
        this.shortenerEngine = shortenerEngine;
        this.keyspace = keyspace;
    }

    @GET
    @Path("/{shortURL}")
    @Timed
    public Response fetchOriginalURL(@PathParam("shortURL") String shortURL){
        try {
            if (cachedUrls.containsKey(shortURL)) {
                String result = cachedUrls.get(shortURL);
                URI location = new URI(result);
                return Response.temporaryRedirect(location).build();
            } else {
                return Response.status(404).build();
            }
        } catch (URISyntaxException e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Url createNewShortUrl(Url url){
        String originalURL = url.getUrl();
        String shortURL = shortenerEngine.encodeURL(originalURL);
        cachedUrls.put(shortURL,originalURL);
        return new Url(shortenerEngine.getBaseURL()+"/shortener/"+shortURL);
    }
}
