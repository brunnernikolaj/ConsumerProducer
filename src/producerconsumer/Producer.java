/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikolaj
 */
public class Producer implements Runnable {

    private BlockingQueue<Long> out;
    private BlockingQueue<Long> in;

    public Producer(BlockingQueue<Long> out, BlockingQueue<Long> in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void run() {
        Long cur;
        while ((cur = in.poll()) != null) {
            try {
                out.put(fib(cur));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
