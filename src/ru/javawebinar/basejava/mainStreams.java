package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class mainStreams {

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 3, 2, 3};
        System.out.println(minValue(arrays));
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        for (Integer x : oddOrEven(integers)) {
            System.out.println(x);
        }
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((x1, x2) -> x1 * 10 + x2).orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int summ = integers.stream()
                .reduce((x1, x2) -> x1 + x2)
                .orElse(0);
        return integers.stream()
                .filter(x -> x % 2 != summ % 2)
                .collect(Collectors.toList());
    }
}
