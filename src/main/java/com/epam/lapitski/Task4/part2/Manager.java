package com.epam.lapitski.Task4.part2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager {
    private ExecutorService producerThreadPool;
    private ExecutorService consumerThreadPool;
    private int producerThreadCount;
    private int consumerThreadCount;
    private Buffer buffer;

    public Manager(int producerThreadsCount, int consumerThreadsCount) {
        producerThreadPool = Executors.newFixedThreadPool(producerThreadsCount);
        consumerThreadPool = Executors.newFixedThreadPool(consumerThreadsCount);
        this.producerThreadCount = producerThreadsCount;
        this.consumerThreadCount = consumerThreadsCount;
        buffer = new Buffer();
    }

    public void runManager() {
        for (int i = 0; i < producerThreadCount; i++) {
            producerThreadPool.submit(new Producer(buffer));
        }
        producerThreadPool.shutdown();

        for (int i = 0; i < consumerThreadCount; i++) {
            consumerThreadPool.submit(new Consumer(buffer));
        }

        consumerThreadPool.shutdown();
    }
}
