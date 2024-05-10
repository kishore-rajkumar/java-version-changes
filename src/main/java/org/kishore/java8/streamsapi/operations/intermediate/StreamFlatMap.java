package org.kishore.java8.streamsapi.operations.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * flatMap() transforms each element of a stream into another stream
 * and then "flattens" these resulting streams into a single stream.
 * It's useful when you have nested data structures and
 * you want to operate on all nested elements in a streamlined way.
 * **/
public class StreamFlatMap {
    public static void main(String[] args) {
       FlatteningListofLists.flatten();
       System.out.println("---------------------------------------------" +
               "--------------------------------------");
       SplittingStringsintoWords.split();
    }
}

/* *
 * Example 1: Flattening a List of Lists
 * Suppose you have a list of lists, and you want to flatten them into a single list
 * */
class FlatteningListofLists{
    public static void flatten(){
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E", "F", "G")
        );
        System.out.println("Un-Flattened - "+listOfLists);

        List<String> result = listOfLists.stream()
                .flatMap(list->list.stream()) // intermediate operation
                .toList();
        System.out.println("Flattened - "+result);
    }
}

/* *
 * Example 2: Splitting Strings into Words
 * Another common use of flatMap() is to split strings into words
 * and then create a single stream of all words:
 * */
class SplittingStringsintoWords{

    public static void split() {
        List<String> sentences = Arrays.asList(
                "The quick brown fox",
                "jumps over the lazy dog",
                "and runs away"
        );
        System.out.println("Un-Split Sentences - "+ sentences);
        List<String> result = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // intermediate operation
                .toList();
        System.out.println("Split words: " + result);
    }
}

