package ua.yandex.sumtimer;

import org.junit.Test;
import ua.yandex.interfaces.MultiThreadSummator;
import ua.yandex.sumofseries.threads.SumOfSeriesThreads;
import ua.yandex.sumofseries.utilconcurrent.SumOfSeriesUtilConcurrent;

/**
 * @author Maksym Yatsura
 */

// Here we define which number of
// threads is optimal
// for series sum calculation

public class SumTimerTest {
    
    // We use the same parameter N for all tests
    private static final double PARAM = 1000.0;
    private static final double MAX_NUM_THREADS = 20;
    
    @Test
    public void testThreadsAndUtilConcurrent() {
        
        MultiThreadSummator mtSummator = new SumOfSeriesThreads();
        MultiThreadSummator ucSummator = new SumOfSeriesUtilConcurrent();
        SumTimer timerThreads = new SumTimer(mtSummator);
        SumTimer timerUtilConcurrent = new SumTimer(ucSummator);
        
        long time;
        
        long bestTime_Threads = -1;
        int bestNum_Threads = -1;
        
        long bestTime_UtilConcurrent = -1;
        int bestNum_UtilConcurrent = -1;
        
        for(int numThreads = 1; numThreads <= MAX_NUM_THREADS; numThreads++) {
            
            time = timerThreads.TimeProcess(PARAM, numThreads);
            if(bestTime_Threads == -1 || time < bestTime_Threads) {
                bestTime_Threads = time;
                bestNum_Threads = numThreads;
            }
            
            time = timerUtilConcurrent.TimeProcess(PARAM, numThreads);
            if(bestTime_UtilConcurrent == -1 
                    || time < bestTime_UtilConcurrent) {
                
                bestTime_UtilConcurrent = time;
                bestNum_UtilConcurrent = numThreads;
            }
            
        }
        
        System.out.println();
        System.out.println("Optimal number of threads: "+ bestNum_Threads);
        System.out.println("Time to calculate sum of sin(x)cos(x) from -"+
                PARAM+" to "+PARAM + " is "+ bestTime_Threads / 1e9 + " sec");
        System.out.println();
        
        System.out.println("Optimal number of threads for util concurrent: "
                + bestNum_UtilConcurrent);
        System.out.println("Time to calculate sum of sin(x)cos(x) from -"+
                PARAM+" to "+PARAM + " is "+ bestTime_UtilConcurrent / 1e9 + " sec");
        System.out.println();
    }
    
}
