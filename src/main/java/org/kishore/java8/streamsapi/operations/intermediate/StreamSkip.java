package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.Arrays;
import java.util.List;

/* *
* skip(long n): Skips a given number of elements.
* */
public class StreamSkip {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,4,2,9,3,4,5,8,3,0);

        List<Integer> skipped3 = numbers.stream()
                .skip(3) // intermediate operation
                .toList();

        System.out.println("Skipped 3: " + skipped3);
    }
}
