package com.epam.lapitski.Task2;

import java.util.*;
import java.util.stream.IntStream;

public class Runner {
    public static final String EMPTY_VALUE_STRING = "";
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(Arrays.asList(
                "dfgh",
                "dfklkll",
                "dfghjhj",
                "dftyuww"));
        System.out.print("The longest prefix: ");
        System.out.println(findLongestPrefix(stringList));
        System.out.print("The longest prefix with stream: ");
        System.out.println(findLongestPrefixWithStream(stringList));
    }

    private static String findLongestPrefix(List<String> list) {
        if (isNullOrEmpty(list)) return EMPTY_VALUE_STRING;

        while(list.remove(null)) {}

        Collections.sort(list);

        int listSize = list.size();
        String first = list.get(0);
        String last = list.get(listSize - 1);

        int minLengthStringInList = Math.min(first.length(), last.length());

        int counter = 0;
        while (counter < minLengthStringInList &&
                first.charAt(counter) == last.charAt(counter)) {
            counter++;
        }

        return first.substring(0, counter);
    }

    private static String findLongestPrefixWithStream(List<String> list) {
        if (isNullOrEmpty(list)) return EMPTY_VALUE_STRING;

        String first = list
                        .stream()
                        .filter(Objects::nonNull)
                        .min(String::compareTo).get();
        String last = list
                        .stream()
                        .filter(Objects::nonNull)
                        .max(String::compareTo).get();

        int counter = (int) IntStream
                            .range(0, first.length())
                            .takeWhile(i -> first.charAt(i) == last.charAt(i))
                            .count();

        return first.substring(0, counter);
    }

    private static boolean isNullOrEmpty(List<String> list) {
        return list == null || list.isEmpty();
    }
}
