package com.epam.lapitski.Task4.part2.countdownlatch;

import com.epam.lapitski.Task4.part2.Buffer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager {
    private final ExecutorService pool;
    private final int producerThreadsCount;
    private final int consumerThreadsCount;
    private final Buffer buffer = new Buffer();
    private final CountDownLatch latch;

    public Manager(int producerThreadsCount, int consumerThreadsCount) {
        this.producerThreadsCount = producerThreadsCount;
        this.consumerThreadsCount = consumerThreadsCount;
        pool = Executors.newCachedThreadPool();
        latch = new CountDownLatch(producerThreadsCount + consumerThreadsCount);
    }

    public void runManager() {
        for (int i = 0; i < consumerThreadsCount; i++) {
            pool.execute(new Consumer(buffer, latch));
        }
        for (int i = 0; i < producerThreadsCount; i++) {
            pool.execute(new Producer(buffer, latch));
        }

        pool.shutdown();
    }
}
