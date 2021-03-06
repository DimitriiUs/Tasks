package com.epam.lapitski.Task4.part2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final Random random = new Random();
    private final CyclicBarrier barrier;

    public Producer(Buffer buffer, CyclicBarrier barrier) {
        this.buffer = buffer;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            barrier.await();

            for (int i = 0; i < 10; i++) {
                int randomInt = random.nextInt(100);

                buffer.put(randomInt);

                System.out.println(Thread.currentThread().getName() + " Producer put " + randomInt);
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
