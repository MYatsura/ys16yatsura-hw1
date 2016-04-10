package ua.yandex.threadpool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Maksym Yatsura
 */
public class ThreadPool {
    
    private int numThreads;
    private int numTask;
    private Thread[] threads;
    private int actualNumTask = 0;
    private int firstFreeThread = 0;
    private Runnable[] tasks = new Runnable[numTask];
    private List<Runnable> taskQueue = 
            Collections.synchronizedList(new LinkedList<Runnable>());

    public ThreadPool(int numThreads, int numTask) {
        this.numThreads = numThreads;
        this.numTask = numTask;
        this.threads = new Thread[numThreads];
        tasks = new Runnable[numThreads];
        
    }
    
    public synchronized void pushTask(Runnable task) {
        if(actualNumTask < numTask) {
            tasks[actualNumTask++] = task;
            threads[firstFreeThread] = new Thread(task);
            firstFreeThread = findFirstFreeThread();
        } else {
            taskQueue.add(task);
        }
    }
    
    public boolean hasFreeThreads() {
        return actualNumTask < numTask;
    }
    
    private int findFirstFreeThread() {
        for(int i = 0; i < numThreads; i++) {
            if(!threads[i].isAlive()) {
                return i;
            }
        }
        
        return -1;
    }
    
}
