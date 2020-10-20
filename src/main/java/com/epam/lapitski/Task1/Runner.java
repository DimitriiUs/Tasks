package com.epam.lapitski.Task1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        List<Integer> randomElementsList = createRandomIntList(0, 20, 100);

        System.out.println("Random List");
        System.out.println(randomElementsList);
        System.out.println("Without Stream API version 1");
        System.out.println(countRepeatingElementsVersion1(randomElementsList));
        System.out.println("Without Stream API version 2");
        System.out.println(countRepeatingElementsVersion2(randomElementsList));
        System.out.println("With Stream API");
        System.out.println(countRepeatingElementsWithStream(randomElementsList));
    }

    private static <T> Map<T, Long> countRepeatingElementsWithStream(List<T> list) {
        return list
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static <T> Map<T, Long> countRepeatingElementsVersion1(List<T> list) {
        Map<T, Long> result = new HashMap<>();

        for (T element : list) {
            if (result.containsKey(element)) {
                result.put(element, result.get(element) + 1);
            } else {
                result.put(element, (long) 1);
            }
        }

        return result;
    }

    private static <T> Map<T, Long> countRepeatingElementsVersion2(List<T> list) {
        Map<T, Long> result = new HashMap<>();

        Set<T> uniqueElements = new HashSet<>(list);

        for (T element : uniqueElements) {
            result.put(element, (long) Collections.frequency(list, element));
        }

        return result;
    }

    private static List<Integer> createRandomIntList(int min, int max, int listSize) {
        return new Random().ints(listSize, min, max).boxed().collect(Collectors.toList());
    }
}
