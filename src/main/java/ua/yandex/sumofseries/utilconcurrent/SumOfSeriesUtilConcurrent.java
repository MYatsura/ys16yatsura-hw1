package ua.yandex.sumofseries.utilconcurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import ua.yandex.interfaces.Calculator;
import ua.yandex.interfaces.MultiThreadSummator;

/**
 *
 * @author Maksym Yatsura
 */
public class SumOfSeriesUtilConcurrent implements MultiThreadSummator<Double> {
    
    private static final double STEP = 0.0001;
    private static final double EPS = 1e-6; 
    
    private static class SumCalculator implements Calculator<Double>, 
            Callable<Double>  {

        private final double begin;
        private final double end;
        private Double result = null;

        public SumCalculator(double begin, double end) {
            this.begin = begin;
            this.end = end;
        }
        
        void calculate() {
            result = 0.0;
            for (double x = begin; x <= end + EPS; x += STEP) {
                result += Math.sin(x) * Math.cos(x);
            }
        }
        
        @Override
        public Double call() {
            if(result == null) {
                calculate();
            }
            return result;
        }
        
        @Override
        public Double getResult() {
            return result;
        }
    }
    
    public Double multiThreadCalculation(double n, int numThreads) {

        ExecutorService es = Executors.newFixedThreadPool(numThreads);
        Future<Double>[] f = new Future[numThreads];
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
            f[i] = es.submit(calculators[i]);
        }        
        
        Double sum = 0.0;
        
        try {
            es.shutdown();
            es.awaitTermination(1, TimeUnit.DAYS);
        
            for(int i = 0; i < numThreads; i++) {
                sum += f[i].get();
            }
            
        } catch(InterruptedException e ) {
            System.out.println("Thread was interrupted");
        } catch(ExecutionException e) {
            System.out.println("Execution failed");
        }
        
        return sum;
    }
    
}
