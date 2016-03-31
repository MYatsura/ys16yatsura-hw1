package ua.yandex.lockfree;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Maksym Yatsura
 */
public class PowerOfTwo {

    private static AtomicReference<BigInteger> current
            = new AtomicReference<> (BigInteger.ONE);
    private static BigInteger TWO = new BigInteger("2");
    
    public static BigInteger next() {
        BigInteger res = null;
        boolean ready = false;
        while(!ready) {
            res = current.get();
            ready = current.compareAndSet(res, res.multiply(TWO));
        } 
        
        return res;
    }
    
}
