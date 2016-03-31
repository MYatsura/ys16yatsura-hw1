/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.mergesort;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class MergeSortTest {
    
    private static final double EPS = 1e-6; 
    
    @Test
    public void MergeSortTest_pMergeSort1() {
        MergeSort sorter = new MergeSort();
        double[] arr = {-1.2, 0, -1.8, 2, 1};
        double[] act = {0, 0, 0, 0, 0};
        double[] exp = {-1.8, -1.2, 0, 1, 2};
        sorter.pMergeSort(arr, act);
        assertArrayEquals(exp, act, EPS);
    }  
    
    @Test
    public void MergeSortTest_pMergeSort2() {
        MergeSort sorter = new MergeSort();
        double[] arr = {7, 18, 21, 4, -5, 2, 0, 8, 4, 4, 1};
        double[] act = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double[] exp = {-5, 0, 1, 2, 4, 4, 4, 7, 8, 18, 21};
        sorter.pMergeSort(arr, act);
        assertArrayEquals(exp, act, EPS);
    }  
    
    @Test
    public void MergeSortTest_pMergeSort3() {
        MergeSort sorter = new MergeSort();
        double[] arr = {7, 18, 21, 4, -5, 2, 0, 8, 4, 4, 1};
        double[] act = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double[] exp = {-5, 0, 1, 2, 4, 4, 4, 7, 8, 18, 21};
        sorter.pMergeSort(arr, act);
        assertArrayEquals(exp, act, EPS);
    }  
    
    @Test
    public void MergeSortTest_Big1() {
        MergeSort sorter = new MergeSort();
        int n = 10000;
        double[] arr = new double[n];
        double[] act = new double[n];
        double[] exp = new double[n];
        
        // arr = { 0, -1, -2, -3, ..., -9999 }
        // sorted: exp = { -9999, -9998, -9997, ..., -1, 0 }
        
        for(int i = 0; i < n; i++) {
            arr[i] = -i;
            exp[i] = - (n - 1 - i);
        }
        
        sorter.pMergeSort(arr, act);
        assertArrayEquals(exp, act, EPS);
    }  
    
    @Test
    public void MergeSortTest_Big2() {
        MergeSort sorter = new MergeSort();
        int n = 10000;
        double[] arr = new double[n];
        double[] act = new double[n];
        double[] exp = new double[n];
        
        // arr = { 1, -2, 3, -4, 5, -6, ..., -9998, 9999, -10000 }
        // sorted: exp = { -10000, -9998, -9996, -9994, ..., 9997, 9999 }
        
        // construct arr
        for(int i = 1; i <= n; i++) {
            if(i % 2 == 0) {
                arr[i-1] = -i;
            } else {
                arr[i-1] = i;
            }
        }
        
        // construct exp
        for(int i = 0; i < 5000; i++) {
            exp[i] = -n + 2 * i;
        }
        
        for(int i = 5000; i < n; i++) {
            exp[i] = 2*i -n + 1;
        }
        
        sorter.pMergeSort(arr, act);
        assertArrayEquals(exp, act, EPS);
    }  
    
}
