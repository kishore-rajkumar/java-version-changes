package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.Arrays;
import java.util.List;

/* *
* distinct(): Removes duplicate elements.
* */
public class StreamDistinct {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,4,2,9,3,4,5,8,3,0);
        List<Integer> distinctNums = numbers.stream()
                .distinct() // intermediate operation
                .toList();
        System.out.println(distinctNums);
    }
}
