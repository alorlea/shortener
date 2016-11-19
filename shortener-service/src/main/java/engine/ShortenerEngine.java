package engine;

/**
 * Created by alberto on 2016-11-19.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Created by Alberto on 2015-03-22.
 */
public class ShortenerEngine{
    private String baseURL;
    private int numberBytes;
    private Base62 base62;

    public ShortenerEngine(String baseURL, Base62 base62, int numberBytes) {
        this.baseURL = baseURL;
        this.numberBytes = numberBytes;
        this.base62 = base62;
    }

    public String encodeURL(String URL) {
        String encodedURL = "";
        try {

            byte[] bytesOfMessage = URL.getBytes("UTF-8");
            byte[] bytesOfKey = baseURL.getBytes("UTF-8");
            byte[] xorBytes = xorWithKey(bytesOfMessage,bytesOfKey);
            //System.out.println(Arrays.toString(xorBytes));
            byte[] lowerBytes = getLowerBytes(xorBytes);
            //System.out.println(Arrays.toString(lowerBytes));

            ByteBuffer buffer = ByteBuffer.wrap(lowerBytes);
            Long stringId = buffer.getLong();
            //System.out.println(stringId);

            encodedURL = base62.encode(stringId);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return encodedURL;
    }

    private byte[] getLowerBytes(byte[] digest) {
        byte[] fetchedBytes = new byte[numberBytes];

        int j = 0;
        for (int i = digest.length - numberBytes; i < digest.length; i++) {
            fetchedBytes[j] = digest[i];
            j++;
        }
        return fetchedBytes;
    }

    private byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
        }
        return out;
    }

    public String getBaseURL() {
        return baseURL;
    }
}
