package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.Arrays;
import java.util.List;

/* *
 * sorted(): Sorts elements in natural order or using a comparator.
 * */
public class StreamSorted {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,4,2,9,3,4,5,8,3,0);
        List<Integer> sorted = numbers.stream()
               // .sorted() // intermediate operation - natural order
                .sorted((i,j) -> j.compareTo(i)) // intermediate operation - reverse order
                .toList();
        System.out.println("Sorted: " + sorted);
    }
}
