/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Nikolaj
 */
public class ProducerConsumer {

    private static final BlockingQueue<Long> in = new ArrayBlockingQueue<>(20);
    private static BlockingQueue<Long> out = new ArrayBlockingQueue<>(20);
    
    public static void main(String[] args) throws InterruptedException {
        in.add((long)4);
        in.add((long)5);
        in.add((long)8);
        in.add((long)12);
        in.add((long)21);
        in.add((long)22);
        in.add((long)34);
        in.add((long)35);
        in.add((long)36);
        in.add((long)37);
        in.add((long)42);

        Thread t1 = new Thread(new Producer(out, in));
        Thread t2 = new Thread(new Producer(out, in));
        Thread t3 = new Thread(new Consumer(out));
        
        t1.start();
        t2.start();
        t3.start();
        t2.join();
        t1.join();
        
        t3.interrupt();

    }
    
}
