package ua.yandex.prodcons.threads;

import ua.yandex.producerconsumer.Producer;
import ua.yandex.producerconsumer.Consumer;
import org.junit.Test;

/**
 *
 * @author user
 */
public class SynchronizedRoundBufferTest {
    
    @Test
    public void SynchronizedRoundBufferTest1() {
        SynchronizedRoundBuffer rBuffer = new SynchronizedRoundBuffer(100);
        int num_producer = 5;
        int num_consumer = 5;
        
        Producer[] producers = new Producer[num_producer];
        Consumer[] consumers = new Consumer[num_consumer];
        for(int i = 0; i < num_producer; i++) {
            producers[i] = new Producer(rBuffer);
        }
        for(int i = 0; i < num_consumer; i++) {
            consumers[i] = new Consumer(rBuffer);
        }
        
        for(int i = 0; i < num_producer; i++) {
            producers[i].run();
        }
        
        for(int i = 0; i < num_consumer; i++) {
            consumers[i].run();
        }
        
    }
    
    
}
