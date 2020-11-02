package com.epam.lapitski.Task4.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class DataGenerator {
    private int numberOfFiles;
    public static final String PATH = "test_data/";

    public DataGenerator(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public void generateData() {
        for (int i = 0; i < numberOfFiles; i++) {
            saveStringInFile(getRandomString(), PATH + "string_data" + i + ".txt");
        }
    }

    private String getRandomString() {
        final int LEFT_LIMIT = 48;
        final int RIGHT_LIMIT = 122;
        final int STRING_LENGTH = 2000000;
        Random random = new Random();

        return random
                .ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private void saveStringInFile(String string, String fileName) {
        Path path = Paths.get(fileName);
        byte[] stringToBytes = string.getBytes();

        try {
            Files.write(path, stringToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
