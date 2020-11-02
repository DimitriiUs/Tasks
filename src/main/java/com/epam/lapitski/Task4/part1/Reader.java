package com.epam.lapitski.Task4.part1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader implements Runnable {
    private String filePath;

    public Reader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            System.out.println("Work " + Thread.currentThread().getId());
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String content = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done " + Thread.currentThread().getId());
    }
}
