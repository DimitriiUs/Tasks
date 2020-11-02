package com.epam.lapitski.Task4.part1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner4 {
    public static void main(String[] args) {

        deleteTestData();

        DataGenerator generator = new DataGenerator(100);
        generator.generateData();

        TaskManager manager = new TaskManager(20);
        manager.runTasks();

    }

    private static void deleteTestData() {
        try {
            Files.walk(Paths.get("test_data"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
