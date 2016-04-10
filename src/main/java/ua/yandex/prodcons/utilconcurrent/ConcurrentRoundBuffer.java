package ua.yandex.prodcons.utilconcurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import ua.yandex.interfaces.Buffer;

/**
 *
 * @author Maksym Yatsura
 */
public class ConcurrentRoundBuffer implements Buffer {
    
    private final int size;
    private volatile int first = 0;
    private volatile int last = 0;
    private volatile int actualSize = 0;
    private volatile boolean open = true;
    private volatile int readPointer = 0;
    private final int[] buffer;
    
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    
    public ConcurrentRoundBuffer(int size) {
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
    
    public void close() {
        open = false;
        
        full.signalAll();
        empty.signalAll();
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

        lock.lock();
        try {
            while(open() && full()) {
                System.out.println("Buffer is full");
                try {
                    full.await();
                } catch (InterruptedException ex) {
                    System.out.println("Thread was interrupted");
                }
            }
            
            if(open()) {
                buffer[last] = a;
                last = indexOfNextElementInBuffer(last);
                actualSize++;
                
                empty.signal();
            }
        } finally {
            lock.unlock();
        }
        
    }   
    
    @Override
    public synchronized void pop() {
        
        lock.lock();
        
        try {
            while(open() && full()) {
                System.out.println("Buffer is full");
                try {
                    empty.await();
                } catch (InterruptedException ex) {
                    System.out.println("Thread was interrupted");
                }
            }
            
            if(open()) {
                first = indexOfNextElementInBuffer(first);
                actualSize--;
                
                full.signal();
            } else {
                System.out.println("Buffer is closed");
            }
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public synchronized int top() {
        
        lock.lock();
        int result = 0;
        
        try {
            while(open() && full()) {
                System.out.println("Buffer is full");
                try {
                    empty.await();
                } catch (InterruptedException ex) {
                    System.out.println("Thread was interrupted");
                }
            }
            
            if(open()) {
                result = buffer[first];
                
                full.signal();
            } else {
                System.out.println("Buffer is closed");
            }
        } finally {
            lock.unlock();
        }
        
        return result;
    }  
    
}
