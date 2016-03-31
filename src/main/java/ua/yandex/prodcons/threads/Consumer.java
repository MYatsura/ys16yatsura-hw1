package ua.yandex.prodcons.threads;

/**
 *
 * @author Maksym Yatsura
 */
public class Consumer implements Runnable{
    
    private RoundBuffer roundBuffer;

    public Consumer(RoundBuffer roundBuffer) {
        this.roundBuffer = roundBuffer;
    }
    
    @Override
    public void run() {
        
        for(int i = 0; i < 3; i++) {
            if(!roundBuffer.empty()) {
                roundBuffer.read();
            }
        }
    }
}
