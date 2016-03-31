/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.sumofseries.threads;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class SumOfSeriesThreadsTest {

    public static final double EPS = 1e-5;
    
    @Test
    public void SumOfSeriesThreadsTest_1_1() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(1, 1);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }    
    
    @Test
    public void SumOfSeriesThreadsTest_N1_1() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(5, 1);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }    
    
    @Test
    public void SumOfSeriesThreadsTest_N5_T1() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(10, 1);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
    @Test
    public void SumOfSeriesThreadsTest_N10_T1() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(10, 1);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
    @Test
    public void SumOfSeriesThreadsTest_N2_T2() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(2, 2);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
    @Test
    public void SumOfSeriesThreadsTest_N5_T4() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(5, 4);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
    @Test
    public void SumOfSeriesThreadsTest_N6_T8() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(6, 8);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
    @Test
    public void SumOfSeriesThreadsTest_N10_T10() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(-10, 10);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
    @Test
    public void SumOfSeriesThreadsTest_N100_T10() {
        SumOfSeriesThreads st = new SumOfSeriesThreads();
        double actRes = st.multiThreadCalculation(100, 10);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    }  
    
}
