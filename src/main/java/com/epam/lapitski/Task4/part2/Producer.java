package com.epam.lapitski.Task4.part2;

import java.util.Random;

public class Producer implements Runnable {
    private Buffer buffer;
    private Random random = new Random();

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int randomInt = random.nextInt(100);
            buffer.put(randomInt);
            System.out.println("Producer put " + randomInt);
        }
    }
}
