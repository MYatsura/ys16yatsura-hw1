package ua.yandex.threadpool;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 *
 * @author Maksym Yatsura
 */
public class ThreadPoolTest {
    
    private Set taskIdSet = new HashSet<Integer>();
    
    private class TestTask implements Runnable {

        private final int id;

        public TestTask(int id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            taskIdSet.add(id);
        }
    }
    
    @Test
    public void testThreadPool_isActive() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.activate();
        assertTrue(threadPool.isActive());
    }
    
    @Test
    public void testThreadPool1() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.activate();
        int numTask = 40;
        TestTask[] tasks = new TestTask[numTask];
        taskIdSet.clear();
        Thread.sleep(500);
        Set expSet = new HashSet<>();
        for(int i = 0; i < numTask; i++) {
            expSet.add(i);
        }
        
        
        for(int i = 0; i < numTask; i++) {
            tasks[i] = new TestTask(i);
        }        
        
        for(int i = 0; i < numTask; i++) {
            threadPool.pushTask(tasks[i]);
        }
        
        Thread.sleep(500);
        threadPool.deactivate();
        while(threadPool.isActive());
        
        assertEquals(expSet, taskIdSet);
    }
    
    
    @Test
    public void testThreadPool2() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.activate();
        int numTask = 1000;
        TestTask[] tasks = new TestTask[numTask];
        taskIdSet.clear();
        Set expSet = new HashSet<>();
        for(int i = 0; i < numTask; i++) {
            expSet.add(i);
        }

        
        
        for(int i = 0; i < numTask; i++) {
            tasks[i] = new TestTask(i);
        }        
        
        for(int i = 0; i < numTask; i++) {
            threadPool.pushTask(tasks[i]);
        }
        
        Thread.sleep(500);
        threadPool.deactivate();
        while(threadPool.isActive());
        
        assertEquals(expSet, taskIdSet);
        
    }
    
    
}
