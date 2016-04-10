/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.lockfree;

import java.math.BigInteger;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class PowerOfTwoTest {
    
    private static class PowerOfTwoTaker implements Runnable {

        BigInteger res;
        
        @Override
        public void run() {
            res = PowerOfTwo.next();
        }
        
        public BigInteger getResult() {
            return res;
        }
    }
    
    @Test
    public void NextTest_100Threads() {
        
        int numThreads = 100;
        Thread[] threads = new Thread[numThreads];
        PowerOfTwoTaker[] takers = new PowerOfTwoTaker[numThreads];
        
        for(int i = 0; i < numThreads; i++) {
            takers[i] = new PowerOfTwoTaker();
            threads[i] = new Thread(takers[i]);
        }
        
        HashSet<BigInteger> hs = new HashSet<>();
        
        for(int i = 0; i < numThreads; i++) {
            threads[i].run();
            hs.add(takers[i].getResult());
        }
        
        int exp = numThreads;
        int act = hs.size();
        
        assertEquals(exp, act);
    }
    
    @Test
    public void NextTest_500Threads() {
        
        int numThreads = 500;
        Thread[] threads = new Thread[numThreads];
        PowerOfTwoTaker[] takers = new PowerOfTwoTaker[numThreads];
        
        for(int i = 0; i < numThreads; i++) {
            takers[i] = new PowerOfTwoTaker();
            threads[i] = new Thread(takers[i]);
        }
        
        HashSet<BigInteger> hs = new HashSet<>();
        
        for(int i = 0; i < numThreads; i++) {
            threads[i].run();
            hs.add(takers[i].getResult());
        }
        
        int exp = numThreads;
        int act = hs.size();
        
        assertEquals(exp, act);
    }
    
    @Test
    public void NextTest_1000Threads() {
        
        int numThreads = 1000;
        Thread[] threads = new Thread[numThreads];
        PowerOfTwoTaker[] takers = new PowerOfTwoTaker[numThreads];
        
        for(int i = 0; i < numThreads; i++) {
            takers[i] = new PowerOfTwoTaker();
            threads[i] = new Thread(takers[i]);
        }
        
        HashSet<BigInteger> hs = new HashSet<>();
        
        for(int i = 0; i < numThreads; i++) {
            threads[i].run();
            hs.add(takers[i].getResult());
        }
        
        int exp = numThreads;
        int act = hs.size();
        
        assertEquals(exp, act);
    }
    
    
}
