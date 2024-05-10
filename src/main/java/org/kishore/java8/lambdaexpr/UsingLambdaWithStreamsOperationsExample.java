package org.kishore.java8.lambdaexpr;

import java.util.Arrays;
import java.util.List;

/* *
 * Example 2: Using Lambda with Stream Operations
 * The Streams API allows you to use lambda expressions to work
 * with collections.
 * Here's an example of filtering a list of strings
 * to find those that start with "J":
 * */
public class UsingLambdaWithStreamsOperationsExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John","jane","Jenny","Tom");
        List<String> result = names
                .stream()
                .filter(name -> name.toLowerCase().startsWith("j"))
                .toList();
        System.out.println(result);
    }
}
