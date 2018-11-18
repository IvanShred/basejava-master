package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class mainStreams {
    private static int result = 0;

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
        Arrays.stream(values)
                .distinct()
                .sorted()
                .forEachOrdered((x) -> changeResult(x));
        return result / 10;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream()
                .reduce((x1, x2) -> x1 + x2)
                .orElse(0) % 2 == 0) {
            return integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
        } else {
            return integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        }
    }

    private static int changeResult(int x) {
        result = (x + result) * 10;
        return result;
    }
}
