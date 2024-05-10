package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.Arrays;
import java.util.List;

/* *
* limit(long n): Limits the stream to a maximum number of elements.
* */
public class StreamLimit {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,4,2,9,3,4,5,8,3,0);

        List<Integer> limit5 = numbers.stream()
                .limit(5) // intermediate operation
                .toList();

        System.out.println("Limit 5: " + limit5);

    }
}
