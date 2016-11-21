package engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by alberto on 2016-11-21.
 */
public class ShortenerEngineTest {

    private ShortenerEngine shortenerEngine;

    @Before
    public void setup(){
        this.shortenerEngine = new ShortenerEngine("http://localhost", new Base62(), 8);
    }

    @Test
    public void encodingURLGivesShortURL(){
        String shortUrl = shortenerEngine.encodeURL("http://www.dice.se");
        assertEquals("6MGfYfqMxwd", shortUrl);
    }

    @Test
    public void differentURLsGiveDifferentResults(){
        String shortUrl = shortenerEngine.encodeURL("http://www.dice.se");
        String other = shortenerEngine.encodeURL("http://www.google.com");
        assertNotEquals(shortUrl, other);
    }

}