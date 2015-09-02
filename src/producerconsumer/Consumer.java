/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikolaj
 */
public class Consumer implements Runnable {

    private BlockingQueue<Long> in;
    private long sum;

    public long getSum() {
        return sum;
    }
    
    public Consumer(BlockingQueue<Long> in) {
        this.in = in;
    }

    @Override
    public void run() {
        Long cur;
        while (true) {
            try {               
                cur = in.take(); 
                sum += cur;
                System.out.println("Number : " + cur);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
