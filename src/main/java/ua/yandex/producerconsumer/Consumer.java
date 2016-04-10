package ua.yandex.producerconsumer;

import java.util.Random;
import ua.yandex.interfaces.Buffer;

/**
 *
 * @author Maksym Yatsura
 */
public class Consumer implements Runnable {
    
    private final int NUM_DATA = 5;
    private final int DELAY_MIL_SEC = 2000;
    private Buffer roundBuffer;
    
    public Consumer(Buffer roundBuffer) {
        this.roundBuffer = roundBuffer;
    }
    
    @Override
    public void run() {
        
        Random random = new Random();
        for(int i = 0; i < NUM_DATA; i++) {
            
            int value = roundBuffer.top();
            roundBuffer.pop();
            System.out.println("Consumer took: "+value);
            try {
                Thread.sleep(random.nextInt(DELAY_MIL_SEC));
            } catch (InterruptedException e) {}
        }
    }
 }
