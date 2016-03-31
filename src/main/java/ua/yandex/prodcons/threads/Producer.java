/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.yandex.prodcons.threads;

/**
 *
 * @author Maksym Yatsura
 */
public class Producer implements Runnable{
 
    private RoundBuffer roundBuffer;
    private int[] data = {1, 2, 3};

    public Producer(RoundBuffer roundBuffer) {
        this.roundBuffer = roundBuffer;
    }
    
    @Override
    public void run() {
        
        for(int i = 0; i < data.length; i++) {
            if(!roundBuffer.filled()) {
                roundBuffer.push(data[i]);
            }
        }
    }
    
    
}
