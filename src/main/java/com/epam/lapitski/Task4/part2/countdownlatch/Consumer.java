package com.epam.lapitski.Task4.part2.countdownlatch;

import com.epam.lapitski.Task4.part2.Buffer;

import java.util.concurrent.CountDownLatch;

public class Consumer implements Runnable {
    private final CountDownLatch latch;
    private final Buffer buffer;

    public Consumer(Buffer buffer, CountDownLatch latch) {
        this.latch = latch;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        latch.countDown();
        while (true) {
            try {
                latch.await();
                Integer gotValue = buffer.get();
                if (gotValue == null) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " Consumer poll " + gotValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
