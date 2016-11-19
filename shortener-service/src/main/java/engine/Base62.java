package engine;

import java.math.BigInteger;

/**
 * Created by alberto on 2016-11-19.
 */
public class Base62 {
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encode(long base10){
        BigInteger BASE = BigInteger.valueOf(62);
        BigInteger base10Integer = BigInteger.valueOf(base10);
        String codedValue ="";
        while(base10Integer.compareTo(BigInteger.ZERO) == 1){
            BigInteger [] divmod = base10Integer.divideAndRemainder(BASE);
            base10Integer = divmod[0];
            int position = divmod[1].intValue();
            codedValue = alphabet.charAt(position) + codedValue;
        }
        return codedValue;
    }
}
