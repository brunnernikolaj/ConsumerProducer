/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.ArrayList;
import java.util.List;
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

        in.add((long) 4);
        in.add((long) 5);
        in.add((long) 8);
        in.add((long) 12);
        in.add((long) 21);
        in.add((long) 22);
        in.add((long) 34);
        in.add((long) 35);
        in.add((long) 36);
        in.add((long) 37);
        in.add((long) 42);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(new Producer(out, in));
            t.start();
            threads.add(t);
        }

        Consumer consumer = new Consumer(out);
        Thread t3 = new Thread(consumer);
        t3.start();

        for (Thread t : threads) {
            t.join();
        }

        t3.interrupt();
        System.out.println("Total fibonaci sum: " + consumer.getSum());

    }

}
