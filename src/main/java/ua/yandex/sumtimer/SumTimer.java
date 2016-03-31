
package ua.yandex.sumtimer;

import ua.yandex.interfaces.MultiThreadSummator;

/**
 *
 * @author Maksym Yatsura
 */

// This timer is used to define
// which number of threads is the
// best to used for calculation

// Testing is performed in a test file
// for this class

public class SumTimer {
    
    MultiThreadSummator mts;

    public SumTimer(MultiThreadSummator mts) {
        this.mts = mts;
    }
    
    public long TimeProcess(double param, int numThreads) {
        
        long startTime = System.nanoTime();
        mts.multiThreadCalculation(param, numThreads);
        long finishTime = System.nanoTime();
        return finishTime - startTime;
    }
    
}
