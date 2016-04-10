package ua.yandex.prodcons.threads;

import ua.yandex.interfaces.Buffer;

/**
 * @author Maksym Yatsura
 */
public class SynchronizedRoundBuffer implements Buffer {
    
    private final int size;
    private volatile int first = 0;
    private volatile int last = 0;
    private volatile int actualSize = 0;
    private volatile boolean open = true;
    private volatile int readPointer = 0;
    private final int[] buffer;

    public SynchronizedRoundBuffer(int size) {
        this.size = size;
        buffer = new int[size];
    }
    
    @Override
    public boolean empty() {
        return actualSize == 0;
    }
    
    @Override
    public boolean full() {
        return actualSize == size;
    }
    
    @Override
    public boolean open() {
        return open == true;
    }
    
    public synchronized void close() {
        open = false;
        notifyAll();
    } 
    
    private int indexOfNextElementInBuffer(int n) {
        int m = n + 1;
        if(m == size) {
            m = 0;
        }
        return m;
    }
    
    @Override
    public synchronized void push(int a) {
        while(open() && full()) {
            System.out.println("Buffer is full");
           
            try {
                wait();
            } catch (InterruptedException ex) {
                 System.out.println("Thread was interrupted");
            }
        }
        
        if(open()) {
            buffer[last] = a;
            last = indexOfNextElementInBuffer(last);
            actualSize++;
            notifyAll();
        } else {
            System.out.println("Buffer is closed");
        }
    }   
    
    @Override
    public synchronized void pop() {
        while(open() && empty()) {
            System.out.println("Buffer is empty");
           
            try {
                wait();
            } catch (InterruptedException ex) {
                 System.out.println("Thread was interrupted");
            }
        }
        
        if(open()) {
            first = indexOfNextElementInBuffer(first);
            actualSize--;
            notifyAll();
        } else {
            System.out.println("Buffer is closed");
        }
    }
    
    @Override
    public synchronized int top() {
        while(open() && empty()) {
            System.out.println("Buffer is empty");
           
            try {
                wait();
            } catch (InterruptedException ex) {
                 System.out.println("Thread was interrupted");
            }
        }
        
        int result = 0;
        if(open()) {
            result = buffer[first];
            notifyAll();
        } else {
            System.out.println("Buffer is closed");
        }
        
        return result;
    }  
   
    
}
