package org.kishore.java8.lambdaexpr;

import java.util.function.Function;

/**
 * Example 1: Using Lambda with a Functional Interface
 * Here, we're using the Function functional interface to create a lambda expression
 * that takes an integer and returns its square:
 * */
public class UsingFunctionalInterfaceExample {
    public static void main(String[] args) {
        singleExpressionLambda();
        System.out.println();
        blockLambda();
    }

    private static void blockLambda() {
        Function<Integer,Integer> squareBlock = (x) -> {
            return x*x;
        };
        System.out.printf("Block Code Lambda: %d",squareBlock.apply(3));
    }

    private static void singleExpressionLambda() {
        Function<Integer,Integer> square = x -> x * x;
        System.out.printf("Single Expression Lambda: %d",square.apply(5));
    }
}
