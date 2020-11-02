package com.epam.lapitski.Task4.part2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Buffer {
    private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

    public int get() {
        int  tookValue = 0;

        try {
            tookValue = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tookValue;
    }

    public void put(int i) {
        try {
            blockingQueue.put(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
