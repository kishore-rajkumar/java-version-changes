package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.Arrays;
import java.util.List;

/* *
 * filter(Predicate<T>): Filters elements based on a condition.
 * */
public class StreamFilter {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John","Cambly",
                "Monty","Lisa","Parker");
        List<String> result = names.stream()
                .filter(n-> n.length()<=4) // intermediate operation
                .toList();
        System.out.println(result);
    }
}
