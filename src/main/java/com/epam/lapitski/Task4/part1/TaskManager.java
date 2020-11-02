package com.epam.lapitski.Task4.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskManager {
    private ExecutorService threadPool;

    public TaskManager(int threadCount) {
        threadPool = Executors.newFixedThreadPool(threadCount);
    }

    public void runTasks() {
        long start = System.nanoTime();

        List<String> filesPaths = getFilesPathsList();
        for (String path : filesPaths) {
            threadPool.submit(new Reader(path));
        }

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(100000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double time = Math.round(((System.nanoTime() - start) / (1_000_000_000.0)) * 100.0) / 100.0;
        System.out.printf("Run time: %.2f sec", time);
    }

    private List<String> getFilesPathsList() {
        final String TEST_DATA_FOLDER = "test_data";
        List<String> filesPaths = new ArrayList<>();

        try (Stream<Path> walk = Files.walk(Paths.get(TEST_DATA_FOLDER))) {
            filesPaths = walk
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filesPaths;
    }

}
