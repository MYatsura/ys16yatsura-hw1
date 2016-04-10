package ua.yandex.producerconsumer;

import java.util.Random;
import ua.yandex.interfaces.Buffer;

/**
 *
 * @author Maksym Yatsura
 */
public class Producer implements Runnable{
 
    private final int NUM_DATA = 5;
    private final int DELAY_MIL_SEC = 2000;
    private Buffer roundBuffer;
    private int[] data = {1, 2, 3, 4, 5};

    public Producer(Buffer roundBuffer) {
        this.roundBuffer = roundBuffer;
    }
    
    @Override
    public void run() {
        
        Random random = new Random();
        
        for(int i = 0; i < NUM_DATA; i++) {
            roundBuffer.push(data[i]);
            System.out.println("Producer pushed: "+data[i]);
            try {
                Thread.sleep(random.nextInt(DELAY_MIL_SEC));
            } catch (InterruptedException e) {}
        }
    }
    
    
}
