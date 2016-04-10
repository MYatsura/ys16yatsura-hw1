/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.prodcons.utilconcurrent;

import org.junit.Test;
import static org.junit.Assert.*;
import ua.yandex.producerconsumer.Consumer;
import ua.yandex.producerconsumer.Producer;

/**
 *
 * @author user
 */
public class ConcurrentRoundBufferTest {
    
    public ConcurrentRoundBufferTest() {
    }

    @Test
    public void testConcurrentRoundBuffer() {
        
        ConcurrentRoundBuffer rBuffer = new ConcurrentRoundBuffer(100);
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
