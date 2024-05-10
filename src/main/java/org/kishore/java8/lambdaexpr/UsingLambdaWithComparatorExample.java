package org.kishore.java8.lambdaexpr;

import java.util.Arrays;
import java.util.List;

public class UsingLambdaWithComparatorExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("banana","apple", "orange");
        words.sort((a,b) -> { // intentionally used lambda block to check,
                              // otherwise can be done with a single statement;
            return a.compareTo(b);
        });
        System.out.println(words);
    }
}
