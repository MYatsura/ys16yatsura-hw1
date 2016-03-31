package ua.yandex.sumofseries.threads;

import ua.yandex.interfaces.Calculator;
import ua.yandex.interfaces.MultiThreadSummator;

/**
 *++
 * @author Maksym Yatsura
 */
public class SumOfSeriesThreads implements MultiThreadSummator<Double> {

    private static final double STEP = 0.0001;
    private static final double EPS = 1e-6; 
    
    private static class SumCalculator implements Calculator<Double>, Runnable {

        private final double begin;
        private final double end;
        private Double result = null;

        public SumCalculator(double begin, double end) {
            this.begin = begin;
            this.end = end;
        }
        
        private void calculate() {
            result = 0.0;
            for (double x = begin; x <= end + EPS; x += STEP) {
                result += Math.sin(x) * Math.cos(x);
            }
        }
        
        @Override
        public void run() {
            if(result == null) {
                calculate();
            }
        }
        
        @Override
        public Double getResult() {
            return result;
        }
    }
    
    public Double multiThreadCalculation(double n, int numThreads) {
        
        Thread[] threads = new Thread[numThreads];
        SumCalculator[] calculators = new SumCalculator[numThreads];
        
        if(n < 0) {
            n = -n;
        }
        double portion = 2.0*n / numThreads;
        
        // We declare first calculator separatly to provide correct
        // calculation on segment [-n; n] (without missed points 
        // and overlaping)
        calculators[0] = new SumCalculator(-n, -n + portion);
        
        for(int i = 1; i < numThreads; i++) {
            calculators[i] = 
                    new SumCalculator(-n+i*portion+STEP, -n+(i+1)*portion);
        }
        
        for(int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(calculators[i]);
        }
                
        for(int i = 0; i < numThreads; i++) {
            threads[i].run();
        }       
        
        try {
            for(int i = 0; i < numThreads; i++) {
                threads[i].join();
            }    
        } catch(InterruptedException e ) {
            System.out.println("Thread was interrupted");
        }
        
        Double sum = 0.0;
        
        for(int i = 0; i < numThreads; i++) {
            sum += calculators[i].getResult();
        }   
       
        return sum;
    }
    
    
}
