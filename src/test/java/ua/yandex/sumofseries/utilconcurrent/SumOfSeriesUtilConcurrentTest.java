/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.sumofseries.utilconcurrent;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class SumOfSeriesUtilConcurrentTest {
    
    public static final double EPS = 1e-5;

    @Test
    public void SumOfSeriesThreadsTest_N2_T1() {
        SumOfSeriesUtilConcurrent st = new SumOfSeriesUtilConcurrent();
        double actRes = st.multiThreadCalculation(2, 1);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    } 
    
    @Test
    public void SumOfSeriesThreadsTest_N2_T2() {
        SumOfSeriesUtilConcurrent st = new SumOfSeriesUtilConcurrent();
        double actRes = st.multiThreadCalculation(2, 2);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    } 
    
    @Test
    public void SumOfSeriesThreadsTest_N5_T4() {
        SumOfSeriesUtilConcurrent st = new SumOfSeriesUtilConcurrent();
        double actRes = st.multiThreadCalculation(-5, 4);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    } 
    
    @Test
    public void SumOfSeriesThreadsTest_N50_T10() {
        SumOfSeriesUtilConcurrent st = new SumOfSeriesUtilConcurrent();
        double actRes = st.multiThreadCalculation(50, 10);
        double expRes = 0.0;
        assertEquals(expRes, actRes, EPS);
    } 
    
}
