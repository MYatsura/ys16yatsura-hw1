package ua.yandex.mergesort;

/*
 * author: Maksym Yatsura
*/

// Class implements multithreaded merge sort
// its single public method pMergeSort accepts
// two arrays and puts sorted first array
// into the second array

public class MergeSort {
    
    private static int LIM_MERGE = 100;
    private static int LIM_SORT = 100;
    
    private class Merger implements Runnable {
        
        private double[] T;
        private int p1;
        private int r1;
        private int p2;
        private int r2;
        private double[] A;
        private int p3;

        public Merger(double[] T, int p1, int r1, 
                int p2, int r2, double[] A, int p3) {
            
            this.T = T;
            this.p1 = p1;
            this.r1 = r1;
            this.p2 = p2;
            this.r2 = r2;
            this.A = A;
            this.p3 = p3;
        }

        @Override
        public void run() {
            pMerge(T, p1, r1, p2, r2, A, p3);
        }    
    }
    
    private class Sorter implements Runnable {
        
        private double[] A;
        private int p;
        private int r;
        private double[] B;
        private int s;

        public Sorter(double[] A, int p, int r, double[] B, int s) {
            this.A = A;
            this.p = p;
            this.r = r;
            this.B = B;
            this.s = s;
        }

        @Override
        public void run() {
            pMergeSort(A, p, r, B, s);
        }    
    }
    
    private int max(int a, int b) {
        if(a < b) {
            return b;
        } else {
            return a;
        }
    }
    
    private int binarySearch(double x, double[] T, int p, int r) {
        int low = p;
        int high = max(p, r+1);
        int mid = 0;
        
        while(low < high) {
            mid = (low + high) / 2;
            if(x <= T[mid]) {
                high = mid;
            } else {
                low = mid +1;
            }
        }
        
        return high;
    }
    
    private void pMerge(double[] T, int p1, int r1, 
                        int p2, int r2, double[] A, int p3) {
        
        int n1 = r1 - p1 + 1;
        int n2 = r2 - p2 + 1;
        
        if(n1 < n2) {
            int buf = p1;
            p1 = p2;
            p2 = buf;
            buf = r1;
            r1 = r2;
            r2 = buf;
            buf = n1;
            n1 = n2;
            n2 = buf;
        }
        
        if(n1 != 0) {
            int q1 = (p1 + r1) / 2;
            int q2 = binarySearch(T[q1], T, p2, r2);
            int q3 = p3 + (q1 - p1) + (q2 - p2);
            A[q3] = T[q1];
            
            // We will merge in two threads only if number 
            // of elements in merged arrays is larger than
            // a fixed value LIM_MERGE
            // otherwise we merge in a single thread
            
            if(q3 - p3 + 1 > LIM_MERGE) {
            
                Merger m1 = new Merger(T, p1, q1-1, p2, q2-1, A, p3);
                Merger m2 = new Merger(T, q1+1, r1, q2, r2, A, q3+1);

                Thread t1 = new Thread(m1);
                Thread t2 = new Thread(m2);

                t1.start();
                t2.start();

                try {
                    t1.join();
                    t2.join();
                    } catch(InterruptedException e) {
                        System.out.println("Thread was interrupted");
                    }
            
            } else {
                pMerge(T, p1, q1-1, p2, q2-1, A, p3);
                pMerge(T, q1+1, r1, q2, r2, A, q3+1);
            }
        }
    }
    
    private void pMergeSort(double[] A, int p, int r, double[] B, int s) {
        int n = r - p + 1;
        if(n == 1) {
            B[s] = A[p];
        } else {
            double[] T = new double[n+1];
            int q = (p+r)/2;
            int q1 = q - p + 1;
            
            // We will sort in two threads only if number 
            // of elements in array is larger than
            // a fixed value LIM_SORT
            // otherwise we sort in a single thread
            
            if(n > LIM_SORT) {
            
                Sorter s1 = new Sorter(A, p, q, T, 1);
                Sorter s2 = new Sorter(A, q+1, r, T, q1+1);

                Thread t1 = new Thread(s1);
                Thread t2 = new Thread(s2);

                t1.start();
                t2.start();

                try {
                    t1.join();
                    t2.join();
                } catch(InterruptedException e) {
                    System.out.println("Thread was interrupted");
                }
                
            } else {
                pMergeSort(A, p, q, T, 1);
                pMergeSort(A, q+1, r, T, q1+1);
            }
            
            pMerge(T, 1, q1, q1+1, n, B, s);
        }
    }
    
    public void pMergeSort(double[] A, double[] B) {

        pMergeSort(A, 0, A.length-1, B, 0);
    }
    
}
