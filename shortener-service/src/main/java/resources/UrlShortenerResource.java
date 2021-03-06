package resources;

import com.codahale.metrics.annotation.Timed;
import dao.UrlMappingDAO;
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
    private UrlMappingDAO urlMappingDAO;

    public UrlShortenerResource(Map<String, String> cachedUrls, ShortenerEngine shortenerEngine, UrlMappingDAO urlMappingDAO) {
        this.cachedUrls = cachedUrls;
        this.shortenerEngine = shortenerEngine;
        this.urlMappingDAO = urlMappingDAO;
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
                //fetch it from the database
                String result = urlMappingDAO.getOriginalURL(shortURL);
                if(result == null){
                    return Response.status(404).build();
                }else{
                    URI location = new URI(result);
                    return Response.temporaryRedirect(location).build();
                }
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
        urlMappingDAO.putNewEncodedURL(shortURL,originalURL);
        return new Url(shortenerEngine.getBaseURL()+"/shortener/"+shortURL);
    }
}
