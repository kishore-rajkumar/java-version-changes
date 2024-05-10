package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.Arrays;
import java.util.List;

/* *
* map(Function<T, R>): Transforms each element by applying a function.
* * */
public class StreamMap {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John","Cambly",
                "Monty","Lisa","Parker");
        //convert names to upper case
        List<String> result = names.stream()
                .map(n -> n.toUpperCase())  // intermediate operation
                .toList();
        System.out.println(result);
    }
}
