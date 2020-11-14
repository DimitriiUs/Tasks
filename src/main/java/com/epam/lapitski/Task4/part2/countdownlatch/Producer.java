package com.epam.lapitski.Task4.part2.countdownlatch;

import com.epam.lapitski.Task4.part2.Buffer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Producer implements Runnable {
    private final Random random = new Random();
    private final CountDownLatch latch;
    private Buffer buffer;

    public Producer(Buffer buffer, CountDownLatch latch) {
        this.latch = latch;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        latch.countDown();
        try {
            latch.await();
            for (int i = 0; i < 10; i++) {
                int randomInt = random.nextInt(100);
                buffer.put(randomInt);
                System.out.println(Thread.currentThread().getName() + " Producer put " + randomInt);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
