package com.epam.lapitski.Task4.part2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public Integer get() {
        return queue.poll();
    }

    public void put(Integer i) throws InterruptedException {
        queue.put(i);
    }
}
