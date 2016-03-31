package ua.yandex.prodcons.threads;

/**
 * @author Maksym Yatsura
 */
public class RoundBuffer {
    
    private static final int SIZE = 50;
    private int[] array = new int[SIZE];
    private volatile int actualSize = 0;
    private volatile int pushPointer = 0;
    private volatile int readPointer = 0;
    
    public synchronized boolean empty() {
        return actualSize == 0;
    }
    
    public synchronized boolean filled() {
        return actualSize == SIZE;
    }
    
    public synchronized void push(int a) {
        array[pushPointer++] = a;
    }    
    
    public synchronized int read() {
        return readPointer++;
    }  
    
}
