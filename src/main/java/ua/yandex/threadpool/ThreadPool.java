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
    private Thread[] threads;
    private int actualNumTask = 0;
    private int firstFreeThread = 0;
    private TaskRunner[] taskRunners;
    private List<Runnable> taskQueue
            = Collections.synchronizedList(new LinkedList<Runnable>());
    private boolean active = false;

    private class TaskRunner implements Runnable {

        private final int id;
        private boolean active = true;

        public TaskRunner(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (active) {
                if (!taskQueue.isEmpty()) {
                    Runnable task = taskQueue.get(0);
                    taskQueue.remove(0);
                    if (task != null) {
                        task.run();
                    }
                }
            }
        }

        public void disable() {
            active = false;
        }

    }

    public ThreadPool() {
        init(10);
    }

    public ThreadPool(int numThreads) {
        init(numThreads);
    }
    
    private void init(int numThreads) {
        this.numThreads = numThreads;
        threads = new Thread[numThreads];
        taskRunners = new TaskRunner[numThreads];
        for (int i = 0; i < numThreads; i++) {
            taskRunners[i] = new TaskRunner(i);
            threads[i] = new Thread(taskRunners[i]);
        }        
    }

    public void activate() {
        active = true;
        for (int i = 0; i < numThreads; i++) {
            threads[i].start();
        }
    }

    public boolean isActive() {
        return active;
    }

    public synchronized boolean pushTask(Runnable task) {
        if (!active) {
            return false;
        }
        taskQueue.add(task);
        return true;
    }

    public void deactivate() {

        for (int i = 0; i < numThreads; i++) {
            taskRunners[i].disable();
        }
        
        active = false;
    }

}
