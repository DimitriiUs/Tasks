package com.epam.lapitski.Task4.part2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Consumer implements Runnable {
    private final Buffer buffer;
    private final CyclicBarrier barrier;

    public Consumer(Buffer buffer, CyclicBarrier barrier) {
        this.buffer = buffer;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            barrier.await();

            while (true) {
                Integer gotValue;

                gotValue = buffer.get();

                if (gotValue == null) {
                    break;
                }

                System.out.println(Thread.currentThread().getName() + " Consumer get " + gotValue);
            }

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
