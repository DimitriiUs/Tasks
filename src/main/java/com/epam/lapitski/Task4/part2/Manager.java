package com.epam.lapitski.Task4.part2;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager {
    private final ExecutorService pool;
    private final int producerThreadCount;
    private final int consumerThreadCount;
    private final Buffer buffer = new Buffer();
    private final CyclicBarrier barrier;

    public Manager(int producerThreadsCount, int consumerThreadsCount) {
        pool = Executors.newCachedThreadPool();
        barrier = new CyclicBarrier(producerThreadsCount + consumerThreadsCount);
        this.producerThreadCount = producerThreadsCount;
        this.consumerThreadCount = consumerThreadsCount;
    }

    public void runManager() {
        for (int i = 0; i < producerThreadCount; i++) {
            pool.execute(new Producer(buffer, barrier));
        }
        for (int i = 0; i < consumerThreadCount; i++) {
            pool.execute(new Consumer(buffer, barrier));
        }

        pool.shutdown();
    }
}
