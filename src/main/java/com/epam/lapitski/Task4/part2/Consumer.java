package com.epam.lapitski.Task4.part2;

public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int gotValue = buffer.get();
            System.out.println("Consumer take " + gotValue);
        }
    }
}
